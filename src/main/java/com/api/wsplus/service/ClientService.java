package com.api.wsplus.service;

import com.api.wsplus.DTO.ClientDTO;
import com.api.wsplus.entity.Client;
import com.api.wsplus.entity.Cart;
import com.api.wsplus.Repository.ClientRepository;
import com.api.wsplus.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CartRepository cartRepository;

    public Client create(ClientDTO clientDTO) {
        System.out.println("Salvando CPF: " + clientDTO.cpf()); // para log

        // Criando o carrinho associado ao cliente
        Cart cart = new Cart();
        Client client = new Client();
        client.setCpf(clientDTO.cpf());
        client.setFirstName(clientDTO.firstName());
        client.setLastName(clientDTO.lastName());
        client.setEmailAddress(clientDTO.emailAddress());
        client.setPhoneNumber(clientDTO.phoneNumber());
        client.setCart(cart);  // Associando o carrinho ao cliente

        // Salvar o cliente e o carrinho
        cart.setClient(client);
        clientRepository.save(client); // Isso salva automaticamente o cliente e o carrinho devido ao CascadeType.ALL

        return client;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findByCpf(String cpf) {
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
