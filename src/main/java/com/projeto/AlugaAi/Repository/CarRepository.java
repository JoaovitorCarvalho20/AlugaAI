package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @EntityGraph(attributePaths = {"carModel"})
    Optional<Car> findById(Long id);
}
