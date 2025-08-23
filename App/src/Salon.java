public class Salon {
    private String nombre;
    private String tamano;
    private int hora_disponible = 0;
    private int tiempo_maximo_reserva;
    private int capacidad_maxima;
    private double costo_reserva;

    private void AsignarPreset(String tamano){
        switch (tamano) {
            case "grande":
                setCapacidad_maxima(500);
                break;
        
            case "mediano":
                setCapacidad_maxima(300);
                break;

            case "pequeno":
                setCapacidad_maxima(100);
                break;

            default:
                break;
        }
    }

    public void ActualizarDisponibilidad(int nueva_hora, int tiempo_reserva){
        int nueva_hora_a_asignar = nueva_hora + tiempo_reserva;
        if (nueva_hora_a_asignar > 24){
            setHora_disponible(25);
        }
        else{
            setHora_disponible(nueva_hora_a_asignar);
        }
    }

    Salon(String nombre, String tamano, double costo_reserva, int tiempo_maximo_reserva){
        this.nombre = nombre;
        this.tamano = tamano;
        this.costo_reserva = costo_reserva;
        this.tiempo_maximo_reserva = tiempo_maximo_reserva;
        AsignarPreset(tamano);
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }
    public void setCosto_reserva(double costo_reserva) {
        this.costo_reserva = costo_reserva;
    }
    public void setHora_disponible(int hora_disponible) {
        this.hora_disponible = hora_disponible;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
    public void setTiempo_maximo_reserva(int tiempo_maximo_reserva) {
        this.tiempo_maximo_reserva = tiempo_maximo_reserva;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }
    public double getCosto_reserva() {
        return costo_reserva;
    }
    public int getHora_disponible() {
        return hora_disponible;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTamano() {
        return tamano;
    }
    public int getTiempo_maximo_reserva() {
        return tiempo_maximo_reserva;
    }

}
