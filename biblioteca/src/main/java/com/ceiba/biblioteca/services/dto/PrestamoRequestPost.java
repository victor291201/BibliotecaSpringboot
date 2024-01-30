package com.ceiba.biblioteca.services.dto;

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
    private String identificaciónUsuario;
    private int tipoUsuario;
}
