package com.api.wsplus.Repository;

import com.api.wsplus.Entity.Cart;
import com.api.wsplus.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByClient(Client client);
}
