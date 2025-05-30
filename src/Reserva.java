import java.io.Serializable;

public class Reserva implements Serializable  {
    private Cliente cliente;
    private Funcion funcion;
    private int asientos;

    public Reserva(Cliente cliente, Funcion funcion, int asientos) {
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
    }

    public void cancelar() {
        funcion.cancelarAsientos(asientos);
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public int getAsientos() {
        return asientos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Reserva de " + asientos + " asiento(s) para '" +
                funcion.getPelicula().getTitulo() + "' en sala " +
                funcion.getSala().getNumero() + " a las " + funcion.getHorario();
    }
}
