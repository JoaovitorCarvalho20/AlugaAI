package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.CarModel;
import com.projeto.AlugaAi.Repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServices {

    private final CarModelRepository carModelRepository;

    @Autowired
    public CarModelServices(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public Optional<CarModel> getCarModelById(Long id) {
        return carModelRepository.findById(id);
    }

    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    public CarModel updateCarModel(Long id, CarModel updatedCarModel) {
        Optional<CarModel> optionalCarModel = carModelRepository.findById(id);
        if (optionalCarModel.isPresent()) {
            CarModel carModel = optionalCarModel.get();
            if (updatedCarModel.getName() != null) {
                carModel.setName(updatedCarModel.getName());
            }
            if (updatedCarModel.getBrand() != null) {
                carModel.setBrand(updatedCarModel.getBrand());
            }
            return carModelRepository.save(carModel);
        } else {
            throw new IllegalArgumentException("CarModel not found with id: " + id);
        }
    }


    public void deleteCarModel(Long id) {
        carModelRepository.deleteById(id);
    }
}
