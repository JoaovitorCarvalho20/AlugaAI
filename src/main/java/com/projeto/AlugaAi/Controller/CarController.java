package com.projeto.AlugaAi.Controller; // Define o pacote onde esta classe está localizada

import com.projeto.AlugaAi.Models.Car; // Importa a classe Car do pacote Models
import com.projeto.AlugaAi.Services.CarService; // Importa a classe CarService do pacote Services
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired para injeção de dependência
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para manipular respostas HTTP
import org.springframework.web.bind.annotation.*; // Importa as anotações necessárias para definir um controlador REST

import java.util.List; // Importa a classe List para manipular listas

@RestController // Anota a classe como um controlador REST
@RequestMapping("/cars") // Define a rota base para todos os endpoints deste controlador
public class CarController {

    @Autowired // Injeta a dependência do CarService automaticamente
    private CarService carService; // Declara uma instância de CarService

    @GetMapping // Mapeia requisições GET para este método
    public List<Car> getAllCars() {
        return carService.findAllCars(); // Chama o método do serviço para obter todos os carros
    }

    @GetMapping("/{id}") // Mapeia requisições GET com um parâmetro de caminho (id) para este método
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.findCarById(id) // Chama o método do serviço para encontrar um carro pelo ID
                .map(ResponseEntity::ok) // Se encontrado, retorna 200 OK com o carro
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrado, retorna 404 Not Found
    }

    @PostMapping // Mapeia requisições POST para este método
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car); // Chama o método do serviço para salvar um novo carro
    }

    @PutMapping("/{id}") // Mapeia requisições PUT com um parâmetro de caminho (id) para este método
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car); // Chama o método do serviço para atualizar um carro existente
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE com um parâmetro de caminho (id) para este método
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id); // Chama o método do serviço para deletar um carro pelo ID
    }
}
