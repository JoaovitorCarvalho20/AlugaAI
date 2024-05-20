package com.projeto.AlugaAi.Services; // Define o pacote onde esta classe está localizada

import com.projeto.AlugaAi.Models.Car; // Importa a classe Car do pacote Models
import com.projeto.AlugaAi.Repository.CarRepository; // Importa a interface CarRepository do pacote Repository
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired para injeção de dependência
import org.springframework.stereotype.Service; // Importa a anotação Service para marcar esta classe como um serviço

import java.util.List; // Importa a classe List para manipular listas
import java.util.Optional; // Importa a classe Optional para manipular valores que podem estar ausentes

@Service // Marca a classe como um serviço do Spring
public class CarService {

    private final CarRepository carRepository; // Declara uma instância de CarRepository

    @Autowired // Injeta a dependência do CarRepository automaticamente
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository; // Inicializa a instância de CarRepository através do construtor
    }

    // Obtém todos os carros
    public List<Car> findAllCars() {
        return carRepository.findAll(); // Retorna uma lista de todos os Car no repositório
    }

    // Obtém um carro por id
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id); // Retorna um Optional contendo o Car, se encontrado, ou vazio, se não encontrado
    }

    // Salva um novo carro
    public Car saveCar(Car car) {
        return carRepository.save(car); // Salva o Car no repositório e retorna a entidade salva
    }

    // Deleta um carro por id
    public void deleteCar(Long id) {
        carRepository.deleteById(id); // Deleta o Car pelo ID no repositório
    }

    // Atualiza um carro existente ou cria um novo se não existir
    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id) // Tenta encontrar o Car pelo ID
                .map(car -> { // Se encontrado, atualiza os campos necessários
                    car.setYear(updatedCar.getYear());
                    car.setColor(updatedCar.getColor());
                    car.setPlate(updatedCar.getPlate());
                    car.setMileage(updatedCar.getMileage());
                    car.setSecurity(updatedCar.getSecurity());
                    car.setAdditionalDetails(updatedCar.getAdditionalDetails());
                    car.setRenavan(updatedCar.getRenavan());
                    car.setChassis(updatedCar.getChassis());
                    car.setYearOfManufacture(updatedCar.getYearOfManufacture());
                    car.setNumberOfPorts(updatedCar.getNumberOfPorts());
                    car.setAvailable(updatedCar.getAvailable());
                    car.setCarModel(updatedCar.getCarModel());
                    return carRepository.save(car); // Salva o Car atualizado no repositório
                })
                .orElseGet(() -> { // Se não encontrado, cria um novo Car com o ID fornecido
                    updatedCar.setId(id);
                    return carRepository.save(updatedCar); // Salva o novo Car no repositório
                });
    }
}
