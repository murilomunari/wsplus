package com.api.wsplus.repository;

import com.api.wsplus.entity.Cart;
import com.api.wsplus.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByClient(Client client);
}
