package com.api.wsplus.DTO;

public record AddressDTO(String street,
                         String city,
                         String state,
                         String postalCode,
                         String country) {
}
