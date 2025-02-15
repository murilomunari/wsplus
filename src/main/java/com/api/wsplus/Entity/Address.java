package com.api.wsplus.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String country;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Construtor padrão
    public Address() {}

    // Construtor com parâmetros
    public Address(String street, String city, String state, String postalCode, String country, Client client) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.client = client;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }

    public void setCountry(String country) {
        this.country = country; }

    public Client getClient() { return client; }

    public void setClient(Client client) { this.client = client; }
}
