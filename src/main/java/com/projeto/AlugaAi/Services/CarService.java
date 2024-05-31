package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Exeptions.NoImagesProvidedException;
import com.projeto.AlugaAi.Models.Car;
import com.projeto.AlugaAi.Models.CarModel;
import com.projeto.AlugaAi.Models.ClientModel;
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

@Service
public class CarService {

    private final CarRepository carRepository;
    private final FileStorageService fileStorageService;
    private final CarModelRepository carModelRepository;

    @Autowired
    public CarService(CarRepository carRepository, FileStorageService fileStorageService, CarModelRepository carModelRepository) {
        this.carRepository = carRepository;
        this.fileStorageService = fileStorageService;
        this.carModelRepository = carModelRepository;
    }

    public Car saveCar(Car car, MultipartFile[] files, ClientModel client) {
        if (files == null || files.length == 0) {
            throw new NoImagesProvidedException("Pelo menos uma imagem deve ser fornecida.");
        }

        Set<String> imagens = new HashSet<>();
        for (MultipartFile file : files) {
            String filename = fileStorageService.store(file);
            imagens.add(filename);
        }
        car.setImagens(imagens);

        if (car.getCarModel() == null || car.getCarModel().getId() == null) {
            throw new IllegalArgumentException("CarModel ID must be provided.");
        }

        // Busca o CarModel no banco de dados
        CarModel carModel = carModelRepository.findById(car.getCarModel().getId())
                .orElseThrow(() -> new IllegalArgumentException("CarModel not found with id: " + car.getCarModel().getId()));

        car.setCarModel(carModel); // Define o CarModel associado ao Car

        // Adiciona o cliente ao carro
        car.setClient(client);

        return carRepository.save(car); // Salva o carro com as imagens no repositório
    }

    public List<Car> findAllCarsWithImages() {
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            car.setImagens(car.getImagens());
        }
        return cars;
    }

    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> findAllCars() {
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            if (car.getImagens() == null) {
                car.setImagens(new HashSet<>());
            } else {
                Set<String> filteredImages = new HashSet<>();
                for (String imagem : car.getImagens()) {
                    if (fileStorageService.exists(imagem)) {
                        filteredImages.add(imagem);
                    }
                }
                car.setImagens(filteredImages);
            }
        }
        return cars;
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
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
                    return carRepository.save(car);
                })
                .orElseGet(() -> {
                    updatedCar.setId(id);
                    return carRepository.save(updatedCar);
                });
    }
}
