import java.io.Serializable;
/**
 * Clase Reserva que representa una reserva de boletos para una funcion.
 */
public class Reserva implements Serializable  {
    private Cliente cliente;
    private Funcion funcion;
    private int asientos;

    // Constructor de la clase
    public Reserva(Cliente cliente, Funcion funcion, int asientos) {
        if (asientos <= 0) throw new IllegalArgumentException("Cantidad de asientos inválida.");
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
    }

    // Metodo para cancelar la reserva
    public void cancelar() {
        funcion.liberarAsientos(asientos);
    }

    // Metodos getter
    public Funcion getFuncion() { return funcion; }
    public int getAsientos() { return asientos; }
    public Cliente getCliente() { return cliente; }

    @Override
    public String toString() {
        return "Reserva de " + asientos + " asiento(s) para '" +
                funcion.getPelicula().getTitulo() + "' en sala " +
                funcion.getSala().getNumero() + " a las " + funcion.getHorario();
    }
}
