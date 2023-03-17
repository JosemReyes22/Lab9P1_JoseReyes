/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9p1_josereyes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author josem
 */
public class Ventanilla {
 private ArrayList<Integer> colaPrioridad;
    private ArrayList<Integer> colaRegular;
    private int[] ventanilla;
    private char[] clientesEnVentanilla;

    public Ventanilla(int tamano) {
        colaPrioridad = new ArrayList<Integer>();
        colaRegular = new ArrayList<Integer>();
        ventanilla = new int[tamano];
        clientesEnVentanilla = new char[tamano];
        for (int i = 0; i < tamano; i++) {
            ventanilla[i] = 0;
            clientesEnVentanilla[i] = ' ';
        }
    }

    public void agregarClienteRegular(int tiempo) {
        colaRegular.add(tiempo);
    }

    public void agregarClientePreferencial(int tiempo) {
        colaPrioridad.add(tiempo);
    }

    public int encontrarVentanillaDisponible() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public void actualizarVentanillas() {
        for (int i = 0; i < ventanilla.length; i++) {
            if (ventanilla[i] > 0) {
                ventanilla[i]--;
                if (ventanilla[i] == 0) {
                    clientesEnVentanilla[i] = ' ';
                }
            }
        }
    }

    public void correrSimulacion(int tiempoTotal) {
        int iteraciones = 0;
        while (iteraciones < tiempoTotal && (!colaPrioridad.isEmpty() || !colaRegular.isEmpty())) {
            int ventanillaDisponible = encontrarVentanillaDisponible();
            while (ventanillaDisponible != -1 && !colaPrioridad.isEmpty()) {
                int tiempoCliente = colaPrioridad.get(0);
                colaPrioridad.remove(0);
                ventanilla[ventanillaDisponible] = tiempoCliente;
                clientesEnVentanilla[ventanillaDisponible] = 'P';
                ventanillaDisponible = encontrarVentanillaDisponible();
            }
            while (ventanillaDisponible != -1 && !colaRegular.isEmpty()) {
                int tiempoCliente = colaRegular.get(0);
                colaRegular.remove(0);
                ventanilla[ventanillaDisponible] = tiempoCliente;
                clientesEnVentanilla[ventanillaDisponible] = 'R';
                ventanillaDisponible = encontrarVentanillaDisponible();
            }
            actualizarVentanillas();
            iteraciones++;
        }
    }

    public void imprimirEstado() {
        for (int i = 0; i < ventanilla.length; i++) {
            System.out.print("Ventanilla " + (i + 1) + ": ");
            if (clientesEnVentanilla[i] == 'P') {
                System.out.print("Prioridad");
            } else if (clientesEnVentanilla[i] == 'R') {
                System.out.print("Regular");
            } else {
                System.out.print("Vacia");
            }
            System.out.println(" (Tiempo restante: " + ventanilla[i] + ")");
        }
        System.out.println("Cola de prioridad: " + colaPrioridad);
        System.out.println("Cola regular: " + colaRegular);
    }
}

