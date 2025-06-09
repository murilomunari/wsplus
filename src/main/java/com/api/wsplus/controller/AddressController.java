package com.api.wsplus.controller;

import com.api.wsplus.DTO.AddressDTO;
import com.api.wsplus.entity.Address;
import com.api.wsplus.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> create(@Valid @RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(addressDTO));
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
    }
}
