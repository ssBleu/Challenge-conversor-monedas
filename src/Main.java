import com.challenge.model.CambioDivisa;
import com.challenge.model.HistorialConversion;
import com.challenge.util.InputUtils;
import com.challenge.service.CambioDivisaService;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<HistorialConversion> historial = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {

            mostrarMenu();
            int opcion = InputUtils.leerEntero("Elija una opción válida: ");

            if (opcion == 9){
                mostrarHistorial();
                continue;
            }
            if (opcion == 10) {
                imprimirHistorial(historial);
                System.out.println("Gracias. ¡Vuelve pronto!");
                return;
            }

            CambioDivisa transaccion = obtenerTransaccion(opcion);

            System.out.println("-Se escogió " + transaccion.getMonedaOrigen()+" =>>" + transaccion.getMonedaDestino() );

            double valor = InputUtils.leerDouble("Ingrese el valor en "+ transaccion.getMonedaOrigen() + ": ");
            double tasa = CambioDivisaService.obtenerTasaDeCambio(transaccion.getMonedaOrigen(), transaccion.getMonedaDestino());
            double convertido = CambioDivisaService.convertirMoneda(valor, tasa);

            System.out.println("-El valor en " + transaccion.getMonedaDestino() + " es: "
                    + String.format("%.2f", convertido) +" "+ transaccion.getMonedaDestino());

            historial.add(new HistorialConversion(transaccion.getMonedaOrigen(), transaccion.getMonedaDestino(),  String.format("%.2f", valor),  String.format("%.2f", convertido), LocalDateTime.now()));

            if (!preguntarContinuar()) {
                mostrarHistorial();
                imprimirHistorial(historial);
                System.out.println("Gracias. ¡Vuelve pronto!");
                return;
            }
        }
    }

    private static void mostrarMenu(){
        System.out.println("************************************");
        System.out.println("Escoja la moneda a convertir :D");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dolar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Dólar =>> Sol peruano");
        System.out.println("8) Sol peruano =>> Dólar");
        System.out.println("9) Ver historial de conversiones");
        System.out.println("10) Salir");
        System.out.println("************************************");
    }

    private static CambioDivisa obtenerTransaccion(int opcion) {
        switch (opcion) {
            case 1:
                return new CambioDivisa("USD", "ARS");
            case 2:
                return new CambioDivisa("ARS", "USD");
            case 3:
                return new CambioDivisa("USD", "BRL");
            case 4:
                return new CambioDivisa("BRL", "USD");
            case 5:
                return new CambioDivisa("USD", "COP");
            case 6:
                return new CambioDivisa("COP", "USD");
            case 7:
                return new CambioDivisa("USD", "PEN");
            case 8:
                return new CambioDivisa("PEN", "USD");
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                return obtenerTransaccion(InputUtils.leerEntero("Elija una opción válida: "));
        }
    }

    private static boolean preguntarContinuar() {
        InputUtils.continuarLinea();
        while (true) {
            String respuesta = InputUtils.leerCadena("Desea continuar (si/no)? ");

            if (respuesta.equalsIgnoreCase("si")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("-Respuesta no válida. Por favor, ingrese 'si' o 'no'.");
            }
        }
    }

    private static void mostrarHistorial() {
        System.out.println("_______________________________");
        System.out.println("**Historial de Conversiones**:");
        for (HistorialConversion conversion : historial) {
            System.out.println(conversion);
        }
        System.out.println("_______________________________");
    }

    private static void imprimirHistorial(List<HistorialConversion> historial) throws IOException {
        FileWriter doc = new FileWriter("HistorialDeConversiones.txt");
        doc.write("Historial de Conversiones:");
        doc.write(historial.toString());
        doc.close();
    }
}