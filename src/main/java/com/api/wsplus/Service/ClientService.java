package com.api.wsplus.Service;

import com.api.wsplus.DTO.ClientDTO;
import com.api.wsplus.Entity.Client;
import com.api.wsplus.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client create(ClientDTO clientDTO){
        System.out.println("Salvando CPF: " + clientDTO.cpf()); // para log
        Client client = new Client();
        client.setCpf(clientDTO.cpf());
        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());
        client.setEmailAddress(clientDTO.emailAddress());
        client.setPhoneNumber(clientDTO.phoneNumber());


        return clientRepository.save(client);
    }


    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findByCpf(String cpf){
        System.out.println("Buscando CPF: " + cpf); // para log
        return clientRepository.findByCpf(cpf);
    }

    public void deleteByCpf(String cpf) {
        System.out.println("Deletando CPF: " + cpf); // Log para rastreamento
        Optional<Client> clientOptional = clientRepository.findByCpf(cpf);

        if (clientOptional.isPresent()) {
            clientRepository.delete(clientOptional.get());
        } else {
            throw new RuntimeException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }


}
