package com.ceiba.biblioteca.controllers.dto;

import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.entities.Prestamo;

@Component

// PrestamoMapper es una clase que configuramos para poder convertir los objetos
// entre Prestamo PrestamoResponsePost,PrestamoResponseGet y PrestamoRequestGet
public final class PrestamoMapper {
    // el metodo toRSP convierte un dato Prestamo a PrestamoResponsePost
    public static PrestamoResponsePost toRSP(Prestamo data) {
        PrestamoResponsePost dataDto = new PrestamoResponsePost();
        dataDto.setId(data.getId());
        dataDto.setFechaMaximaDevolucion(data.getFechaEntrega());
        return dataDto;
    }

    // el metodo toRSG convierte un dato Prestamo a PrestamoResponseGet
    public static PrestamoResponseGet toRSG(Prestamo data) {
        PrestamoResponseGet dataDto = new PrestamoResponseGet();
        dataDto.setId(data.getId());
        dataDto.setIsbn(data.getIsbn());
        dataDto.setIdentificacionUsuario(data.getIdentificacionUsuario());
        dataDto.setTipoUsuario(data.getTipoUsuario());
        dataDto.setFechaMaximaDevolucion(data.getFechaEntrega());
        return dataDto;
    }

    // el metodo toEntity convierte un dato PrestamoRequestPost a Prestamo
    public static Prestamo toEntity(PrestamoRequestPost dataDto) {
        Prestamo data = new Prestamo();
        data.setIsbn(dataDto.getIsbn());
        data.setIdentificacionUsuario(dataDto.getIdentificacionUsuario());
        data.setTipoUsuario(dataDto.getTipoUsuario());
        return data;
    }
}
