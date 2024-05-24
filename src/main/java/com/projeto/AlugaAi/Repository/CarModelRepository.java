package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.AlugaAi.Models.CarModel;

import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModel , Long> {

    Optional<CarModel> findByName(String name);

}
