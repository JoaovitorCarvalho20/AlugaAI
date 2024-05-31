package com.projeto.AlugaAi.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> imagens = new HashSet<>();

    @OneToMany(mappedBy = "car")
    private List<RentalModel> rentals;
}
