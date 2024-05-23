package com.projeto.AlugaAi.Models; // Define o pacote onde esta classe está localizada

import jakarta.persistence.*; // Importa as anotações e classes necessárias para JPA (Java Persistence API)
import java.util.Set;
import java.util.List; // Importa a classe List para manipular listas

import lombok.Data; // Importa a anotação @Data do Lombok, que gera automaticamente getters, setters, toString, equals, hashCode, etc.
import lombok.NoArgsConstructor; // Importa a anotação @NoArgsConstructor do Lombok, que gera um construtor sem argumentos
import lombok.AllArgsConstructor; // Importa a anotação @AllArgsConstructor do Lombok, que gera um construtor com todos os argumentos

@Data // Gera automaticamente getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Entity // Marca a classe como uma entidade JPA
@Table(name = "cars") // Define o nome da tabela no banco de dados que esta entidade mapeia
public class Car {
    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração do valor da chave primária
    private Long id; // Identificador único do carro

    private Integer year; // Ano do carro
    private String color; // Cor do carro
    private String plate; // Placa do carro
    private Integer mileage; // Quilometragem do carro
    private Boolean security; // Indica se o carro possui segurança
    private String additionalDetails; // Detalhes adicionais do carro
    private String renavan; // Número do RENAVAM do carro
    private String chassis; // Número do chassi do carro
    private Integer yearOfManufacture; // Ano de fabricação do carro
    private Integer numberOfPorts; // Número de portas do carro
    private Boolean available; // Indica se o carro está disponível para aluguel

    @ElementCollection
    private Set<String> imagens; // Armazena os nomes dos arquivos de imagem

    @ManyToOne // Define um relacionamento Many-to-One (muitos para um) com CarModel
    @JoinColumn(name = "model_id") // Define a coluna de junção no banco de dados que referencia a chave primária da tabela CarModel
    private CarModel carModel; // Referência ao modelo do carro

    // Comentado para evitar mapeamento no momento
    // @OneToMany(mappedBy = "car") // Define um relacionamento One-to-Many (um para muitos) com Rental
    // private List<Rental> rentals; // Lista de aluguéis associados a este carro
}
