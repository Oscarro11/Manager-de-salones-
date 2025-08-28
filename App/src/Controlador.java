public class Controlador {
    private ListaSalones lista_salon_pequeno;
    private ListaSalones lista_salon_mediano;
    private ListaSalones lista_salon_grande;
    private ListaSalones lista_salon_actual;

    private ListaEspera lista_espera;

    public Controlador(int capacidad_pequeno, int capacidad_mediano, int capacidad_grande, int capacidad_espera){
        lista_salon_pequeno = new ListaSalones("pequeno", capacidad_pequeno);
        lista_salon_mediano = new ListaSalones("mediano", capacidad_mediano);
        lista_salon_grande = new ListaSalones("grande", capacidad_grande); 
        
        lista_espera = new ListaEspera(capacidad_espera);
    }

    public boolean AnadirSalon(String nombre, String tamano, double costo, int max_tiempo){
        switch (tamano) {
            case "pequeno":
                lista_salon_actual = lista_salon_pequeno;
                break;

            case "mediano":
                lista_salon_actual = lista_salon_mediano;
                break;

            case "grande":
                lista_salon_actual = lista_salon_grande;
                break;

            default:
                return false;
        }

        if (lista_salon_actual.VerificarMaxCapacidad()){
            return false;
        }

        else{
            lista_salon_actual.AnadirSalon(nombre, tamano, costo, max_tiempo);
            return true;
        }
    }

    public boolean VerificarMaxCapacidadEspera(){
        return lista_espera.VerificarMaxCapacidad();
    }

    public boolean VerificarMaxCapacidadSalon(String tamano_salon){
        boolean salon_al_maximo = true;
        switch (tamano_salon) {
            case "pequeno":
                salon_al_maximo = lista_salon_pequeno.VerificarMaxCapacidad();
                break;

            case "mediano":
                salon_al_maximo = lista_salon_mediano.VerificarMaxCapacidad();
                break;

            case "grande":
                salon_al_maximo = lista_salon_grande.VerificarMaxCapacidad();
                break;
        
            default:
                break;
        }
        return salon_al_maximo;
    }

    public boolean SalonExiste(String nombre_salon){
        return (lista_salon_pequeno.SalonExiste(nombre_salon) || lista_salon_mediano.SalonExiste(nombre_salon) || lista_salon_grande.SalonExiste(nombre_salon));
    }

    public boolean ReservaExiste(String nombre_reserva){
        return lista_espera.ReservaExiste(nombre_reserva);
    }

    public boolean HayReservas(){
        return lista_espera.VerificarVacio();
    }

    public boolean AnadirReserva(String nombre, boolean vip, int hora_inicio, int duracion, int asistentes, double deposito){
        if (lista_espera.VerificarMaxCapacidad()){
            return false;
        }

        else{
            lista_espera.AnadirReserva(nombre, vip, hora_inicio, duracion, asistentes, deposito);
            return true;
        }
    }

    private int VerificarAsignacionReserva(String nombre_reserva, String nombre_salon){
        if (lista_salon_pequeno.SalonExiste(nombre_salon)){
            lista_salon_actual = lista_salon_pequeno;
        }
        else if (lista_salon_mediano.SalonExiste(nombre_salon)){
            lista_salon_actual = lista_salon_mediano;
        }
        else if (lista_salon_grande.SalonExiste(nombre_salon)){
            lista_salon_actual = lista_salon_grande;
        }
        else{
            return -2;
        }


        if (!lista_espera.ReservaExiste(nombre_reserva)){
            return -1;
        }
        if (!Reglas.VerificarVIP(lista_salon_actual.getTamano(), lista_espera.getVipReserva(nombre_reserva))){
            return 1;
        }
        if (!Reglas.VerificarCapacidad(lista_salon_actual.getTamano(), lista_salon_actual.getMaxCapacidadSalon(nombre_salon), lista_espera.getAsistentesReserva(nombre_reserva))){
            return 2;
        }
        if (!Reglas.VerificarHorario(lista_salon_actual.getHoraSalon(nombre_salon), lista_espera.getHoraReserva(nombre_reserva))){
            return 3;
        }
        if (!Reglas.VerificarDuracion(lista_salon_actual.getMaxTiempoSalon(nombre_salon), lista_espera.getDuracionReserva(nombre_reserva))){
            return 4;
        }
        if (!Reglas.VerificarDeposito(lista_salon_actual.getCostoSalon(nombre_salon), lista_espera.getDuracionReserva(nombre_reserva), lista_espera.getDepositoReserva(nombre_reserva))){
            return 5;
        }

        return 0;
    }

    public int AsignarReserva(String nombre_reserva, String nombre_salon){
        int codigo_error = VerificarAsignacionReserva(nombre_reserva, nombre_salon);

        if (codigo_error != 0){
            return codigo_error;
        }
        else{
            if (lista_salon_pequeno.SalonExiste(nombre_salon)){
                lista_salon_actual = lista_salon_pequeno;
            }
            else if (lista_salon_mediano.SalonExiste(nombre_salon)){
                lista_salon_actual = lista_salon_mediano;
            }
            else{
                lista_salon_actual = lista_salon_grande;
            }

            lista_salon_actual.ActualizarDisponibilidadSalon(nombre_salon, lista_espera.getHoraReserva(nombre_reserva), lista_espera.getDuracionReserva(nombre_reserva));
            lista_espera.QuitarReserva(nombre_reserva);
            return 0;
        }
    }

    public String MostrarReservas(){
        return lista_espera.MostrarReservas();
    }

    public String MostrarSalones(String tamano_salon){
        String mensaje = "";
        switch (tamano_salon) {
            case "pequeno":
                mensaje = lista_salon_pequeno.MostrarSalones();
                break;
                    
            case "mediano":
                mensaje = lista_salon_mediano.MostrarSalones();
                break;

            case "grande":
                mensaje = lista_salon_grande.MostrarSalones();
                break;

            default:
                break;
        }
        return mensaje;
    }
}
