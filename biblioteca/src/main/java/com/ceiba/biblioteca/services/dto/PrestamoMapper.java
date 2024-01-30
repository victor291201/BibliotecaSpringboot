package com.ceiba.biblioteca.services.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;

@Component
public final class PrestamoMapper {

    private static PrestamoRepository prestamoRepository;

    public static PrestamoResponsePost toRSP(Prestamo data) {
        PrestamoResponsePost dataDto = new PrestamoResponsePost();
        dataDto.setId(data.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = formatter.format(data.getFechaEntrega());

        System.out.println("Fecha formateada: " + fechaFormateada); // "08/02/2024"

        dataDto.setFechaMaximaDevolucion(fechaFormateada);
        return dataDto;
    }

    public static PrestamoResponseGet toRSG(Prestamo data) {
        PrestamoResponseGet dataDto = new PrestamoResponseGet();
        dataDto.setId(data.getId());
        dataDto.setIsbn(data.getIsbn());
        dataDto.setIdentificaciónUsuario(data.getIdentificacionUsuario());
        dataDto.setTipoUsuario(data.getTipoUsuario());
        dataDto.setFechaMaximaDevolucion(data.getFechaEntrega());
        return dataDto;
    }

    public static Prestamo toEntity(PrestamoRequestPost dataDto) {
        Prestamo data = new Prestamo();
        data.setIsbn(dataDto.getIsbn());
        data.setIdentificacionUsuario(dataDto.getIdentificaciónUsuario());
        data.setTipoUsuario(dataDto.getTipoUsuario());
        System.out.println("---------------------------------");
        System.out.println("converti a entidad" + dataDto.getIsbn());
        System.out.println("---------------------------------");
        return data;
    }
}
