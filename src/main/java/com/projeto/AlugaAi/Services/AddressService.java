package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.AddressModel;
import com.projeto.AlugaAi.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Anotação do Spring para marcar esta classe como um serviço
public class AddressService {

    @Autowired  // Injeta uma instância do AddressRepository
    private AddressRepository addressRepository;

    // Método para listar todos os endereços
    public List<AddressModel> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Método para buscar um endereço por ID
    public Optional<AddressModel> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Método para criar um novo endereço
    public AddressModel createAddress(AddressModel address) {
        return addressRepository.save(address);
    }

    // Método para atualizar um endereço existente
    public Optional<AddressModel> updateAddress(Long id, AddressModel updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setStreet(updatedAddress.getStreet());
            address.setNumber(updatedAddress.getNumber());
            address.setComplement(updatedAddress.getComplement());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setCep(updatedAddress.getCep());
            address.setClient(updatedAddress.getClient());
            return addressRepository.save(address);
        });
    }

    // Método para deletar um endereço por ID
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
