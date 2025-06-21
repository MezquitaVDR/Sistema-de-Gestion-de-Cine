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
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad inválida.");
        if (cantidad > asientosDisponibles) throw new IllegalArgumentException("No hay suficientes asientos.");
        this.asientosDisponibles -= cantidad;
    }

    public void liberarAsientos(int cantidad) {
        this.asientosDisponibles += cantidad;
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
    @Override
    public String toString() {
        return pelicula.getTitulo() + " - " + horario + " - Sala " + sala.getNumero();
    }

    public Sala getSala() {
        return sala;
    }
}
