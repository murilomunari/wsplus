package com.api.wsplus.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "d{11}", message = "CPF must contain exactly 11 digits")
    @Column(unique = true, nullable = false)
    private String CPF;

    @Pattern(regexp = "\\+?\\d{10,15}", message = "Invalid phone number format")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String emailAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;
}
