package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.ClientModel;
import com.projeto.AlugaAi.Repository.AddressRepository;
import com.projeto.AlugaAi.Repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    // Método para buscar todos os clientes
    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    // Método para criar um novo cliente
    public ClientModel createClient(ClientModel client) {
        // Verifica se já existe um cliente com o mesmo CPF
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new DataIntegrityViolationException("CPF already exists: " + client.getCpf());
        }
        // Salva o novo cliente no banco de dados
        return clientRepository.save(client);
    }

    // Método para buscar um cliente pelo ID
    public Optional<ClientModel> getClientById(Long id) {
        // Utilizando o método personalizado para carregar os endereços junto com o cliente
        return clientRepository.findByIdWithAddresses(id);
    }

    // Método para buscar um cliente pelo CPF
    public Optional<ClientModel> getClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    // Método para atualizar um cliente
    public Optional<ClientModel> updateClient(Long id, ClientModel updatedClient) {
        // Utilizando findByIdWithAddresses para garantir que os endereços sejam carregados junto com o cliente
        return clientRepository.findByIdWithAddresses(id).map(client -> {
            // Atualizando os dados do cliente com base no cliente atualizado
            client.setName(updatedClient.getName());
            client.setLastName(updatedClient.getLastName());
            client.setPhone(updatedClient.getPhone());
            client.setCpf(updatedClient.getCpf());
            client.setCnh(updatedClient.getCnh());
            client.setYearOfBirth(updatedClient.getYearOfBirth());
            client.setEmail(updatedClient.getEmail());
            client.setPassword(updatedClient.getPassword());
            client.setClientType(updatedClient.getClientType());
            client.setAddresses(updatedClient.getAddresses());
            return clientRepository.save(client); // Salvando o cliente atualizado
        });
    }

    // Método para deletar um cliente pelo ID
    public boolean deleteClient(Long id) {
        Optional<ClientModel> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            ClientModel client = optionalClient.get();

            // Exclui todos os endereços associados ao cliente
            addressRepository.deleteByClientId(id);

            // Agora é seguro excluir o cliente
            clientRepository.delete(client);

            return true; // Retorna verdadeiro se o cliente foi deletado com sucesso
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
        }
    }
}
