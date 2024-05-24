package com.projeto.AlugaAi.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)  // Define que a exceção retorna um status HTTP 400 Bad Request
public class NoImagesProvidedException extends RuntimeException {  // Declaração da classe NoImagesProvidedException, que estende RuntimeException

    public NoImagesProvidedException(String message) {  // Construtor da classe, que recebe uma mensagem de erro
        super(message);  // Chama o construtor da classe RuntimeException com a mensagem recebida
    }
}
