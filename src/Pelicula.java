import java.io.Serializable;
/**
 * Clase Pelicula que representa una pelicula en el sistema de cine.
 * Implementa Serializable para permitir la persistencia de datos.
 */
public class Pelicula implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String titulo;
    private final int duracion; // Duracion en minutos
    private final String clasificacion; // Ejemplo: "PG-13"
    private final String genero; // Ejemplo: "Accion"

    // Constructor de la clase
    public Pelicula(String titulo, int duracion, String clasificacion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.genero = genero;
    }
    // Metodos getter para acceder a los atributos
    public String getTitulo() { return titulo; }
    public int getDuracion() { return duracion; }
    public String getClasificacion() { return clasificacion; }
    public String getGenero() { return genero; }

    // Metodo para mostrar la ficha de la pelicula

    public void mostrarFicha() {
        System.out.println("Título: " + titulo);
        System.out.println("Duración: " + duracion + " min");
        System.out.println("Clasificación: " + clasificacion);
        System.out.println("Género: " + genero);
    }
    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + duracion + " min, " + clasificacion + ")";
    }

}
