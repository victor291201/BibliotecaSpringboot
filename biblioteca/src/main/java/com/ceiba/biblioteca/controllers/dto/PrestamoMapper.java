package com.ceiba.biblioteca.controllers.dto;

import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.entities.Prestamo;

@Component
public final class PrestamoMapper {

    public static PrestamoResponsePost toRSP(Prestamo data) {
        PrestamoResponsePost dataDto = new PrestamoResponsePost();
        dataDto.setId(data.getId());
        dataDto.setFechaMaximaDevolucion(data.getFechaEntrega());
        return dataDto;
    }

    public static PrestamoResponseGet toRSG(Prestamo data) {
        PrestamoResponseGet dataDto = new PrestamoResponseGet();
        dataDto.setId(data.getId());
        dataDto.setIsbn(data.getIsbn());
        dataDto.setIdentificacionUsuario(data.getIdentificacionUsuario());
        dataDto.setTipoUsuario(data.getTipoUsuario());
        dataDto.setFechaMaximaDevolucion(data.getFechaEntrega());
        return dataDto;
    }

    public static Prestamo toEntity(PrestamoRequestPost dataDto) {
        Prestamo data = new Prestamo();
        data.setIsbn(dataDto.getIsbn());
        data.setIdentificacionUsuario(dataDto.getIdentificacionUsuario());
        data.setTipoUsuario(dataDto.getTipoUsuario());
        return data;
    }
}
