package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel,Long> {
    Optional<ClientModel> findByCpf(String cpf);
}
