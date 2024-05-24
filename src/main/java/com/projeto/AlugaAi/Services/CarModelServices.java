package com.projeto.AlugaAi.Services;

import com.projeto.AlugaAi.Models.CarModel;
import com.projeto.AlugaAi.Repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Indica que esta classe é um serviço gerenciado pelo Spring
public class CarModelServices {

    private final CarModelRepository carModelRepository;  // Repositório para operações com CarModel

    @Autowired  // Injeção de dependências através do construtor
    public CarModelServices(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    // Método para criar um novo CarModel
    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);  // Salva o CarModel no banco de dados
    }

    // Método para buscar um CarModel pelo ID
    public Optional<CarModel> getCarModelById(Long id) {
        return carModelRepository.findById(id);  // Busca um CarModel pelo ID no banco de dados
    }

    // Método para buscar todos os CarModels
    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();  // Busca todos os CarModels no banco de dados
    }

    // Método para atualizar um CarModel pelo ID
    public CarModel updateCarModel(Long id, CarModel updatedCarModel) {
        Optional<CarModel> optionalCarModel = carModelRepository.findById(id);  // Busca um CarModel pelo ID no banco de dados
        if (optionalCarModel.isPresent()) {  // Verifica se o CarModel foi encontrado
            CarModel carModel = optionalCarModel.get();  // Obtém o CarModel encontrado
            // Atualiza os campos do CarModel com os valores fornecidos pelo CarModel atualizado
            if (updatedCarModel.getName() != null) {
                carModel.setName(updatedCarModel.getName());
            }
            if (updatedCarModel.getBrand() != null) {
                carModel.setBrand(updatedCarModel.getBrand());
            }
            return carModelRepository.save(carModel);  // Salva o CarModel atualizado no banco de dados
        } else {
            throw new IllegalArgumentException("CarModel not found with id: " + id);  // Lança exceção se o CarModel não for encontrado
        }
    }

    // Método para deletar um CarModel pelo ID
    public void deleteCarModel(Long id) {
        carModelRepository.deleteById(id);  // Exclui um CarModel do banco de dados pelo ID
    }
}
