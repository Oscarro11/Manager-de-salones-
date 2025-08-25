public class ListaSalones {
    private String tamano;
    private Salon[] lista_salones;
    private int pos_ingresar_salon = 0;

    public ListaSalones(String tamano, int capacidad){
        setTamano(tamano);
        setLista_salones(new Salon[capacidad]);
    }

    public boolean VerificarMaxCapacidad(){
        return pos_ingresar_salon == lista_salones.length;
    }

    public boolean VerificarVacio(){
        return pos_ingresar_salon == 0;
    }

    public boolean AnadirSalon(String nombre, String tamano, double costo, int max_tiempo){
        if (!VerificarMaxCapacidad()){
            lista_salones[pos_ingresar_salon] = new Salon(nombre, tamano, costo, max_tiempo);
            pos_ingresar_salon++;
            return true;
        }
        return false;

    }

    private Salon BuscarSalon(String nombre_salon){
        for (int i=0; i<pos_ingresar_salon; i++){
            if (lista_salones[i].getNombre() == nombre_salon){
                return lista_salones[i];
            }
        }
        return null;
    }

    public boolean SalonExiste(String nombre_salon){
        return !(BuscarSalon(nombre_salon) == null);
    }

    public String MostrarSalones(){
        String mensaje = "Salones disponibles de tamano " + tamano + ":\n";
        for (int i=0; i<pos_ingresar_salon; i++){
            mensaje += "- Salon " + lista_salones[i].getNombre() + "\n";
        }

        if (VerificarVacio()){
            return "No hay salones disponibles de tamano " + tamano;
        }
        else{
            return mensaje;
        }        
    }

    public void ActualizarDisponibilidadSalon(String nombre_salon, int nueva_hora, int duracion){
        BuscarSalon(nombre_salon).ActualizarDisponibilidad(nueva_hora, duracion);
    }


    public String getTamanoSalon(String nombre_salon){
        return BuscarSalon(nombre_salon).getTamano();
    }

    public int getHoraSalon(String nombre_salon){
        return BuscarSalon(nombre_salon).getHora_desocupada();
    }

    public int getMaxTiempoSalon(String nombre_salon){
        return BuscarSalon(nombre_salon).getMax_tiempo_reserva();
    }

    public int getMaxCapacidadSalon(String nombre_salon){
        return BuscarSalon(nombre_salon).getMax_capacidad();
    }

    public double getCostoSalon(String nombre_salon){
        return BuscarSalon(nombre_salon).getCosto_hora();
    }


    public Salon[] getLista_salones() {
        return lista_salones;
    }
    public int getPos_ingresar_salon() {
        return pos_ingresar_salon;
    }
    public String getTamano() {
        return tamano;
    }

    public void setLista_salones(Salon[] lista_salones) {
        this.lista_salones = lista_salones;
    }
    public void setPos_ingresar_salon(int pos_ingresar_salon) {
        this.pos_ingresar_salon = pos_ingresar_salon;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
