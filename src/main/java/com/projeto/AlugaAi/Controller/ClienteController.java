package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.ClientModel;
import com.projeto.AlugaAi.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  // Anotação do Spring para marcar esta classe como um controller REST
@RequestMapping("/api/clientes")  // Define a URL base para todos os endpoints deste controller
public class ClienteController {

    @Autowired  // Injeta uma instância do ClientService
    private ClientService clientService;

    // Endpoint para listar todos os clientes
    @GetMapping
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    // Endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable Long id) {
        Optional<ClientModel> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para buscar um cliente por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientModel> getClientByCpf(@PathVariable String cpf) {
        Optional<ClientModel> client = clientService.getClientByCpf(cpf);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo cliente
    @PostMapping
    public ClientModel createClient(@RequestBody ClientModel client) {
        return clientService.createClient(client);
    }

    // Endpoint para atualizar um cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable Long id, @RequestBody ClientModel updatedClient) {
        Optional<ClientModel> client = clientService.updateClient(id, updatedClient);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para deletar um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
