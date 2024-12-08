/* RETO 1 - Simulador de Viaje Interplanetario
 * El desafío consistirá en desarrollar un programa que simule la planificación y ejecución de
un viaje interplanetario, donde el usuario podrá elegir destinos, calcular la distancia y el
tiempo de viaje, y gestionar los recursos de la nave. El objetivo es aplicar conceptos
básicos de programación de forma práctica y entretenida.
*/

/*
 * ALISON LOPEZ
 * FREDY PINEDA RODRIGUEZ
 * GRUPO 18
 * 
 */
import java.util.Scanner;
import java.util.Random;

//Toda la lógica del simulador se encuentra dentro de esta clase.
public class App{

    // Datos estaticos de los planetas-arreglos para almacenar informacion 
    static String[] planetas = {"Marte", "Júpiter", "Saturno"};
    static double[] distancias = {78, 628, 1275}; // Distancias en millones de km
    static String[] descripciones = {
        "El planeta rojo, conocido por su superficie de óxido de hierro.",
        "El gigante gaseoso con una gran mancha roja.",
        "El planeta de los anillos y uno de los más bellos del sistema solar."
    };

    // Datos estaticos de las naves espaciales-arreglos para almacenar informacion
    static String[] naves = {"Columbia", "Estelar", "Discovery"};
    static double[] velocidades = {20, 50, 100}; // Velocidad en millones de km/día
    static int[] capacidades = {5, 10, 15}; // Número máximo de pasajeros

    //Punto de entrada del programa, Contiene el flujo principal de la aplicación.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables para selección del usuario-inicializacion de variables
        //El valor -1 Actúa como un marcador de "sin seleccionar".
        //El valor -1 es un número que está fuera del rango válido
        int planetaSeleccionado = -1;
        int naveSeleccionada = -1;

        // Menú principal interactivo
        //while (true): Ciclo infinito que muestra el menú principal hasta que el usuario elija salir.
        while (true) {
            System.out.println("\n\n==========================================");
            System.out.println("=== Simulador de Viaje Interplanetario ===");
            System.out.println("==========================================\n");
            System.out.println("1. Seleccionar Planeta de Destino");
            System.out.println("2. Seleccionar Nave Espacial");
            System.out.println("3. Iniciar Simulación del Viaje");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

      
        //Switch para manejar las opciones
            switch (opcion) {
                case 1 -> planetaSeleccionado = seleccionarPlaneta(scanner); //llama al metodo seleccionarPlaneta
                case 2 -> naveSeleccionada = seleccionarNave(scanner); //llama al metodo seleccionarNave
                case 3 -> {                                             // Valida que el usuario haya seleccionado un planeta y una nave antes de iniciar el viaje.
                    //&& Este operador asegura que ambas condiciones se cumplan al mismo tiempo:
                    //Si ambas condiciones son verdaderas, el programa procederá al bloque dentro del if
                    //El método iniciarViaje utiliza estos índices para calcular:
                   if (planetaSeleccionado >= 0 && naveSeleccionada >= 0) {
                        iniciarViaje(planetaSeleccionado, naveSeleccionada);
                    } else {
                        System.out.println("Primero selecciona un planeta y una nave.");
                    }
                }
                case 4 -> {
                    System.out.println("\n\n¡Gracias por usar el simulador! Hasta luego.\n\n");
                    return;
                }
                default -> System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }
    }

    // Método para seleccionar planeta
    public static int seleccionarPlaneta(Scanner scanner) {
        System.out.println("\n============================");
        System.out.println("=== Planetas Disponibles ===");
        System.out.println("============================\n");
        for (int i = 0; i < planetas.length; i++) {
            System.out.printf("%d. %s (%,.0f millones de km)\n", i + 1, planetas[i], distancias[i]);
       //%d: Imprime el número del planeta (i + 1)
       //%s: Imprime el nombre del planeta (planetas[i]).
       //%,.0f: Imprime la distancia del planeta (distancias[i]) con separadores de miles y sin decimales.
        }
        System.out.print("Selecciona un planeta (1-" + planetas.length + "): ");
        // Se resta 1 al valor ingresado porque los índices del arreglo comienzan en 0
        int seleccion = scanner.nextInt() - 1;

        if (seleccion >= 0 && seleccion < planetas.length) {
            System.out.printf("\nHas seleccionado: %s\n", planetas[seleccion]);
            System.out.println("Descripción: " + descripciones[seleccion]);
            return seleccion;
        } else {
            System.out.println("Selección inválida.");
            return -1;
        }
    }

    // Método para seleccionar nave
    public static int seleccionarNave(Scanner scanner) {
        System.out.println("\n=========================");
        System.out.println("=== Naves Disponibles ===");
        System.out.println("=========================\n");
        for (int i = 0; i < naves.length; i++) {      //Itera sobre todas las naves en el arreglo naves.
            System.out.printf("%d. %s (Velocidad: %.0f millones de km/día, Capacidad: %d pasajeros)\n",  //ejemplo de salida: Columbia (Velocidad: 20 millones de km/día, Capacidad: 5 pasajeros)
                    i + 1, naves[i], velocidades[i], capacidades[i]); //Muestra el número de la nave (que es i + 1 para iniciar desde 1 en lugar de 0).
        }
        System.out.print("Selecciona una nave (1-" + naves.length + "): ");
        int seleccion = scanner.nextInt() - 1;
             //Verifica si la opción ingresada es válida
        if (seleccion >= 0 && seleccion < naves.length) {
            System.out.printf("\nHas seleccionado: %s\n", naves[seleccion]);
            return seleccion;
        } else {
            System.out.println("Selección inválida.");
            return -1;
        }
    }

    // Método para iniciar el viaje
    public static void iniciarViaje(int planeta, int nave) {
        double distancia = distancias[planeta];
        double velocidad = velocidades[nave];
        double duracion = distancia / velocidad; // Duración en días

        System.out.printf("\n=== Simulación del Viaje ===\n");
        System.out.printf("Destino: %s\n", planetas[planeta]);
        System.out.printf("Distancia: %,.0f millones de km\n", distancia);
        System.out.printf("Duración estimada: %.2f días\n", duracion);

        Random random = new Random();

        // Simulación del progreso
        for (int i = 0; i <= 100; i += 20) {
            System.out.printf("Progreso del viaje: %d%%\n", i);
            if (i == 0) System.out.println("Inicio del viaje...");
            else if (i == 50) System.out.println("Mitad del camino...");
            else if (i == 100) System.out.println("Llegada al destino...");

            // Posibilidad de que ocurra un evento (30% de probabilidad)
            if (random.nextInt(100) < 30) {
                eventoAleatorio();
            }

            try {
                Thread.sleep(1000); // Pausa de 1 segundo para simular tiempo real
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n¡Has llegado a " + planetas[planeta] + " con éxito!\n");
        System.out.println("FIN DEL JUEGO\n");
    }

    // Método para eventos aleatorios
    public static void eventoAleatorio() {
        Random random = new Random();
        int evento = random.nextInt(5); // Genera un número aleatorio entre 0 y 4

        switch (evento) {
            case 0 -> System.out.println("Problema técnico: Uno de los motores falló. La duración del viaje aumenta un 20%.");
            case 1 -> System.out.println("Encuentro con una nave alienígena. ¡Pierdes tiempo tratando de comunicarte!");
            case 2 -> System.out.println("Impacto de meteoritos: La nave sufre daños menores, pero puedes continuar.");
            case 3 -> System.out.println("Condiciones ideales: Encuentras una corriente gravitacional que acelera el viaje.");
            case 4 -> System.out.println("Problemas de suministro: El equipo pierde parte de sus provisiones.");
            default -> System.out.println("Nada fuera de lo común. El viaje continúa sin incidentes.");
        }
    }
}

