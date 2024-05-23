package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.AddressModel;
import com.projeto.AlugaAi.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  // Anotação do Spring para marcar esta classe como um controller REST
@RequestMapping("/api/addresses")  // Define a URL base para todos os endpoints deste controller
public class AddressController {

    @Autowired  // Injeta uma instância do AddressService
    private AddressService addressService;

    // Endpoint para listar todos os endereços
    @GetMapping
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Endpoint para buscar um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        Optional<AddressModel> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo endereço
    @PostMapping
    public AddressModel createAddress(@RequestBody AddressModel address) {
        return addressService.createAddress(address);
    }

    // Endpoint para atualizar um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long id, @RequestBody AddressModel updatedAddress) {
        Optional<AddressModel> address = addressService.updateAddress(id, updatedAddress);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para deletar um endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (addressService.deleteAddress(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
