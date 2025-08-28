public class Reglas {
    public static boolean VerificarVIP(String tamano, boolean vip){
        return !(tamano.equals("grande") && !vip);
    }

    public static boolean VerificarCapacidad(String tamano, int capacidad, int asistentes){
        int cuota;
        switch (tamano) {
            case "pequeno":
                cuota = capacidad / 2;
                break;
        
            case "mediano":
                cuota = (capacidad * 2) / 3;
                break;

            case "grande":
                cuota = (capacidad * 3) / 4;
                break;

            default:
            cuota = 0;
                break;
        }
        return asistentes >= cuota && asistentes <= capacidad;
    }

    public static boolean VerificarHorario(int hora_disponible, int hora_evento){
        return hora_evento > hora_disponible;
    }

    public static boolean VerificarDuracion(int max_tiempo, int duracion){
        return duracion <= max_tiempo;
    }

    public static boolean VerificarDeposito(double costo_hora, int duracion, double deposito){
        return deposito >= (costo_hora * duracion) / 2;
    }
}
