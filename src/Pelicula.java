import java.io.Serializable;

public class Pelicula implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private int duracion;
    private String clasificacion;
    private String genero;

    public Pelicula(String titulo, int duracion, String clasificacion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.genero = genero;
    }
    public String getTitulo() {
        return titulo;
    }

    public void mostrarFicha() {
        System.out.println("Título: " + titulo);
        System.out.println("Duración: " + duracion + " min");
        System.out.println("Clasificación: " + clasificacion);
        System.out.println("Género: " + genero);
    }
}
