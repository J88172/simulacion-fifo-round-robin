import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Lista original de procesos
        List<Proceso> procesosOriginales = new ArrayList<>(Arrays.asList(
                new Proceso(1, 5, 0),
                new Proceso(2, 3, 1),
                new Proceso(3, 8, 2)
        ));

        List<Proceso> fifoProcesos = copiarLista(procesosOriginales);
        List<Proceso> rrProcesos = copiarLista(procesosOriginales);

        // ===== FIFO =====
        System.out.println("===== FIFO =====");
        FIFO fifo = new FIFO();
        List<Proceso> resultadoFIFO = fifo.ejecutar(fifoProcesos);

        imprimirResultados(resultadoFIFO);

        // ===== ROUND ROBIN =====
        System.out.println("\n===== ROUND ROBIN =====");
        RoundRobin rr = new RoundRobin(2); 
        List<Proceso> resultadoRR = rr.ejecutar(rrProcesos);

        imprimirResultados(resultadoRR);


    }

    static List<Proceso> copiarLista(List<Proceso> original) {
        List<Proceso> copia = new ArrayList<>();
        for (Proceso p : original) {
            copia.add(p.copiar());
        }
        return copia;
    }

    static void imprimirResultados(List<Proceso> procesos) {
        double totalEspera = 0;
        double totalRetorno = 0;

        System.out.println("ID | Espera | Retorno | Finalización");

        for (Proceso p : procesos) {
            System.out.println(p.id + " | " +
                    p.tiempoEspera + " | " +
                    p.tiempoRetorno + " | " +
                    p.tiempoFinalizacion);

            totalEspera += p.tiempoEspera;
            totalRetorno += p.tiempoRetorno;
        }

        System.out.println("Promedio espera: " + (totalEspera / procesos.size()));
        System.out.println("Promedio retorno: " + (totalRetorno / procesos.size()));
    }


}
