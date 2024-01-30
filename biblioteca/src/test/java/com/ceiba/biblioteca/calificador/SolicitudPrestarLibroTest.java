package com.ceiba.biblioteca.calificador;

public class SolicitudPrestarLibroTest {

    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;

    public SolicitudPrestarLibroTest(String isbn, String identificacionUsuario, int tipoUsuario) {
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }
}
