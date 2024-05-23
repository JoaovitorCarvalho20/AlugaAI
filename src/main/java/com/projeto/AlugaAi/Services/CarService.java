package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Repository.CarRepository;
import com.projeto.AlugaAi.Util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService {

    private final CarRepository carRepository; // Declara uma instância de CarRepository
    private final FileStorageService fileStorageService; // Declara uma instância de FileStorageService

    @Autowired // Injeta as dependências através do construtor
    public CarService(CarRepository carRepository, FileStorageService fileStorageService) {
        this.carRepository = carRepository; // Inicializa a instância de CarRepository através do construtor
        this.fileStorageService = fileStorageService; // Inicializa a instância de FileStorageService através do construtor
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

    // Método para salvar um carro com arquivos de imagem
    public Car saveCar(Car car, MultipartFile[] files) {
        Set<String> imagens = new HashSet<>();
        for (MultipartFile file : files) {
            String filename = fileStorageService.store(file);
            imagens.add(filename);
        }
        car.setImagens(imagens);
        return carRepository.save(car); // Usa a instância injetada carRepository para salvar
    }
}
