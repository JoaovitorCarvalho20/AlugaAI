package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Exeptions.NoImagesProvidedException;
import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Endpoint para adicionar um novo carro com imagens
    @PostMapping("/add-with-images")
    public ResponseEntity<?> addCarWithImages(@RequestBody Car car, @RequestParam("files") MultipartFile[] files) {
        try {
            Car savedCar = carService.saveCar(car, files);
            return ResponseEntity.ok(savedCar);
        } catch (NoImagesProvidedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }

    // Endpoint para buscar todos os carros
    @GetMapping
    public List<Car> getAllCars() {
        return carService.findAllCars();
    }

    // Endpoint para buscar um carro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.findCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para adicionar um novo carro
    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    // Endpoint para atualizar um carro existente pelo ID
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    // Endpoint para deletar um carro pelo ID
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
