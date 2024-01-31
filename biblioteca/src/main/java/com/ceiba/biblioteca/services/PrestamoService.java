package com.ceiba.biblioteca.services;

import org.springframework.http.ResponseEntity;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;

import java.util.Optional;

//PrestamoService, es una intefaz que define las funciones que posteriormente van a ser ejecutadas en 
//la capa de controladores en el controlador de prestamos

public interface PrestamoService {

    // crea un prestamo y lo guarda en la base de datos por medio de las funciones
    // de la capa repositories
    // por medio del objeto PrestamoRepository

    Prestamo crearPrestamo(Prestamo Prestamo);

    // consuta un prestamo por medio de su id y retorna un prestamo de la base de
    // datos por medio del objeto
    // PrestamoRepository y en caso de existir o null en caso de no exixtir

    Prestamo consultarPrestamo(Integer id);
}
