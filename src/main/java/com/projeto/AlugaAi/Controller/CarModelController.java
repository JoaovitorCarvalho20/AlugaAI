package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.CarModel;
import com.projeto.AlugaAi.Services.CarModelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carmodels")
public class CarModelController {

    private final CarModelServices carModelServices;

    // Injeção de dependência do serviço CarModelServices via construtor
    @Autowired
    public CarModelController(CarModelServices carModelServices) {
        this.carModelServices = carModelServices;
    }

    // Endpoint para criar um novo modelo de carro
    @PostMapping
    public ResponseEntity<CarModel> createCarModel(@RequestBody CarModel carModel) {
        CarModel createdCarModel = carModelServices.createCarModel(carModel);
        return ResponseEntity.ok(createdCarModel); // Retorna 200 OK com o modelo de carro criado
    }

    // Endpoint para obter um modelo de carro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarModelById(@PathVariable Long id) {
        Optional<CarModel> carModel = carModelServices.getCarModelById(id);
        return carModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        // Retorna 200 OK com o modelo de carro se encontrado, senão 404 Not Found
    }

    // Endpoint para obter todos os modelos de carro
    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        List<CarModel> carModels = carModelServices.getAllCarModels();
        return ResponseEntity.ok(carModels); // Retorna 200 OK com a lista de modelos de carro
    }

    // Endpoint para atualizar um modelo de carro existente pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarModel(@PathVariable Long id, @RequestBody CarModel updatedCarModel) {
        try {
            CarModel updatedModel = carModelServices.updateCarModel(id, updatedCarModel);
            return ResponseEntity.ok(updatedModel); // Retorna 200 OK com o modelo de carro atualizado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o modelo não for encontrado
        }
    }

    // Endpoint para deletar um modelo de carro existente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        try {
            carModelServices.deleteCarModel(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se o modelo foi deletado com sucesso
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o modelo não for encontrado
        }
    }
}
