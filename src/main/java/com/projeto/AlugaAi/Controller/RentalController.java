package com.projeto.AlugaAi.Controller;

// Importações necessárias
import com.projeto.AlugaAi.Models.RentalModel;
import com.projeto.AlugaAi.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/rentals") // Define o caminho base para as requisições deste controlador
public class RentalController {

    @Autowired // Injeta a dependência do serviço de aluguel
    private RentalService rentalService;

    /**
     * Obtém todos os registros de aluguéis.
     *
     * @return ResponseEntity contendo a lista de todos os aluguéis e o status HTTP 200 (OK).
     */
    @GetMapping // Mapeia requisições HTTP GET para o método
    public ResponseEntity<List<RentalModel>> getAllRentals() {
        List<RentalModel> rentals = rentalService.getAllRentals(); // Chama o serviço para obter todos os aluguéis
        return ResponseEntity.ok(rentals); // Retorna a lista de aluguéis com status HTTP 200 (OK)
    }

    /**
     * Obtém um aluguel específico pelo ID.
     *
     * @param id O ID do aluguel a ser obtido.
     * @return ResponseEntity contendo o aluguel solicitado e o status HTTP 200 (OK) ou status HTTP 404 (Not Found) se o aluguel não for encontrado.
     */
    @GetMapping("/{id}") // Mapeia requisições HTTP GET com um ID para o método
    public ResponseEntity<RentalModel> getRentalById(@PathVariable Long id) {
        Optional<RentalModel> rental = rentalService.getRentalById(id); // Chama o serviço para obter um aluguel pelo ID
        return rental.map(ResponseEntity::ok) // Se o aluguel for encontrado, retorna com status HTTP 200 (OK)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não for encontrado, retorna status HTTP 404 (Not Found)
    }

    /**
     * Cria um novo aluguel.
     *
     * @param rental O objeto RentalModel representando o novo aluguel a ser criado.
     * @return ResponseEntity contendo o aluguel criado e o status HTTP 201 (Created).
     */
    @PostMapping // Mapeia requisições HTTP POST para o método
    public ResponseEntity<RentalModel> createRental(@RequestBody RentalModel rental) {
        RentalModel createdRental = rentalService.createRental(rental); // Chama o serviço para criar um novo aluguel
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental); // Retorna o aluguel criado com status HTTP 201 (Created)
    }

    /**
     * Atualiza um aluguel existente.
     *
     * @param id O ID do aluguel a ser atualizado.
     * @param rental O objeto RentalModel contendo os novos dados do aluguel.
     * @return ResponseEntity contendo o aluguel atualizado e o status HTTP 200 (OK) ou status HTTP 404 (Not Found) se o aluguel não for encontrado.
     */
    @PutMapping("/{id}") // Mapeia requisições HTTP PUT com um ID para o método
    public ResponseEntity<RentalModel> updateRental(@PathVariable Long id, @RequestBody RentalModel rental) {
        Optional<RentalModel> updatedRental = rentalService.updateRental(id, rental); // Chama o serviço para atualizar o aluguel
        return updatedRental.map(ResponseEntity::ok) // Se a atualização for bem-sucedida, retorna com status HTTP 200 (OK)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não for encontrado, retorna status HTTP 404 (Not Found)
    }

    /**
     * Deleta um aluguel específico pelo ID.
     *
     * @param id O ID do aluguel a ser deletado.
     * @return ResponseEntity com status HTTP 204 (No Content) se a deleção for bem-sucedida ou status HTTP 404 (Not Found) se o aluguel não for encontrado.
     */
    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE com um ID para o método
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        boolean deleted = rentalService.deleteRental(id); // Chama o serviço para deletar o aluguel
        return deleted ? ResponseEntity.noContent().build() // Se a deleção for bem-sucedida, retorna status HTTP 204 (No Content)
                : ResponseEntity.notFound().build(); // Se não for encontrado, retorna status HTTP 404 (Not Found)
    }
}
