package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.ClientModel;
import com.projeto.AlugaAi.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientService clientService;

    // Endpoint para obter todos os clientes
    @GetMapping
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientModel client) {
        try {
            ClientModel newClient = clientService.createClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED); // Retorna o cliente criado com status 201 Created
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // Retorna mensagem de erro em caso de conflito de dados
        }
    }

    // Endpoint para obter um cliente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable Long id) {
        Optional<ClientModel> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o cliente se encontrado, ou 404 Not Found se não encontrado
    }

    // Endpoint para obter um cliente pelo CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientModel> getClientByCpf(@PathVariable String cpf) {
        Optional<ClientModel> client = clientService.getClientByCpf(cpf);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o cliente se encontrado, ou 404 Not Found se não encontrado
    }

    // Endpoint para atualizar um cliente pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable Long id, @RequestBody ClientModel updatedClient) {
        Optional<ClientModel> client = clientService.updateClient(id, updatedClient);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o cliente atualizado se encontrado, ou 404 Not Found se não encontrado
    }

    // Endpoint para deletar um cliente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return ResponseEntity.noContent().build(); // Retorna status 204 No Content se o cliente foi deletado com sucesso
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o cliente não foi encontrado
        }
    }
}
