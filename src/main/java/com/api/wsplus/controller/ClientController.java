package com.api.wsplus.controller;

import com.api.wsplus.DTO.ClientDTO;
import com.api.wsplus.Entity.Client;
import com.api.wsplus.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(clientDTO));
    }

}
