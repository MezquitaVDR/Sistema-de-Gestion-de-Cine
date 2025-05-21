import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Cliente implements Serializable  {
    private String nombre;
    private String correo;
    private List<Reserva> reservas;

    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String getNombre() {
        return nombre;
    }
}
