package com.api.wsplus.controller;

import com.api.wsplus.DTO.AuthenticationDTO;
import com.api.wsplus.DTO.LoginResponseDTO;
import com.api.wsplus.DTO.RegisterDTO;
import com.api.wsplus.entity.Client;
import com.api.wsplus.entity.User;
import com.api.wsplus.Repository.UserRepository;
import com.api.wsplus.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO){


        if (this.userRepository.findByLogin(registerDTO.login()) != null)
            return ResponseEntity.badRequest().body("Login already exists");

        Client client = new Client();
        client.setFirstName(registerDTO.firstName());
        client.setLastName(registerDTO.lastName());
        client.setCpf(registerDTO.cpf());
        client.setPhoneNumber(registerDTO.phoneNumber());
        client.setEmailAddress(registerDTO.emailAddress());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        User newUser = new User(registerDTO.login(), encryptedPassword, registerDTO.role());
        newUser.setClient(client);

        this.userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

}
