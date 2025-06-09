package com.api.wsplus.DTO;

import com.api.wsplus.entity.Cart;

public record ClientDTO(String firstName,
                        String lastName,
                        String cpf,
                        String phoneNumber,
                        String emailAddress,
                        Cart cart) {
}
