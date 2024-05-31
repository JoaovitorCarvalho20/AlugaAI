package com.projeto.AlugaAi.Controller;

import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    // Injeção de dependência do serviço CarService via construtor
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
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
                .map(ResponseEntity::ok) // Retorna 200 OK com o carro se encontrado
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 Not Found se não encontrado
    }

    // Endpoint para adicionar um novo carro
    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car); // Salva o novo carro e retorna o objeto salvo
    }

    // Endpoint para atualizar um carro existente pelo ID
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car); // Atualiza o carro com o ID especificado e retorna o objeto atualizado
    }

    // Endpoint para deletar um carro pelo ID
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id); // Deleta o carro com o ID especificado
    }

    // Endpoint para adicionar um novo carro com imagens
    @PostMapping("/add-with-images")
    public Car addCarWithImages(@ModelAttribute Car car,
                                @RequestParam("files") MultipartFile[] files) {
        return carService.saveCar(car, files); // Salva o novo carro com as imagens fornecidas
    }
}
