package com.ceiba.biblioteca.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequestPost {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}
