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
        if (Reglas.VerificarDuracion(lista_salon_actual.getMaxTiempoSalon(nombre_salon), lista_espera.getDuracionReserva(nombre_reserva))){
            return 4;
        }
        if (Reglas.VerificarDeposito(lista_salon_actual.getCostoSalon(nombre_salon), lista_espera.getDuracionReserva(nombre_reserva), lista_espera.getDepositoReserva(nombre_reserva))){
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

            lista_salon_actual.ActualizarDisponibilidadSalon(nombre_salon, codigo_error, codigo_error);
            lista_espera.QuitarReserva(nombre_reserva);
            return 0;
        }
    }
}
