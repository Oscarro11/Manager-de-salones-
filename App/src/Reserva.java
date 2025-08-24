public class Reserva {
    private String nombre;
    private boolean vip;
    private int hora_inicio;
    private int duracion;
    private int asistentes;
    private double deposito;

    public Reserva(String nombre, boolean vip, int hora_inicio, int duracion, int asistentes, double deposito){
        setNombre(nombre);
        setVip(vip);
        setHora_inicio(hora_inicio);
        setDuracion(duracion);
        setAsistentes(asistentes);
        setDeposito(deposito);
    }
    

    public int getAsistentes() {
        return asistentes;
    }
    public double getDeposito() {
        return deposito;
    }
    public int getDuracion() {
        return duracion;
    }
    public int getHora_inicio() {
        return hora_inicio;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean getVip(){
        return vip;
    }


    public void setAsistentes(int asistentes) {
        this.asistentes = asistentes;
    }
    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
    }
    
}
