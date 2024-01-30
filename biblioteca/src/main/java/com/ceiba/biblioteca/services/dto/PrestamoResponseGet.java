package com.ceiba.biblioteca.services.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponseGet {
    private Integer id;
    private String isbn;
    private String identificaciónUsuario;
    private int tipoUsuario;
    private LocalDate fechaMaximaDevolucion;
}