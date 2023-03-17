/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab9p1_josereyes;


import java.util.Random;
import java.util.Scanner;
        

/**
 *
 * @author josem
 */
public class Lab9P1_JoseReyes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Ingrese la cantidad de clientes a generar: ");
        int cantidadClientes = scanner.nextInt();

        System.out.print("Ingrese el número de escritorios disponibles: ");
        int cantidadEscritorios = scanner.nextInt();

        System.out.print("Ingrese el tiempo total que le tomará a todos los clientes realizar sus consultas: ");
        int tiempoTotal = scanner.nextInt();

        Ventanilla ventanilla = new Ventanilla(cantidadEscritorios);

        // Generación aleatoria de clientes y adición a las colas
        for (int i = 0; i < cantidadClientes; i++) {
            int tiempoConsulta = random.nextInt(5) + 1; // tiempo de 1 a 5
            if (random.nextFloat() < 0.5) {
                ventanilla.agregarClienteRegular(tiempoConsulta);
            } else {
                ventanilla.agregarClientePreferencial(tiempoConsulta);
            }
        }

        // Correr la simulación
        ventanilla.correrSimulacion(tiempoTotal);
    }
}
