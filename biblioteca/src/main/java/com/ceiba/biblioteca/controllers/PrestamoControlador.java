package com.ceiba.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.services.PrestamoService;
import com.ceiba.biblioteca.services.dto.PrestamoMapper;
import com.ceiba.biblioteca.services.dto.PrestamoRequestPost;
import com.ceiba.biblioteca.services.dto.PrestamoResponseGet;
import com.ceiba.biblioteca.services.dto.PrestamoResponsePost;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {
    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public PrestamoResponsePost prestarLibro(@Validated @RequestBody PrestamoRequestPost prestamo) {
        return PrestamoMapper.toRSP(prestamoService.crearPrestamo(PrestamoMapper.toEntity(prestamo)));
    }

    @GetMapping("/{id}")
    public PrestamoResponseGet consultarPrestamo(@PathVariable Integer id) {
        return PrestamoMapper.toRSG(prestamoService.consultarPrestamo(id));
    }
}
