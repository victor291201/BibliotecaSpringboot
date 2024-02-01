package com.ceiba.biblioteca.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.reposotories.PrestamoRepository;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImp prestamoService;

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

    @DisplayName("crear y retornar un contacto cuando se envia un contacto")
    @Test
    public void test1() {
        // given
        when(prestamoRepository.save(any(Prestamo.class)))
                .thenReturn(dato);

        // when
        Prestamo datoGuardado = prestamoService.crearPrestamo(dato);

        // then
        assertThat(datoGuardado).isNotNull();
        assertThat(datoGuardado).isEqualTo(dato);
    }

    @DisplayName("retornar un prestamo cuando se envia un id")
    @Test
    public void test2() {
        // given
        when(prestamoRepository.save(any(Prestamo.class)))
                .thenReturn(dato);
        when(prestamoRepository.findById(anyInt()))
                .thenReturn(Optional.of(dato));
        Prestamo datoGuardado = prestamoService.crearPrestamo(dato);

        // when
        Prestamo datoConsultado = prestamoService.consultarPrestamo(dato.getId());

        // then
        assertThat(datoConsultado).isNotNull();
        assertThat(datoConsultado).isEqualTo(datoGuardado);
    }
}
