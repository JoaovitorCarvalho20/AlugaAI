package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.AddressModel;
import com.projeto.AlugaAi.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Endpoint para obter todos os endereços
    @GetMapping
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Endpoint para obter um endereço pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        Optional<AddressModel> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o endereço se encontrado, ou 404 Not Found se não encontrado
    }

    // Endpoint para criar um novo endereço
    @PostMapping
    public AddressModel createAddress(@RequestBody AddressModel address) {
        return addressService.createAddress(address);
    }

    // Endpoint para atualizar um endereço pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long id, @RequestBody AddressModel updatedAddress) {
        Optional<AddressModel> address = addressService.updateAddress(id, updatedAddress);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // Retorna o endereço atualizado se encontrado, ou 404 Not Found se não encontrado
    }

    // Endpoint para deletar um endereço pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (addressService.deleteAddress(id)) {
            return ResponseEntity.noContent().build(); // Retorna status 204 No Content se o endereço foi deletado com sucesso
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o endereço não foi encontrado
        }
    }
}
