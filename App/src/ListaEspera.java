public class ListaEspera {
    private Reserva[] lista_reservas;
    private boolean[] lista_disponibles;

    public ListaEspera(int capacidad){
        lista_reservas = new Reserva[capacidad];
        lista_disponibles = new boolean[capacidad];
        
        for (int i=0; i<capacidad; i++){
            lista_disponibles[i] = true;
        }
        
    }

    public boolean VerificarMaxCapacidad(){
        for (boolean ocupado: lista_disponibles){
            if (ocupado){
                return false;
            }
        }
        return true;
    }

    public boolean VerificarVacio(){
        for (boolean ocupado: lista_disponibles){
            if (!ocupado){
                return false;
            }
        }
        return true;
    }

    public boolean AnadirReserva(String nombre, boolean vip, int hora_inicio, int duracion, int asistentes, double deposito){
        if (!VerificarMaxCapacidad()) {
            for (int i=0; i<lista_disponibles.length; i++){
                if (lista_disponibles[i]){
                    lista_reservas[i] = new Reserva(nombre, vip, hora_inicio, duracion, asistentes, deposito);
                    lista_disponibles[i] = false;
                    return true;
                }
            }
        }
        return false;
    }

    private Reserva BuscarReserva(String nombre_reserva){
        for (int i=0; i<lista_reservas.length; i++){
            if (!lista_disponibles[i]){
                if (lista_reservas[i].getNombre().equals(nombre_reserva)){
                    return lista_reservas[i];
                }
            }
        }
        return null;
    }

    public boolean ReservaExiste(String nombre_reserva){
        return !(BuscarReserva(nombre_reserva) == null);
    }

    public void QuitarReserva(String nombre_reserva){
        for (int i=0; i<lista_reservas.length; i++){
            if (!lista_disponibles[i]){
                if (lista_reservas[i].getNombre().equals(nombre_reserva)){
                    lista_reservas[i] = null;
                    lista_disponibles[i] = true;
                    break;
                }
            }
        }
    }

    public String MostrarReservas(){
        int cont_existe = 0;
        String mensaje = "Las reservas de los siguientes eventos se encuentran en lista de espera:\n";
        for (int i=0; i<lista_disponibles.length; i++){
            if (!lista_disponibles[i]){
                mensaje += "- " + lista_reservas[i].getNombre() + "\n";
                cont_existe++;
            }
        }

        if (cont_existe != 0){
            return mensaje;
        }
        else{
            return "No hay ninguna reserva en la lista de espera";
        }
    }


    public boolean getVipReserva(String nombre_reserva){
        return BuscarReserva(nombre_reserva).getVip();
    }

    public int getHoraReserva(String nombre_reserva){
        return BuscarReserva(nombre_reserva).getHora_inicio();
    }

    public int getDuracionReserva(String nombre_reserva){
        return BuscarReserva(nombre_reserva).getDuracion();
    }

    public int getAsistentesReserva(String nombre_reserva){
        return BuscarReserva(nombre_reserva).getAsistentes();
    }

    public double getDepositoReserva(String nombre_reserva){
        return BuscarReserva(nombre_reserva).getDeposito();
    }

    
    public boolean[] getLista_disponibles() {
        return lista_disponibles;
    }
    public Reserva[] getLista_reservas() {
        return lista_reservas;
    }

    public void setLista_disponibles(boolean[] lista_disponibles) {
        this.lista_disponibles = lista_disponibles;
    }
    public void setLista_reservas(Reserva[] lista_reservas) {
        this.lista_reservas = lista_reservas;
    }
}
