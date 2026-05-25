import java.util.List;

public class FIFO implements Planificador{

    public List<Proceso> ejecutar(List<Proceso> procesos) {
        int tiempoActual = 0;

        for (Proceso p : procesos) {
            if (tiempoActual < p.tiempoLlegada) {
                tiempoActual = p.tiempoLlegada;
            }

            p.tiempoEspera = tiempoActual - p.tiempoLlegada;
            tiempoActual += p.tiempoEjecucion;

            p.tiempoFinalizacion = tiempoActual;
            p.tiempoRetorno = p.tiempoFinalizacion - p.tiempoLlegada;
        }

        return procesos;
    }

}
