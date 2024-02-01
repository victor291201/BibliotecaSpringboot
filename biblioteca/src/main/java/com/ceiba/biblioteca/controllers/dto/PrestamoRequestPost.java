package com.ceiba.biblioteca.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// PrestamoRequestPost es una clase que configuramos para poder manejar los
// datos que recibiran las peticiones post en su cuerpo
public class PrestamoRequestPost {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}
