package com.projeto.AlugaAi.Models;

import com.projeto.AlugaAi.Enum.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data  // Anotação do Lombok para gerar getters, setters, equals, hashCode e métodos toString
@NoArgsConstructor  // Anotação do Lombok para gerar um construtor sem argumentos
@AllArgsConstructor  // Anotação do Lombok para gerar um construtor com todos os campos como argumentos
@Entity  // Anotação do JPA para marcar esta classe como uma entidade persistente
@Table(name = "clients")  // Anotação do JPA para especificar o nome da tabela no banco de dados
public class ClientModel {

    @Id  // Anotação do JPA para marcar este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Anotação do JPA para indicar que o ID deve ser gerado automaticamente
    private int id;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String name;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String lastName;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String phone;

    @Column(nullable = false, unique = true)  // Anotação do JPA para especificar que esta coluna não pode ser nula e deve ser única
    private String cpf;

    @Column(nullable = false, unique = true)  // Anotação do JPA para especificar que esta coluna não pode ser nula e deve ser única
    private String cnh;

    @Column(name = "year_of_birth", nullable = false)  // Anotação do JPA para especificar o nome da coluna e que ela não pode ser nula
    private int yearOfBirth;

    @Column(nullable = false, unique = true)  // Anotação do JPA para especificar que esta coluna não pode ser nula e deve ser única
    private String email;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String password;

    @Enumerated(EnumType.STRING)  // Anotação do JPA para especificar que este campo é um tipo enumerado e deve ser armazenado como uma string
    @Column(name = "client_type", nullable = false)  // Anotação do JPA para especificar o nome da coluna e que ela não pode ser nula
    private ClientType clientType;

    @OneToMany(mappedBy = "client")  // Anotação do JPA para especificar um relacionamento um-para-muitos com a entidade AddressModel
    private List<AddressModel> addresses;

    // Descomente as linhas abaixo quando a classe RentalModel estiver implementada
    // @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL)
    // private List<RentalModel> rentalsAsRenter;

    // @OneToMany(mappedBy = "lessor", cascade = CascadeType.ALL)
    // private List<RentalModel> rentalsAsLessor;
}
