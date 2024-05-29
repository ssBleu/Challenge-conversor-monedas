package com.challenge.model;

public class CambioDivisa {
    private String monedaOrigen;
    private String monedaDestino;

    public CambioDivisa(String monedaOrigen, String monedaDestino) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

}