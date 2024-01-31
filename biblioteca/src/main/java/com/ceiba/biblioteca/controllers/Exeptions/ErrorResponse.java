package com.ceiba.biblioteca.controllers.Exeptions;

public final class ErrorResponse {

    private final String mensaje;

    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
