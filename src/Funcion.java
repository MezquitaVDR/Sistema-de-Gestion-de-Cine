import java.io.Serializable;
/**
 * Clase Funcion que representa una funcion de cine.
 * Implementa Serializable para permitir su almacenamiento y transferencia.
 */
public class Funcion implements Serializable
{
    private Pelicula pelicula;
    private Sala sala;
    private String horario;
    private int asientosDisponibles;

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
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad inválida.");
        if (cantidad > asientosDisponibles) throw new IllegalArgumentException("No hay suficientes asientos.");
        this.asientosDisponibles -= cantidad;
    }
    /**
     * Metodo para liberar una cantidad de asientos en la funcion.
     * @param cantidad Numero de asientos a liberar.
     */
    public void liberarAsientos(int cantidad) {
        this.asientosDisponibles += cantidad;
    }

    /**
     * Metodo para obtener la cantidad de asientos disponibles en la funcion.
     * @return Numero de asientos actualmente disponibles.
     */
    public int getAsientosDisponibles() {
        return asientosDisponibles;
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
    @Override
    public String toString() {
        return pelicula.getTitulo() + " - " + horario + " - Sala " + sala.getNumero();
    }
    /**
     * Metodo para obtener la sala donde se proyecta la funcion.
     * @return Objeto de la clase Sala.
     */
    public Sala getSala() {
        return sala;
    }
}
