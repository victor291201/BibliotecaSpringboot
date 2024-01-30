package com.ceiba.biblioteca.services;

import org.springframework.http.ResponseEntity;

import com.ceiba.biblioteca.entities.Prestamo;

import java.util.Optional;

public interface PrestamoService {

    Prestamo crearPrestamo(Prestamo Prestamo);

    Prestamo consultarPrestamo(Integer id);
}
