package com.projeto.AlugaAi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Anotação do Lombok para gerar getters, setters, equals, hashCode e métodos toString
@NoArgsConstructor  // Anotação do Lombok para gerar um construtor sem argumentos
@AllArgsConstructor  // Anotação do Lombok para gerar um construtor com todos os campos como argumentos
@Entity  // Anotação do JPA para marcar esta classe como uma entidade persistente
@Table(name = "addresses")  // Anotação do JPA para especificar o nome da tabela no banco de dados
public class AddressModel {

    @Id  // Anotação do JPA para marcar este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Anotação do JPA para indicar que o ID deve ser gerado automaticamente
    private int id;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String street;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String number;

    @Column  // Anotação do JPA para especificar que esta coluna pode ser nula
    private String complement;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String city;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String state;

    @Column(nullable = false)  // Anotação do JPA para especificar que esta coluna não pode ser nula
    private String cep;

    @ManyToOne  // Anotação do JPA para especificar um relacionamento muitos-para-um com a entidade ClientModel
    @JoinColumn(name = "client_id", nullable = false)  // Anotação do JPA para especificar a coluna de junção que referencia a chave estrangeira da tabela de clientes e que não pode ser nula
    private ClientModel client;
}
