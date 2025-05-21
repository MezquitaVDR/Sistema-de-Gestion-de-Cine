import java.io.Serializable;

public class Funcion implements Serializable
{
    private Pelicula pelicula;
    private Sala sala;
    private String horario;
    private int asientosDisponibles;

    public Funcion(Pelicula pelicula, Sala sala, String horario) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
        this.asientosDisponibles = sala.getCapacidad();
    }

    public void reservarAsientos(int cantidad) {
        if (cantidad <= asientosDisponibles) {
            asientosDisponibles -= cantidad;
        } else {
            throw new IllegalArgumentException("No hay suficientes asientos disponibles.");
        }
    }

    public void cancelarAsientos(int cantidad) {
        asientosDisponibles += cantidad;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public String getHorario() {
        return horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }
}
