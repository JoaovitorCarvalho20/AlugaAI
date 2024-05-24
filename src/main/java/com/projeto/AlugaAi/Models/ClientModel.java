package com.projeto.AlugaAi.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projeto.AlugaAi.Enum.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Lombok annotation para gerar getters, setters, toString, equals, e hashCode automaticamente
@NoArgsConstructor // Lombok annotation para gerar construtor padrão sem argumentos
@AllArgsConstructor // Lombok annotation para gerar construtor com todos os argumentos
@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "clients") // Especifica o nome da tabela correspondente no banco de dados
public class ClientModel {

    @Id // Indica que esta propriedade é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração automática do valor da chave primária
    private int id; // Identificador único do cliente

    @Column(nullable = false) // Especifica que este campo não pode ser nulo no banco de dados
    private String name; // Nome do cliente

    @Column(nullable = false)
    private String lastName; // Sobrenome do cliente

    @Column(nullable = false)
    private String phone; // Número de telefone do cliente

    @Column(nullable = false, unique = true) // Especifica que este campo é único (não pode haver CPFs duplicados)
    private String cpf; // CPF do cliente

    @Column(nullable = false, unique = true)
    private String cnh; // Número da CNH do cliente

    @Column(name = "year_of_birth", nullable = false)
    private int yearOfBirth; // Ano de nascimento do cliente

    @Column(nullable = false, unique = true)
    private String email; // Endereço de e-mail do cliente

    @Column(nullable = false)
    private String password; // Senha do cliente

    @Enumerated(EnumType.STRING) // Especifica que este campo é um enum cujos valores são armazenados como strings no banco de dados
    @Column(name = "client_type", nullable = false)
    private ClientType clientType; // Tipo de cliente (enum ClientType)

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER) // Mapeamento um-para-muitos com a classe AddressModel, onde "client" é o nome da propriedade na classe AddressModel que mapeia o relacionamento
    private List<AddressModel> addresses; // Lista de endereços associados a este cliente
}
