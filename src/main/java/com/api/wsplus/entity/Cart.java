package com.api.wsplus.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import com.api.wsplus.entity.Client;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    @Column(nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO; // Inicializa com 0 para evitar problemas

    public Cart() {
    }

    public Cart(Client client, List<CartItem> items, BigDecimal totalAmount) {
        this.client = client;
        this.items = items;
        this.totalAmount = (totalAmount != null) ? totalAmount : BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return (totalAmount != null) ? totalAmount : BigDecimal.ZERO;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = (totalAmount != null) ? totalAmount : BigDecimal.ZERO;
    }
}
