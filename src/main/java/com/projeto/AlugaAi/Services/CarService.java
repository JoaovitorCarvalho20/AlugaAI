package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Exeptions.NoImagesProvidedException;
import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Models.CarModel;
import com.projeto.AlugaAi.Repository.CarModelRepository;
import com.projeto.AlugaAi.Repository.CarRepository;
import com.projeto.AlugaAi.Util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service  // Indica que esta classe é um serviço gerenciado pelo Spring
public class CarService {

    private final CarRepository carRepository;  // Repositório para operações com Carros
    private final FileStorageService fileStorageService;  // Serviço para armazenamento de arquivos
    private final CarModelRepository carModelRepository;  // Repositório para operações com CarModels

    @Autowired  // Injeção de dependências através do construtor
    public CarService(CarRepository carRepository, FileStorageService fileStorageService, CarModelRepository carModelRepository) {
        this.carRepository = carRepository;
        this.fileStorageService = fileStorageService;
        this.carModelRepository = carModelRepository;
    }

    // Método para salvar um carro com imagens
    public Car saveCar(Car car, MultipartFile[] files) {
        // Verifica se foram fornecidos arquivos de imagens
        if (files == null || files.length == 0) {
            throw new NoImagesProvidedException("Pelo menos uma imagem deve ser fornecida.");
        }

        Set<String> imagens = new HashSet<>();
        for (MultipartFile file : files) {
            String filename = fileStorageService.store(file);  // Armazena o arquivo de imagem
            imagens.add(filename);  // Adiciona o nome do arquivo ao conjunto de imagens do carro
        }
        car.setImagens(imagens); // Define as imagens no objeto Car

        // Verifica se o CarModel já está definido
        if (car.getCarModel() == null || car.getCarModel().getId() == null) {
            throw new IllegalArgumentException("CarModel ID must be provided.");
        }

        // Busca o CarModel no banco de dados
        CarModel carModel = carModelRepository.findById(car.getCarModel().getId())
                .orElseThrow(() -> new IllegalArgumentException("CarModel not found with id: " + car.getCarModel().getId()));

        car.setCarModel(carModel); // Define o CarModel associado ao Car

        return carRepository.save(car); // Salva o carro com as imagens no repositório
    }

    // Método para buscar todos os carros com suas imagens
    public List<Car> findAllCarsWithImages() {
        List<Car> cars = carRepository.findAll();  // Busca todos os carros no banco de dados
        for (Car car : cars) {
            // Aqui você pode carregar as imagens do carro se necessário
            // Normalmente, carregar as imagens seria feito sob demanda para otimizar o desempenho
             car.setImagens(car.getImagens()); // Carregar imagens, se necessário
        }
        return cars;  // Retorna a lista de carros com imagens (ou sem)
    }

    // Método para buscar um carro pelo ID
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);  // Busca um carro pelo ID no banco de dados
    }

    // Método para buscar todos os carros
    public List<Car> findAllCars() {
        List<Car> cars = carRepository.findAll();  // Busca todos os carros no banco de dados
        for (Car car : cars) {
            if (car.getImagens() == null) {
                car.setImagens(new HashSet<>()); // Inicializa imagens como um conjunto vazio se for nulo
            } else {
                // Filtra as imagens para manter apenas as que existem no armazenamento de arquivos
                Set<String> filteredImages = new HashSet<>();
                for (String imagem : car.getImagens()) {
                    if (fileStorageService.exists(imagem)) {
                        filteredImages.add(imagem);
                    }
                }
                car.setImagens(filteredImages);
            }
        }
        return cars;  // Retorna a lista de carros com imagens filtradas
    }

    // Método para salvar um carro sem imagens
    public Car saveCar(Car car) {
        return carRepository.save(car);  // Salva um carro sem imagens no banco de dados
    }

    // Método para deletar um carro pelo ID
    public void deleteCar(Long id) {
        carRepository.deleteById(id);  // Exclui um carro do banco de dados pelo ID
    }

    // Método para atualizar um carro pelo ID
    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id)  // Busca um carro pelo ID no banco de dados
                .map(car -> {
                    // Atualiza os campos do carro com os valores do carro atualizado
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
                    return carRepository.save(car);  // Salva o carro atualizado no banco de dados
                })
                .orElseGet(() -> {
                    updatedCar.setId(id);  // Define o ID do carro atualizado
                    return carRepository.save(updatedCar);  // Salva o carro atualizado no banco de dados
                });
    }
}
