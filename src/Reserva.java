import java.io.Serializable;

/**
 * Clase Reserva que representa una reserva de boletos para una funcion.
 */
public class Reserva implements Serializable {
    private final Cliente cliente;
    private final Funcion funcion;
    private final int asientos;

    // Constructor de la clase
    public Reserva(Cliente cliente, Funcion funcion, int asientos) {
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
    }

    // Metodo para cancelar la reserva
    public void cancelar() {
        funcion.reservarAsientos(-asientos);
    }

    // Metodos getter
    public Funcion getFuncion() { return funcion; }
    public int getAsientos() { return asientos; }
    public Cliente getCliente() { return cliente; }
}
