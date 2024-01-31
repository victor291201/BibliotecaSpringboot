package com.ceiba.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;

import lombok.NonNull;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Service
public class PrestamoServiceImp implements PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public Prestamo crearPrestamo(Prestamo prestamo) {
        if (prestamo.getTipoUsuario() != 1 && prestamo.getTipoUsuario() != 2 && prestamo.getTipoUsuario() != 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tipo de usuario no permitido en la biblioteca");
        }
        Prestamo prestamoSend = new Prestamo();
        prestamoSend.setIsbn(prestamo.getIsbn());
        prestamoSend.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        prestamoSend.setTipoUsuario(prestamo.getTipoUsuario());
        Integer dias = 0;
        if (prestamo.getTipoUsuario() == 1) {
            dias = 10;
        } else {
            if (prestamo.getTipoUsuario() == 2) {
                dias = 8;
            } else {
                if (prestamo.getTipoUsuario() == 3) {
                    dias = 7;
                }
            }
        }
        Prestamo prestamoInvitado = prestamoRepository
                .findFirstByIdentificacionUsuarioAndFechaEntregaIsAfter(
                        prestamoSend.getIdentificacionUsuario(), LocalDate.now())
                .orElse(null);
        if ((prestamo.getTipoUsuario() == 3 && prestamoInvitado == null) || prestamo.getTipoUsuario() != 3) {
            LocalDate fecha = LocalDate.now();
            int diasAgregados = 0;
            while (diasAgregados < dias) {
                fecha = fecha.plusDays(1);
                if (!(fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                        || fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                    diasAgregados++;
                }
            }
            prestamoSend.setFechaEntrega(fecha);

            Prestamo prestamoResponse = prestamoRepository.save(prestamoSend);
            return prestamoResponse;

        } else {
            String mensaje = "El usuario con identificación " + prestamo.getIdentificacionUsuario()
                    + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    mensaje);
        }
    }

    @Override
    public Prestamo consultarPrestamo(Integer id) {
        Prestamo prestamo = prestamoRepository.findById(id).get();
        return prestamo;
    }
}
