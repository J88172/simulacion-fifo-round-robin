import java.util.*;

public class RoundRobin implements Planificador {

    int quantum;

    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    public List<Proceso> ejecutar(List<Proceso> procesos) {

        // Asegurar orden por llegada
        procesos.sort(Comparator.comparingInt(p -> p.tiempoLlegada));

        Queue<Proceso> cola = new LinkedList<>();
        int tiempoActual = 0;
        int index = 0;

        // Ajustar inicio del tiempo
        if (!procesos.isEmpty()) {
            tiempoActual = procesos.get(0).tiempoLlegada;
        }

        while (!cola.isEmpty() || index < procesos.size()) {

            // Agregar procesos que ya llegaron
            while (index < procesos.size() && procesos.get(index).tiempoLlegada <= tiempoActual) {
                cola.add(procesos.get(index));
                index++;
            }

            // Si no hay procesos listos, avanzar el tiempo
            if (cola.isEmpty()) {
                tiempoActual = procesos.get(index).tiempoLlegada;
                continue;
            }

            Proceso p = cola.poll();

            if (p.tiempoRestante > quantum) {
                tiempoActual += quantum;
                p.tiempoRestante -= quantum;

                // Agregar nuevos procesos que hayan llegado durante la ejecución
                while (index < procesos.size() && procesos.get(index).tiempoLlegada <= tiempoActual) {
                    cola.add(procesos.get(index));
                    index++;
                }

                cola.add(p);
            } else {
                tiempoActual += p.tiempoRestante;
                p.tiempoRestante = 0;

                p.tiempoFinalizacion = tiempoActual;
                p.tiempoRetorno = p.tiempoFinalizacion - p.tiempoLlegada;
                p.tiempoEspera = p.tiempoRetorno - p.tiempoEjecucion;
            }
        }

        return procesos;
    }
}