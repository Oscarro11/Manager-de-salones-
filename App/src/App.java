import java.util.Scanner;

public class App {
    public static Controlador controlador = new Controlador(6, 4, 3, 10);
    public static Scanner teclado = new Scanner(System.in);

    public static void CrearSalon(){
        boolean validado = false;

        String nombre = "";
        String tamano = "";
        double costo = 0.0;
        int max_horas = 0;

        System.out.println("\nIngrese el nombre del nuevo salon");
        while (!validado) {
            nombre = teclado.nextLine();
            if (nombre.equals("")){
                System.out.println("El nombre del salon no puede ser vacio. Intentelo de nuevo");
            }
            else if(controlador.SalonExiste(nombre)){
                System.out.println("El sistema ya cuenta con un salon con este nombre, intentelo de nuevo");
            }
            else{
                validado = true;
            }
        }

        System.out.println("\nIngrese el tamano del nuevo salon (tamanos disponibles: pequeno, mediano y grande)");
        validado = false;
        while (!validado) {
            tamano = teclado.nextLine().toLowerCase();
            if (tamano.equals("pequeno") || tamano.equals("mediano") || tamano.equals("grande")){
                if (controlador.VerificarMaxCapacidadSalon(tamano)){
                    System.out.println("El sistema no puede almacenar mas salones del tamano " + tamano + ". Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }
            else{
                System.out.println("El tamano del salon debe ser pequeno, mediano o grande. Intentelo de nuevo");
            }
        }

        System.out.println("\nIngrese el costo por hora que cuesta rentar el salon (en quetzales)");
        validado = false;
        while (!validado) {
            costo = teclado.nextDouble();
            teclado.nextLine();
            if (costo > 0){
                validado = true;
            }
            else{
                System.out.println("El costo por hora del salon debe ser mayor a 0. Intentelo de nuevo");
            }
        }

        System.out.println("\nIngrese la cantidad de horas máximas que puede durar un evento en el salon");
        validado = false;
        while (!validado) {
            max_horas = teclado.nextInt();
            teclado.nextLine();
            if (max_horas > 0 && max_horas < 24){
                validado = true;
            }
            else{
                System.out.println("El tiempo maximo de reserva del salon debe estar en el intervalo de 0 a 24 horas. Intentelo de nuevo");
            }
        }

        controlador.AnadirSalon(nombre, tamano, costo, max_horas);
        System.out.println("\nSalon creado exitosamente");
    }

    public static void AnadirReserva(){
        if (controlador.VerificarMaxCapacidadEspera()){
            System.out.println("La lista de espera para eventos esta llena. Asigne algun evento a un salon, e intentelo de nuevo");
        }
        else{
            boolean validado = false;
            String nombre = "";
            boolean vip = false;
            int hora_inicio = 0;
            int duracion = 0;
            int asistentes = 0;
            double deposito = 0;

            System.out.println("\nIngrese el nombre del evento a reservar");
            while (!validado) {
                nombre = teclado.nextLine();
                if (nombre.equals("")){
                    System.out.println("El nombre del evento a reservar no puede ser vacio. Intentelo de nuevo");
                }
                else if(controlador.SalonExiste(nombre)){
                    System.out.println("Ya existe un salon con el mismo nombre en el sistema. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }
            }
            validado = false;

            System.out.println("\n¿Es usted un cliente VIP (y/n)?");
            while (!validado) {
                String tipo_cliente = teclado.nextLine().toLowerCase();
                if (tipo_cliente.equals("y")){
                    vip = true;
                    validado = true;
                }
                else if(tipo_cliente.equals("n")){
                    vip = false;
                    validado = true;
                }
                else{
                    System.out.println("Debe ingresar 'y' o 'n' para confirmar si es un cliente VIP. Intentelo de nuevo");
                }
            }
            validado = false;

            System.out.println("\nIngrese la hora a la que iniciara el evento (formato de 24 horas)");
            while (!validado) {
                hora_inicio = teclado.nextInt();
                teclado.nextLine();
                if (hora_inicio > 0 && hora_inicio <= 24){
                    validado = true;
                }
                else{
                    System.out.println("La hora de inicio de su evento debe estar en el rango de 0 a 24. Intentelo de nuevo");
                }
            }
            validado = false;

            System.out.println("\nIngrese la cantidad de horas que durara el evento");
            while (!validado) {
                duracion = teclado.nextInt();
                teclado.nextLine();
                if (duracion >= 1){
                    validado = true;
                }
                else{System.out.println("El evento debe durar al menos 1 hora. Intentelo de nuevo");
                }
            }
            validado = false;

            System.out.println("\nIngrese la cantidad prevista de asistentes que llegaran al evento");
            while (!validado) {
                asistentes = teclado.nextInt();
                teclado.nextLine();
                if (asistentes > 0){
                    validado = true;
                }
                else{
                    System.out.println("Debe llegar al menos 1 asistente para poder reservar el evento. Intentelo de nuevo");
                }
            }
            validado = false;

            System.out.println("\nIngrese el deposito de dinero que ha destinado al evento, previo al pago completo (en quetzales)");
            while (!validado) {
                deposito = teclado.nextDouble();
                teclado.nextLine();
                if (deposito > 0){
                    validado = true;
                }
                else{
                    System.out.println("El deposito debe ser mayor a Q0.00 en su saldo. Intentelo de nuevo");
                }
            }

            controlador.AnadirReserva(nombre, vip, hora_inicio, duracion, asistentes, deposito);
            System.out.println("\nReserva anadida con exito a la lista de espera");
        } 

    }
    
    public static void MostrarSalones(){
        System.out.println(controlador.MostrarSalones("pequeno"));
        System.out.println(controlador.MostrarSalones("mediano"));
        System.out.println(controlador.MostrarSalones("grande"));
    }

    public static void MostrarReservas(){
        System.out.println(controlador.MostrarReservas());
    }

    public static void AsignarReserva(){
        if (controlador.HayReservas()) {
            System.out.println("Debe haber al menos 1 reserva en la lista de espera para poder usar esta opcion. Ingrese una reserva al sistema, e intentelo de nuevo \n");
        }
        else{
            boolean validado = false;
            String nombre_reserva_asignar = "";
            String nombre_salon_asignar = "";
            
            while (!validado) {
                System.out.println("Ingrese el nombre de una de estas reservas para asignarla a un salon:");
                MostrarReservas();
                nombre_reserva_asignar = teclado.nextLine();
                if (!controlador.ReservaExiste(nombre_reserva_asignar)){
                    System.out.println("Esta reserva no se encuentra en la lista de espera. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }  
            }
            validado = false;

            while (!validado) {
                System.out.println("Ingrese el nombre de uno de los salones siguientes, la reserva previamente seleccionada sera asignada a este salon");
                MostrarSalones();
                nombre_salon_asignar = teclado.nextLine();
                if (!controlador.SalonExiste(nombre_salon_asignar)){
                    System.out.println("Este salon no se encuentra en la lista de espera. Intentelo de nuevo");
                }
                else{
                    validado = true;
                }  
            }

            int resultado_asignacion = controlador.AsignarReserva(nombre_reserva_asignar, nombre_salon_asignar);
            if (resultado_asignacion == 0){
                System.out.println("La reserva se ha asignado al salon correspondiente de forma exitosa.\n");
            }
            else{
                String razon = "";
                System.out.println("La reserva no se ha asignado al salon deseado.");
                switch (resultado_asignacion) {
                    case -2:
                        razon = "El salon deseado para la asignacion no existe en el sistema.";
                        break;
                
                    case -1:
                        razon = "La reserva para la cual se iba a hacer la asignacion no existe en el sistema.";
                        break;

                    case 1:
                        razon = "Un cliente que no es VIP no puede reservar un salon grande.";
                        break;

                    case 2:
                        razon = "El evento cuenta con muy pocos o demasiados asistentes para la capacidad del salon";
                        break;

                    case 3:
                        razon = "El salon deseado ya esta apartado para la hora en que comienza el evento.";
                        break;

                    case 4:
                        razon = "El salon deseado no puede usarse por la duracion de todo el evento, debido a politicas internas";
                        break;

                    case 5:
                        razon = "La reserva del evento no cuenta con los fondos necesarios en su deposito para poder ser asignada al salon deseado";
                        break;

                    default:
                        razon = "No se ha podido determinar la razon de que la asignacion haya fallado.";
                        break;
                }
                System.out.println("Motivo: " + razon);
                System.out.println("Revise los datos de la reserva y del salon, e intentelo de nuevo");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido al programa de control de salones. Antes de proceder, requerimos que ingrese 4 salones al sistema");
        /*for (int i=0; i<4; i++){
            System.out.println("Debe ingresar " + (4-i) + " salones mas antes de acceder al menu");
            CrearSalon();
        }*/

        controlador.AnadirReserva("cumple", false, 11, 3, 100, 1000);
        controlador.AnadirReserva("cumple2", true, 12, 2, 400, 1000);
        controlador.AnadirReserva("boda", true, 13, 4, 400, 10000);
        controlador.AnadirSalon("Danubio Azul", "grande", 1, 5);

        String opcion = "";
        boolean continuar_menu = true;
        while (continuar_menu) {
            System.out.println("\nBienvenido al menu principal. Este cuenta con las siguientes opciones:");
            System.out.println("1. Anadir salon al sistema");
            System.out.println("2. Mostrar salones en el sistema");
            System.out.println("3. Anadir reserva de evento al sistema");
            System.out.println("4. Mostrar reservas en el sistema");
            System.out.println("5. Asignar reserva ingresada en el sistema a salon");
            System.out.println("6. Salir");
            
            System.out.println("\nIngrese el numero de la opcion que desea realizar: ");
            opcion = teclado.nextLine().strip();

            switch (opcion) {
                case "1":
                    CrearSalon();
                    break;
            
                case "2":
                    MostrarSalones();
                    break;

                case "3":
                    AnadirReserva();
                    break;

                case "4":
                    MostrarReservas();
                    break;

                case "5":
                    AsignarReserva();
                    break;

                case "6":
                    System.out.println("Gracias por usar el sistema, esperamos verlo pronto");
                    continuar_menu = false;
                    break;

                default:
                    System.out.println("\nLa opcion ingresada no pertenece a las que el sistema reconoce. Intentelo de nuevo");
                    break;
            }

        }
    }
}
