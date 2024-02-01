package com.ceiba.biblioteca.controllers.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// PrestamoResponsePost es una clase que configuramos para poder manejar los
// datos que nos enviaran las peticiones post en su cuerpo
public class PrestamoResponsePost {
    private Integer id;

    // parseamos la fecha
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaMaximaDevolucion;
}
