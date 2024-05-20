package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel , Long> {
}
