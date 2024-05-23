package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel,Long> {
}
