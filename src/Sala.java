import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
/**
 * Clase Sala que representa una sala de cine.
 */
public class Sala implements Serializable {

    private int numero;
    private int capacidad;
    private String tipo; // 2D, 3D, VIP
    private List<Funcion> funciones;

    // Constructor de la clase
    public Sala(int numero, int capacidad, String tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.funciones = new ArrayList<>();
    }

    // Metodo para agregar una funcion a la sala
    public void agregarFuncion(Funcion funcion) {
        for (Funcion f : funciones) {
            if (f.getHorario().equals(funcion.getHorario())) {
                System.out.println("⚠️ Ya existe una función en ese horario.");
                return;
            }
        }
        funciones.add(funcion);
    }

    // Metodos getter
    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }
    @Override
    public String toString() {
        return "Sala " + numero + " (" + tipo + ") - Capacidad: " + capacidad;
    }

}
