package um.edu.uy;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in); //uso final

    public static int menuPrincipal(){ // el static significa que el metodo es de la clase entera, no de un objeto en particular entonces se llama sin instanciar antes un objeto y solo con Menu.menuPrincipal();

        System.out.println("Seleccione la opción que desee:");
        System.out.println("1. Carga de datos");
        System.out.println("2. Ejecutar consultas");
        System.out.println("3. Salir");

        return validarResultadoScanner(3);
    }

    public static int menuDeConsultas(){
        System.out.println("1. Top 5 de las películas que más calificaciones por idioma.");
        System.out.println("2. Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.");
        System.out.println("3. Top 5 de las colecciones que más ingresos generaron.");
        System.out.println("4. Top 10 de los directores que mejor calificación tienen.");
        System.out.println("5. Actor con más calificaciones recibidas en cada mes del año.");
        System.out.println("6. Usuarios con más calificaciones por género");
        System.out.println("7. Salir");

        return validarResultadoScanner(7);
    }

    private static int validarResultadoScanner(int max){

        boolean terminado = false;
        int resultado = 0;

        while(!terminado){

            resultado = scanner.nextInt();

            if(resultado > max || resultado < 1){
                System.out.println("La opción ingresada no es valida. Inente nuevamente");
            } else {
                terminado = true;
            }
        }

        return resultado;

    }
}
