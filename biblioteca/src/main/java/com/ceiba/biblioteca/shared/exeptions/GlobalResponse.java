package com.ceiba.biblioteca.shared.exeptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
// GlobalResponse400 es una clase que le proporcionamos a sprginboot con la
// configuracion del manejador para lals peticiones de tipo 400
public class GlobalResponse {
    // declaramos el manejador para las respuestas de tipo .BAD_REQUEST o 400 el
    // cual retorna un objeto ResponseEntity con un objeto ErrorResponse en su
    // cuerpo
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleException(ResponseStatusException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getReason());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

}
