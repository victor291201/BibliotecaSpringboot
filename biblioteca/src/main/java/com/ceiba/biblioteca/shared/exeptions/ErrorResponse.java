package com.ceiba.biblioteca.shared.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// ErrorResponse es un objeto que tiene un mensaje como atributo
public final class ErrorResponse {
    // definimos los atributos
    private final String mensaje;
}
