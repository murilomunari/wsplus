package com.api.wsplus.DTO;

import com.api.wsplus.entity.ROLE;

public record RegisterDTO(
        String login,
        String password,
        ROLE role,
        String firstName,
        String lastName,
        String cpf,
        String phoneNumber,
        String emailAddress
) {}
