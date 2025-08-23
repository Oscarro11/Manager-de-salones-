public class Controlador {
    private Salon[] lista_salones_pequenos = new Salon[6];
    private Salon[] lista_salones_medianos = new Salon[4];
    private Salon[] lista_salones_grandes = new Salon[3];
    
    private int cont_salon_pequeno = 0;
    private int cont_salon_mediano = 0;
    private int cont_salon_grande = 0;

    private Reserva[] lista_espera = new Reserva[10];
    private int cont_espera = 0;

    private Salon salon_actual;
    private Reserva reserva_actual;

    public boolean AnadirSalon(String nombre, String tamano, double costo, int tiempo_max){
        boolean salon_anadido = true;

        switch (tamano) {
            case "pequeno":
                if (cont_salon_pequeno == lista_salones_pequenos.length){
                    salon_anadido = false;
                }
                break;

            case "mediano":
                if (cont_salon_mediano == lista_salones_medianos.length){
                    salon_anadido = false;
                }
                break;

            case "grande":
                if (cont_salon_pequeno == lista_salones_pequenos.length){
                    salon_anadido = false;
                }
                break;

            default:
                salon_anadido = false;
                break;
        }

        if (salon_anadido){
            Salon salon_nuevo = new Salon(nombre, tamano, costo, tiempo_max);
            switch (tamano) {
                case "pequeno":
                    lista_salones_pequenos[cont_salon_pequeno] = salon_nuevo;
                    cont_salon_pequeno++;
                    break;
            
                case "mediano":
                    lista_salones_medianos[cont_salon_mediano] = salon_nuevo;
                    cont_salon_mediano++;
                    break;

                case "grande":
                    lista_salones_grandes[cont_salon_grande] = salon_nuevo;
                    cont_salon_grande++;  
                    break;

                default:
                    break;
            }
        }
        return salon_anadido;
    }

    public boolean AnadirReserva(String nombre, boolean VIP, int hora_inicio, int duracion, int asistentes, double pago_inicial){
        
        if (cont_espera == lista_espera.length) {
            return false;
        }
        else{
            lista_espera[cont_espera] = new Reserva(nombre, VIP, hora_inicio, duracion, asistentes, pago_inicial);
            return true;
        }
    }

    public boolean SeleccionarReserva(String nombre_reserva){
        boolean encontrado = false;

        for (Reserva reserva_comparar: lista_espera){
            if (reserva_comparar.getNombre() == nombre_reserva) {
                setReserva_actual(reserva_comparar);
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean SeleccionarSalon(String nombre_salon){
        boolean encontrado = false;

        for (Salon[] lista_salon_comparar: new Salon[][]{lista_salones_pequenos, lista_salones_medianos, lista_salones_grandes}){
            for (Salon salon_a_comparar: lista_salon_comparar){
                if (salon_a_comparar.getNombre() == nombre_salon){
                    setSalon_actual(salon_a_comparar);
                    encontrado = true;
                    break;
                }
            }

            if (encontrado){
                break;
            }
        }

        return encontrado;
    }

    public int AsignarReserva(String nombre_reserva_a_asignar, String nombre_salon_deseado){       
        if (!SeleccionarReserva(nombre_reserva_a_asignar)){
            return -1;
        }

        if (!SeleccionarSalon(nombre_salon_deseado)){
            return -2;
        }

        if (Reglas.verificarVIP(salon_actual, reserva_actual.getStatus_VIP())){
            return 1;
        }

        if (Reglas.verificarCapacidad(salon_actual, reserva_actual.getAsistentes())){
            return 2;
        }

        if (Reglas.verificarHora(salon_actual, reserva_actual.getHora_inicio())){
            return 3;
        }

        if (Reglas.verificarPresupuesto(salon_actual, reserva_actual.getDuracion_evento(), reserva_actual.getPago_inicial())){
            return 4;
        }

        if (Reglas.verificarTiempoDeReserva(salon_actual, reserva_actual.getDuracion_evento())){
            return 5;
        }

        salon_actual.ActualizarDisponibilidad(reserva_actual.getHora_inicio(), reserva_actual.getDuracion_evento());
        for (int i=0; i) //pendiente de hacer actualizacion de lista de espera
        
    }

    public void setReserva_actual(Reserva reserva_actual) {
        this.reserva_actual = reserva_actual;
    }
    public void setSalon_actual(Salon salon_actual) {
        this.salon_actual = salon_actual;
    }

    public Reserva getReserva_actual() {
        return reserva_actual;
    }
    public Salon getSalon_actual() {
        return salon_actual;
    }
}
