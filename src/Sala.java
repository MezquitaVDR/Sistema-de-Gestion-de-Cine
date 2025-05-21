import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class Sala implements Serializable {

    private int numero;
    private int capacidad;
    private String tipo; // 2D, 3D, VIP
    private List<Funcion> funciones;

    public Sala(int numero, int capacidad, String tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.funciones = new ArrayList<>();
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

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
}
