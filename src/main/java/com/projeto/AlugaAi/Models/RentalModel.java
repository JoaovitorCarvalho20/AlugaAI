package com.projeto.AlugaAi.Models;

// Importações necessárias
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Anotação do Lombok que gera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Anotação do Lombok que gera um construtor sem argumentos
@AllArgsConstructor // Anotação do Lombok que gera um construtor com todos os argumentos
@Entity // Anotação que indica que a classe é uma entidade JPA
@Table(name = "rentals") // Especifica o nome da tabela no banco de dados
public class RentalModel {

    @Id // Indica que o campo 'id' é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id;

    @ManyToOne // Relacionamento muitos-para-um com a entidade Car
    @JoinColumn(name = "car_id") // Especifica a coluna de junção para o relacionamento
    private Car car;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore // Evita a serialização recursiva
    private ClientModel client;


    @Temporal(TemporalType.DATE) // Indica que o campo é uma data (sem tempo)
    private Date startDate; // Data de início do aluguel

    @Temporal(TemporalType.DATE) // Indica que o campo é uma data (sem tempo)
    private Date endDate; // Data de término do aluguel

    private String pickupLocation; // Local de onde o cliente vai pegar o carro

    private double totalAmount; // Valor total do aluguel calculado com base na duração
}
