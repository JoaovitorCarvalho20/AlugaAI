package com.projeto.AlugaAi.Repository;

import com.projeto.AlugaAi.Models.ClientModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// Interface que estende JpaRepository para operações de CRUD com ClientModel
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    // Método para encontrar um cliente pelo CPF
    Optional<ClientModel> findByCpf(String cpf);

    // Método para verificar se já existe um cliente com o CPF informado
    boolean existsByCpf(String cpf);

    // Método personalizado para encontrar um cliente pelo ID junto com seus endereços
    @EntityGraph(attributePaths = "addresses") // Define um grafo de entidade para carregar também os endereços
    @Query("SELECT c FROM ClientModel c JOIN FETCH c.addresses WHERE c.id = :clientId")
    Optional<ClientModel> findByIdWithAddresses(@Param("clientId") Long clientId);
}
