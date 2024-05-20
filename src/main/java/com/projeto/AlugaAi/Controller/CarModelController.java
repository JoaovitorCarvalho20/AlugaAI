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

    @Autowired
    public CarModelController(CarModelServices carModelServices) {
        this.carModelServices = carModelServices;
    }

    @PostMapping
    public ResponseEntity<CarModel> createCarModel(@RequestBody CarModel carModel) {
        CarModel createdCarModel = carModelServices.createCarModel(carModel);
        return ResponseEntity.ok(createdCarModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarModelById(@PathVariable Long id) {
        Optional<CarModel> carModel = carModelServices.getCarModelById(id);
        return carModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        List<CarModel> carModels = carModelServices.getAllCarModels();
        return ResponseEntity.ok(carModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarModel(@PathVariable Long id, @RequestBody CarModel updatedCarModel) {
        try {
            CarModel updatedModel = carModelServices.updateCarModel(id, updatedCarModel);
            return ResponseEntity.ok(updatedModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        try {
            carModelServices.deleteCarModel(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
