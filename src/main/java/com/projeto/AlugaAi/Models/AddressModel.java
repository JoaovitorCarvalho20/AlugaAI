package com.projeto.AlugaAi.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation para gerar getters, setters, toString, equals, e hashCode automaticamente
@NoArgsConstructor // Lombok annotation para gerar construtor padrão sem argumentos
@AllArgsConstructor // Lombok annotation para gerar construtor com todos os argumentos
@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "addresses") // Especifica o nome da tabela correspondente no banco de dados
public class AddressModel {

    @Id // Indica que esta propriedade é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração automática do valor da chave primária
    private int id; // Identificador único do endereço

    @Column(nullable = false) // Especifica que este campo não pode ser nulo no banco de dados
    private String street; // Nome da rua

    @Column(nullable = false)
    private String number; // Número do endereço

    @Column
    private String complement; // Complemento do endereço

    @Column(nullable = false)
    private String city; // Cidade

    @Column(nullable = false)
    private String state; // Estado

    @Column(nullable = false)
    private String cep; // CEP (Código de Endereçamento Postal)

    @JsonBackReference // Anotação Jackson para controlar a serialização (evitar loop infinito)
    @ManyToOne(fetch = FetchType.LAZY) // Mapeamento muitos-para-um com a entidade ClientModel
    @JoinColumn(name = "client_id", nullable = false) // Nome da coluna na tabela de endereços que faz referência ao cliente
    private ClientModel client; // Cliente associado a este endereço
}
