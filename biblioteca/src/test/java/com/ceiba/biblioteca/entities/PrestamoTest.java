package com.ceiba.biblioteca.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PrestamoTest {

    private Prestamo dato;

    @BeforeEach
    void setup() {
        dato = new Prestamo();
    }

    @DisplayName("setear y obtener el id")
    @Test
    public void test1() {
        // given

        // when
        dato.setId(1);
        Integer datoRetornado = dato.getId();

        // then
        assertThat(datoRetornado).isNotNull();
        assertThat(datoRetornado).isEqualTo(1);
    }

    @DisplayName("setear y obtener el Isbn")
    @Test
    public void test2() {
        // given

        // when
        dato.setIsbn("123b");
        String datoRetornado = dato.getIsbn();

        // then
        assertThat(datoRetornado).isNotNull();
        assertThat(datoRetornado).isEqualTo("123b");
    }

    @DisplayName("setear y obtener el identificadorUsuario")
    @Test
    public void test3() {
        // given

        // when
        dato.setIdentificacionUsuario("321b");
        String datoRetornado = dato.getIdentificacionUsuario();

        // then
        assertThat(datoRetornado).isNotNull();
        assertThat(datoRetornado).isEqualTo("321b");
    }

    @DisplayName("setear y obtener el tipoUsuario")
    @Test
    public void test4() {
        // given

        // when
        dato.setTipoUsuario(1);
        int datoRetornado = dato.getTipoUsuario();

        // then
        assertThat(datoRetornado).isNotNull();
        assertThat(datoRetornado).isEqualTo(1);
    }

    @DisplayName("setear y obtener el fechaEntrega")
    @Test
    public void test5() {
        // given

        // when
        dato.setFechaEntrega(LocalDate.now());
        LocalDate datoRetornado = dato.getFechaEntrega();

        // then
        assertThat(datoRetornado).isNotNull();
        assertThat(datoRetornado).isEqualTo(LocalDate.now());
    }
}
