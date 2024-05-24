package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende JpaRepository para operações de CRUD com AddressModel
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
