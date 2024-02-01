package com.ceiba.biblioteca.controllers;

import com.ceiba.biblioteca.calificador.ResultadoPrestarTest;
import com.ceiba.biblioteca.calificador.SolicitudPrestarLibroTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PrestamoTests {

    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;
    public static final int USUARIO_DESCONOCIDO = 5;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void prestamoLibroUsuarioInvitadoDeberiaAlmacenarCorrectamenteYCalcularFechaDeDevolucion() throws Exception {

        MvcResult resultadoLibroPrestado = mvc.perform(MockMvcRequestBuilders
                .post("/prestamo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(new SolicitudPrestarLibroTest("EQWQW8545", "74851254", USUARIO_INVITADO))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fechaMaximaDevolucion").exists())
                .andReturn();

        ResultadoPrestarTest resultadoPrestamo = objectMapper
                .readValue(resultadoLibroPrestado.getResponse().getContentAsString(), ResultadoPrestarTest.class);

        LocalDate fechaPrestamo = LocalDate.now();
        fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, 7);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        mvc.perform(MockMvcRequestBuilders
                .get("/prestamo/" + 2002)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje", is(
                        "No existe un prestamo asociado al identificador 2002")));

    }

    public static LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
