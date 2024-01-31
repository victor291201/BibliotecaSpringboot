package com.ceiba.biblioteca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//entidad prestamo es una clase que sirve como representacion de la tabla prestamos de la base de datos en un objeto 
//lo cual nos permite manipular los datos de la tabla con mayor facilidad

@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "isbn", length = 10)
    private String isbn;

    @Column(name = "identificacionUsuario", length = 10)
    private String identificacionUsuario;

    @Column(name = "tipoUsuario", length = 1)
    private int tipoUsuario;

    @Column(name = "fechaEntrega")
    private LocalDate fechaEntrega;

}