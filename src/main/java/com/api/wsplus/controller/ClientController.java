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

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody ClientDTO clientDTO) {
        Client createdClient = clientService.create(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);  // Retornando o cliente com o carrinho
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable String cpf) {
        return clientService.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<Void> deleteByCpf(@PathVariable String cpf) {
        clientService.deleteByCpf(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
