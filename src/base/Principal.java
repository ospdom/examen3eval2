package base;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Principal {
    private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

    static {
        try {
            FileHandler handler = new FileHandler("selecciones.log" , true);
            handler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(handler);
        } catch (Exception e) {
            LOGGER.fine("No se pudo agregar el manejador de archivo para el registro");
        }
    }

    private static Scanner teclado = new Scanner(System.in);

    private static boolean permiso = false;

    private static boolean compuertasVerificadas = false;

	private static Handler FileHandler;

    public static void main(String[] args) {
        System.out.println("Este programa lee el nivel de agua de una presa y permite abrir compuertas si tenemos permiso (el nivel es superior a 50) y las compuertas están verificadas.");

        int nivel = leerNivelAgua();

        mostrarMenu(nivel);
        

    }

    private static void mostrarMenu(int nivel) {
        int opcion = 0;
        do {
            System.out.println();
            System.out.println("Nivel del agua: " + nivel);
            System.out.println();
            System.out.println("ACCIONES: ");
            System.out.println();
            System.out.println("1. Nueva lectura del nivel de agua.");
            System.out.println("2. Abrir compuertas. Requiere:");
            System.out.println("    3. Solicitar permiso. Estado: " + (permiso ? "CONCEDIDO" : "NO CONCEDIDO"));
            System.out.println("    4. Verificar compuertas. Estado: " + (compuertasVerificadas ? "VERIFICADAS" : "NO VERIFICADAS"));
            System.out.println("5. Salir");
            System.out.println();
            System.out.print("Introduce opción: ");
            opcion = teclado.nextInt();
            
            
            switch (opcion) {
                case 1:
                    nivel = leerNivelAgua();
                    permiso = false;
                    compuertasVerificadas = false;
                    LOGGER.fine("El usuario seleccionó la opción 1: Nueva lectura del nivel de agua.");
                    break;
                case 2:
                    if (abrirCompuertas()) {
                        System.out.println();
                        System.out.print("¡Compuertas abiertas!");
                        LOGGER.fine("El usuario seleccionó la opción 2: Abrir compuertas.");
                    } else {
                        System.out.println();
                        System.out.print("No se cumplen las condiciones para abrir compuertas.");
                    }
                    break;
                case 3:
                    permiso = solicitarPermiso(nivel);
                    if (!permiso) {
                        System.out.println();
                        System.out.print("El permiso solamente se concede si el nivel del agua es superior a 50.");
                    }
                    LOGGER.fine("El usuario seleccionó la opción 3: Solicitar permiso.");
                    break;
			case 4:
				compuertasVerificadas = verificarCompuertas();
				if(compuertasVerificadas) {
					System.out.println();
					System.out.print("�Compuertas verificadas!");
				}  LOGGER.fine("El usuario seleccionó la opción 4: Verificar compuertas.");
                break;                
				
			default:
				break;
				
			}
            
		} while (opcion != 5);
	}
    
    

	public static int leerNivelAgua() {

		permiso = false;
		return (int) Math.round(Math.random() * 100);
	}

	public static boolean abrirCompuertas() {
		if (permiso && compuertasVerificadas) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 * @param nivel Este método solicita un permiso basado en un nivel dado como argumento y devuelve un valor booleano que indica si se debe otorgar el permiso o no.  si el nivel es mayor que 50, el método devuelve true, eso otorga el permiso. Si el nivel es menor o igual a 50, el método devuelve false y no da el permiso
	 * @return
	 * @author christian
	 */
	public static boolean solicitarPermiso(int nivel) {
		if (nivel > 50) {
			return true;
		}else {
			return false;
		}
		
	}
	static boolean verificarCompuertas() {		
		return true;
	}
	

}
