package com.api.wsplus.Service;

import com.api.wsplus.DTO.ClientDTO;
import com.api.wsplus.Entity.Client;
import com.api.wsplus.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(ClientDTO clientDTO){
        Client client = new Client();
        client.setCpf(clientDTO.cpf());
        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());
        client.setEmailAddress(clientDTO.emailAddress());
        client.setPhoneNumber(clientDTO.phoneNumber());
        //client.setOrders(clientDTO.orders());

        return clientRepository.save(client);
    }

}
