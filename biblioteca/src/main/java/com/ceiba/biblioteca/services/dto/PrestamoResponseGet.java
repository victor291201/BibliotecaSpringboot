package com.ceiba.biblioteca.services.dto;

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
public class PrestamoResponseGet {
    private Integer id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaMaximaDevolucion;
}
