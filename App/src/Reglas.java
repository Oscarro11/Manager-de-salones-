public class Reglas {
    public static boolean verificarCapacidad(Salon salon_deseado, int asistentes){
        boolean aceptado = false;

        switch (salon_deseado.getTamano()) {
            case "pequeno":
                aceptado = asistentes < salon_deseado.getCapacidad_maxima() * 1/2;
                break;
                
            case "mediano":
                aceptado = asistentes < salon_deseado.getCapacidad_maxima() * 2/3;
                break;
                
            case "grande":
                aceptado = asistentes < salon_deseado.getCapacidad_maxima() * 3/4;
                break;

            default:
                break;
        }
        return aceptado;
    }

    public static boolean verificarHora(Salon salon_deseado, int hora_de_inicio){
        return hora_de_inicio < salon_deseado.getHora_disponible();
    }

    public static boolean verificarTiempoDeReserva(Salon salon_deseado, int duracion_evento){
        return duracion_evento <= salon_deseado.getTiempo_maximo_reserva();
    }

    public static boolean verificarPresupuesto(Salon salon_deseado, int duracion_evento, double pago_inicial){
        return pago_inicial < (salon_deseado.getCosto_reserva() * duracion_evento)/2;
    }

    public static boolean verificarVIP(Salon salon_deseado, boolean reserva_vip){
        if (salon_deseado.getTamano() == "grande" && !reserva_vip){
            return false;
        }
        else{
            return true;
        }
    }
}
