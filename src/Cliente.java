import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Clase Cliente que representa a un usuario del sistema de gestion de cine.
 * Implementa Serializable para permitir su almacenamiento y transferencia.
 */
public class Cliente implements Serializable {
    private String nombre;  // Nombre del cliente
    private String correo;  // Correo electronico del cliente
    private List<Reserva> reservas; // Lista de reservas realizadas por el cliente

    /**
     * Constructor de la clase Cliente.
     * @param nombre Nombre del cliente.
     * @param correo Correo electronico del cliente.
     */
    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.reservas = new ArrayList<>();
    }

    /**
     * Metodo para agregar una reserva a la lista de reservas del cliente.
     * @param reserva La reserva a agregar.
     */
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    /**
     * Metodo para cancelar una reserva del cliente.
     * Si la reserva existe, se eliminan los asientos reservados en la funcion correspondiente.
     * @param r Reserva a cancelar.
     */
    public void cancelarReserva(Reserva r) {
        if (reservas.remove(r)) { // Si la reserva esta en la lista, se elimina
            r.getFuncion().liberarAsientos(r.getAsientos()); // Libera los asientos reservados
            System.out.println("✅ Reserva cancelada y asientos liberados.");
        } else {
            System.out.println("❌ No se encontro la reserva para cancelar.");
        }
    }

    /**
     * Metodo para obtener la lista de reservas del cliente.
     * @return Lista de reservas.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Metodo para obtener el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
}
