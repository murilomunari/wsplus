package com.api.wsplus.DTO;

import com.api.wsplus.Entity.ROLE;

public record RegisterDTO(String login,
                          String password,
                          ROLE role) {
}
