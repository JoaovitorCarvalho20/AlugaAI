package com.projeto.AlugaAi.Exeptions;

public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException(String message) {
        super(message);
    }

    public MyEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

