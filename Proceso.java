public class Proceso {
    int id;
    int tiempoEjecucion;
    int tiempoRestante;
    int tiempoLlegada;
    int tiempoFinalizacion;
    int tiempoEspera;
    int tiempoRetorno;

    public Proceso(int id, int tiempoEjecucion, int tiempoLlegada) {
        this.id = id;
        this.tiempoEjecucion = tiempoEjecucion;
        this.tiempoRestante = tiempoEjecucion;
        this.tiempoLlegada = tiempoLlegada;
    }

    public Proceso copiar() {
        return new Proceso(id, tiempoEjecucion, tiempoLlegada);
    }

}
