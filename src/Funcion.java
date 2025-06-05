import java.io.Serializable;

/**
 * Clase Funcion que representa una funcion de cine.
 * Implementa Serializable para permitir su almacenamiento y transferencia.
 */
public class Funcion implements Serializable {
    private Pelicula pelicula; // Pelicula que se proyecta en la funcion
    private Sala sala; // Sala en la que se proyecta la pelicula
    private String horario; // Horario en el que se proyectara la pelicula
    private int asientosDisponibles; // Cantidad de asientos disponibles para la funcion

    /**
     * Constructor de la clase Funcion.
     * @param pelicula Pelicula que se proyectara en la funcion.
     * @param sala Sala donde se proyectara la pelicula.
     * @param horario Horario en el que se realizara la funcion.
     */
    public Funcion(Pelicula pelicula, Sala sala, String horario) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
        this.asientosDisponibles = sala.getCapacidad(); // Inicializa los asientos disponibles con la capacidad total de la sala
    }

    /**
     * Metodo para reservar una cantidad de asientos en la funcion.
     * @param cantidad Numero de asientos a reservar.
     */
    public void reservarAsientos(int cantidad) {
        if (cantidad <= asientosDisponibles) {
            this.asientosDisponibles -= cantidad; // Reduce la cantidad de asientos disponibles
        } else {
            System.out.println("❌ No hay suficientes asientos disponibles.");
        }
    }

    /**
     * Metodo para liberar una cantidad de asientos en la funcion.
     * @param cantidad Numero de asientos a liberar.
     */
    public void liberarAsientos(int cantidad) {
        if ((asientosDisponibles + cantidad) <= sala.getCapacidad()) {
            this.asientosDisponibles += cantidad; // Aumenta la cantidad de asientos disponibles
        } else {
            System.out.println("❌ No puedes liberar mas asientos de los que hay en la sala.");
        }
    }

    /**
     * Metodo para cancelar la reserva de asientos y devolverlos al total disponible.
     * @param cantidad Numero de asientos cuya reserva sera cancelada.
     */
    public void cancelarAsientos(int cantidad) {
        liberarAsientos(cantidad); // Se utiliza el metodo para liberar asientos
    }

    /**
     * Metodo para obtener la cantidad de asientos disponibles en la funcion.
     * @return Numero de asientos actualmente disponibles.
     */
    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    /**
     * Metodo para obtener la cantidad de asientos ocupados.
     * @return Numero de asientos ocupados.
     */
    public int getAsientosOcupados() {
        return sala.getCapacidad() - asientosDisponibles; // Calcula los asientos ocupados
    }

    /**
     * Metodo para mostrar la informacion de disponibilidad de asientos.
     */
    public void mostrarDisponibilidadAsientos() {
        System.out.println("🎟️ Asientos disponibles: " + asientosDisponibles);
        System.out.println("🛑 Asientos ocupados: " + getAsientosOcupados());
    }

    /**
     * Metodo para obtener el horario de la funcion.
     * @return Horario en que se proyectara la pelicula.
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Metodo para obtener la pelicula que se proyecta en la funcion.
     * @return Objeto de la clase Pelicula.
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Metodo para obtener la sala donde se proyecta la funcion.
     * @return Objeto de la clase Sala.
     */
    public Sala getSala() {
        return sala;
    }
}