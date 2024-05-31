package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalModel, Long> {
}
