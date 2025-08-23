public class Reserva {
    private String nombre;
    private boolean cliente_vip;
    private int hora_inicio;
    private int duracion_evento;
    private int asistentes;
    private double pago_inicial;

    public Reserva(String nombre, boolean cliente_vip, int hora_inicio, int duracion_evento, int asistentes, double pago_inicial){
        this.nombre = nombre;
        this.cliente_vip = cliente_vip;
        this.hora_inicio = hora_inicio;
        this.duracion_evento = duracion_evento;
        this.asistentes = asistentes;
        this.pago_inicial = pago_inicial;
    }

    public String getNombre(){
        return this.nombre;
    }
    public boolean getCliente_vip(){
        return this.cliente_vip;
    }
    public int getHora_inicio() {
        return hora_inicio;
    }
    public int getAsistentes() {
        return asistentes;
    }
    public int getDuracion_evento() {
        return duracion_evento;
    }
    public double getPago_inicial() {
        return pago_inicial;
    }

    public void setAsistentes(int asistentes) {
        this.asistentes = asistentes;
    }
    public void setCliente_vip(boolean cliente_vip) {
        this.cliente_vip = cliente_vip;
    }
    public void setDuracion_evento(int duracion_evento) {
        this.duracion_evento = duracion_evento;
    }
    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPago_inicial(double pago_inicial) {
        this.pago_inicial = pago_inicial;
    }
    
}
