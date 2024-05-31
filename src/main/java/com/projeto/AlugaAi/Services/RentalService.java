package com.projeto.AlugaAi.Services;

// Importações necessárias
import com.projeto.AlugaAi.Models.RentalModel;
import com.projeto.AlugaAi.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service // Anotação para indicar que esta classe é um serviço do Spring
public class RentalService {

    @Autowired // Injeta automaticamente uma instância de RentalRepository
    private RentalRepository rentalRepository;

    // Método para obter todos os aluguéis
    public List<RentalModel> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Método para obter um aluguel pelo seu ID
    public Optional<RentalModel> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    // Método para criar um novo aluguel
    public RentalModel createRental(RentalModel rental) {
        // Calcula o valor total com base na duração do aluguel
        rental.setTotalAmount(calculateTotalAmount(rental));
        // Salva o aluguel no repositório
        return rentalRepository.save(rental);
    }

    // Método para atualizar um aluguel existente
    public Optional<RentalModel> updateRental(Long id, RentalModel updatedRental) {
        // Procura o aluguel pelo ID e, se encontrado, atualiza os dados
        return rentalRepository.findById(id).map(rental -> {
            rental.setCar(updatedRental.getCar());
            rental.setStartDate(updatedRental.getStartDate());
            rental.setEndDate(updatedRental.getEndDate());
            rental.setPickupLocation(updatedRental.getPickupLocation());
            // Recalcula o valor total se a duração do aluguel for alterada
            if (!rental.getStartDate().equals(updatedRental.getStartDate())
                    || !rental.getEndDate().equals(updatedRental.getEndDate())) {
                rental.setTotalAmount(calculateTotalAmount(updatedRental));
            } else {
                rental.setTotalAmount(updatedRental.getTotalAmount());
            }
            // Salva o aluguel atualizado no repositório
            return rentalRepository.save(rental);
        });
    }


    // Método para excluir um aluguel pelo seu ID
    public boolean deleteRental(Long id) {
        if (rentalRepository.existsById(id)) {
            // Se o aluguel existir, ele é excluído
            rentalRepository.deleteById(id);
            return true;
        } else {
            // Se o aluguel não existir, retorna falso
            return false;
        }
    }

    // Método privado para calcular o valor total do aluguel
    private double calculateTotalAmount(RentalModel rental) {
        Date startDate = rental.getStartDate(); // Obtém a data de início do aluguel
        Date endDate = rental.getEndDate(); // Obtém a data de término do aluguel

        // Converte java.util.Date para java.time.LocalDate
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calcula o número de dias entre as duas datas
        long days = ChronoUnit.DAYS.between(localStartDate, localEndDate);
        // Define a taxa diária do aluguel
        double dailyRate = 50.0;
        // Calcula o valor total multiplicando os dias pela taxa diária
        return days * dailyRate;
    }
}
