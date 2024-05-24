package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.AddressModel;
import com.projeto.AlugaAi.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Método para buscar todos os endereços
    public List<AddressModel> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Método para buscar um endereço pelo ID
    public Optional<AddressModel> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Método para criar um novo endereço
    public AddressModel createAddress(AddressModel address) {
        return addressRepository.save(address);
    }

    // Método para atualizar um endereço
    public Optional<AddressModel> updateAddress(Long id, AddressModel updatedAddress) {
        // Utiliza findById para obter o endereço pelo ID
        return addressRepository.findById(id).map(address -> {
            // Atualiza os dados do endereço com base no endereço atualizado
            address.setStreet(updatedAddress.getStreet());
            address.setNumber(updatedAddress.getNumber());
            address.setComplement(updatedAddress.getComplement());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setCep(updatedAddress.getCep());
            address.setClient(updatedAddress.getClient());
            return addressRepository.save(address); // Salva o endereço atualizado
        });
    }

    // Método para deletar um endereço pelo ID
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true; // Retorna verdadeiro se o endereço foi deletado com sucesso
        } else {
            return false; // Retorna falso se o endereço não foi encontrado
        }
    }
}
