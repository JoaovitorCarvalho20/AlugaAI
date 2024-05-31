package com.projeto.AlugaAi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;
    private String color;
    private String plate;
    private Integer mileage;
    private Boolean security;
    private String additionalDetails;
    private String renavan;
    private String chassis;
    private Integer yearOfManufacture;
    private Integer numberOfPorts;
    private Boolean available;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> imagens;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @JsonIgnore // Evita a serialização recursiva
    private List<RentalModel> rentals;

}
