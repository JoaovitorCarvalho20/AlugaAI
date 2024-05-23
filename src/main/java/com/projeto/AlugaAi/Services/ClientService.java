package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.ClientModel;
import com.projeto.AlugaAi.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Anotação do Spring para marcar esta classe como um serviço
public class ClientService {

    @Autowired  // Injeta uma instância do ClientRepository
    private ClientRepository clientRepository;

    // Método para listar todos os clientes
    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    // Método para buscar um cliente por ID
    public Optional<ClientModel> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Método para buscar um cliente por CPF
    public Optional<ClientModel> getClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    // Método para criar um novo cliente
    public ClientModel createClient(ClientModel client) {
        return clientRepository.save(client);
    }

    // Método para atualizar um cliente existente
    public Optional<ClientModel> updateClient(Long id, ClientModel updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setName(updatedClient.getName());
            client.setLastName(updatedClient.getLastName());
            client.setPhone(updatedClient.getPhone());
            client.setCpf(updatedClient.getCpf());
            client.setCnh(updatedClient.getCnh());
            client.setYearOfBirth(updatedClient.getYearOfBirth());
            client.setEmail(updatedClient.getEmail());
            client.setPassword(updatedClient.getPassword());
            client.setClientType(updatedClient.getClientType());
            // Atualize outros campos conforme necessário
            return clientRepository.save(client);
        });
    }

    // Método para deletar um cliente por ID
    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
