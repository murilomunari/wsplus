package com.api.wsplus.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") // "order" é uma palavra reservada no SQL, então alterei para "orders"
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Garante que o ID será gerado automaticamente
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne // Muitos pedidos para um cliente
    @JoinColumn(name = "client_id", nullable = false) // Nome da chave estrangeira
    private Client customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private BigDecimal totalAmount;

    private String paymentMethod;

    @ManyToOne // Muitos pedidos podem estar associados a um endereço
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;
}
