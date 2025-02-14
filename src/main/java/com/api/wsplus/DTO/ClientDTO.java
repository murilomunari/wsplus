package com.api.wsplus.DTO;

import com.api.wsplus.Entity.Order;

import java.util.List;

public record ClientDTO(String firstName, String lastName, String cpf, String phoneNumber, String emailAddress, List<Order> orders) {
}
