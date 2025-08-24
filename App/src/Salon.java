public class Salon {
    private String nombre;
    private String tamano;
    private int hora_desocupada = 0;
    private int max_tiempo_reserva;
    private int max_capacidad;
    private double costo_hora;

    public Salon(String nombre, String tamano, double costo_hora, int max_tiempo){
        setNombre(nombre);
        setTamano(tamano);
        setCosto_hora(costo_hora);
        setMax_tiempo_reserva(max_tiempo);
        AsignarPreset(tamano);
    }

    private void AsignarPreset(String tamano){
        int capacidad;
        switch (tamano) {
            case "pequeno":
                capacidad = 80;
                break;
        
            case "mediano":
                capacidad = 200;
                break;

            case "grande":
                capacidad = 450;
                break;

            default:
                capacidad = 0;
                break;
        }
        setMax_capacidad(capacidad);
    }
    

    public double getCosto_hora() {
        return costo_hora;
    }
    public int getHora_desocupada() {
        return hora_desocupada;
    }
    public int getMax_capacidad() {
        return max_capacidad;
    }
    public int getMax_tiempo_reserva() {
        return max_tiempo_reserva;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTamano() {
        return tamano;
    }

    public void setCosto_hora(double costo_hora) {
        this.costo_hora = costo_hora;
    }
    public void setHora_desocupada(int hora_desocupada) {
        this.hora_desocupada = hora_desocupada;
    }
    public void setMax_capacidad(int max_capacidad) {
        this.max_capacidad = max_capacidad;
    }
    public void setMax_tiempo_reserva(int max_tiempo_reserva) {
        this.max_tiempo_reserva = max_tiempo_reserva;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

}
