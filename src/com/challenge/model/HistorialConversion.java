package com.challenge.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialConversion {
    private String monedaOrigen;
    private String monedaDestino;
    private String valorOrigen;
    private String valorDestino;
    private LocalDateTime fechaHora;

    public HistorialConversion(String monedaOrigen, String monedaDestino, String valorOrigen, String valorDestino, LocalDateTime fechaHora) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.valorOrigen = valorOrigen;
        this.valorDestino = valorDestino;
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "\n(monedaOrigen='" + monedaOrigen + "', monedaDestino='" + monedaDestino +
                "', valorIngresado='" + valorOrigen + "', valorConvertido='" + valorDestino +
                "', fechaHora='" + fechaHora.format(formatoFecha) + "')";
    }
}
