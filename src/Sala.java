import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase Sala que representa una sala de cine.
 */
public class Sala implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Sala.class.getName());

    private final int numero;
    private final int capacidad;
    private final String tipo; // Ejemplo: "2D", "3D", "VIP"
    private final List<Funcion> funciones;

    // Constructor de la clase
    public Sala(int numero, int capacidad, String tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.funciones = new ArrayList<>();
    }

    // Metodo para agregar una funcion a la sala
    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
        LOGGER.info("✅ Funcion agregada en Sala " + numero);
    }

    // Metodos getter
    public int getNumero() { return numero; }
    public int getCapacidad() { return capacidad; }
    public String getTipo() { return tipo; }
    public List<Funcion> getFunciones() { return new ArrayList<>(funciones); } // Retorna copia de la lista
}
