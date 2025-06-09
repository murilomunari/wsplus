package com.api.wsplus.service;

import com.api.wsplus.DTO.AddressDTO;
import com.api.wsplus.entity.Address;
import com.api.wsplus.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address create(AddressDTO addressDTO){
        Address address = new Address();
        address.setCity(addressDTO.city());
        address.setCountry(addressDTO.country());
        address.setState(addressDTO.state());
        address.setStreet(addressDTO.street());
        address.setPostalCode(addressDTO.postalCode());

        return addressRepository.save(address);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
