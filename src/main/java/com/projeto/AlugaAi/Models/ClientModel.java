package com.projeto.AlugaAi.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projeto.AlugaAi.Enum.ClientType;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String cnh;

    @Column(name = "year_of_birth", nullable = false)
    private int yearOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type", nullable = false)
    private ClientType clientType;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AddressModel> addresses;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER) // Mapeamento um-para-muitos com a classe RentalModel
    private List<RentalModel> rentals; // Lista de aluguéis associados a este cliente
}
