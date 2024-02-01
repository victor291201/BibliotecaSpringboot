package com.ceiba.biblioteca.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;

@DataJpaTest
public class PrestamoRepositoryTest {

    @Autowired
    PrestamoRepository prestamoRepository;

    private Prestamo dato;

    @BeforeEach
    void setup() {
        dato = new Prestamo();
        dato.setId(1);
        dato.setIsbn("123");
        dato.setIdentificacionUsuario("321");
        dato.setTipoUsuario(1);
        dato.setFechaEntrega(LocalDate.now());
    }

    @DisplayName("crear y retornar un prestamo cuando se envia un prestamo")
    @Test
    public void test1() {
        // given

        // when
        Prestamo datoGuardado = prestamoRepository.save(dato);

        // then
        assertThat(datoGuardado).isNotNull();
        assertThat(datoGuardado).isEqualTo(dato);
    }

    @DisplayName("retornar un prestamo cuando se envia un id")
    @Test
    public void test2() {
        // given
        Prestamo datoGuardado = prestamoRepository.save(dato);

        // when
        Optional<Prestamo> datoRetornado = prestamoRepository.findById(datoGuardado.getId());

        // then
        assertThat(datoRetornado.orElse(null)).isNotNull();
        assertThat(datoRetornado.get()).isEqualTo(datoGuardado);
    }

    @DisplayName("retornar un valor null cuando se envia un id no registrado en la base de datos")
    @Test
    public void test3() {
        // given
        Prestamo datoGuardado = prestamoRepository.save(dato);

        // when
        Optional<Prestamo> datoRetornado = prestamoRepository.findById(2);

        // then
        assertThat(datoRetornado.orElse(null)).isNull();
    }

}
