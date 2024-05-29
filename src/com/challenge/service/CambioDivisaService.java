package com.challenge.service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CambioDivisaService {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static double obtenerTasaDeCambio(String moneda, String monedaConvertir) throws IOException, InterruptedException {
        // URL de la API de tasa de cambio con la moneda base
        String direccion = "https://v6.exchangerate-api.com/v6/76c7678b4c10085795a41ae9/latest/" + moneda;

        // Solicitud y recepcion HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String jsonResponse = response.body();

        // Parseo del JSON para obtener la tasa
        double tasa = parsearTasa(jsonResponse, monedaConvertir);
        return tasa;
    }

    private static double parsearTasa(String jsonResponse, String monedaConvertir) {
        // Parseo del JSON a un objeto JsonObject
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        return conversionRates.get(monedaConvertir).getAsDouble();
    }

    public static double convertirMoneda(double valor, double tasa) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor debe ser positivo");
        }
        return valor * tasa;
    }
}