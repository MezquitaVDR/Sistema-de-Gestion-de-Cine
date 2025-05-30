import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SistemaCine implements Serializable {
    private List<Reserva> reservas = new ArrayList<>();
    private List<Pelicula> peliculas = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public void registrarPelicula(Pelicula p) {
        peliculas.add(p);
    }

    public void registrarSala(Sala s) {
        salas.add(s);
    }

    public void registrarCliente(Cliente c) {
        clientes.add(c);
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void programarFuncion(Pelicula p, Sala s, String horario) {
        Funcion f = new Funcion(p, s, horario);
        s.agregarFuncion(f);
    }

    public void hacerReserva(Cliente cliente, Funcion funcion, int asientos) {
        if (asientos <= funcion.getAsientosDisponibles()) {
            funcion.reservarAsientos(asientos);
            Reserva r = new Reserva(cliente, funcion, asientos);
            cliente.agregarReserva(r);
        }
    }

    public void cancelarReserva(Cliente cliente, Reserva reserva) {
        reserva.cancelar();
        cliente.cancelarReserva(reserva);

    }

    public void guardarDatos(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
            System.out.println("✅ Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar datos: " + e.getMessage());
        }
    }
    public void mostrarReservas(Cliente cliente) {
        List<Reserva> reservas = cliente.getReservas();

        if (reservas.isEmpty()) {
            System.out.println("Este cliente no tiene reservas.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r); // o r.mostrarReserva() si tienes uno
                System.out.println("-----------");
            }
        }
    }
    public void mostrarCartelera() {
        if (salas.isEmpty()) {
            System.out.println("❌ No hay salas registradas.");
            return;
        }

        for (Sala sala : salas) {
            List<Funcion> funciones = sala.getFunciones();
            if (!funciones.isEmpty()) {
                for (Funcion f : funciones) {
                    Pelicula p = f.getPelicula();
                    System.out.println("🎬 " + p.getTitulo() +
                            " | Sala: " + sala.getNumero() +
                            " (" + sala.getTipo() + ")" +
                            " | Hora: " + f.getHorario() +
                            " | Asientos disponibles: " + f.getAsientosDisponibles());
                }
            }
        }
    }

    public static SistemaCine cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (SistemaCine) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo cargar datos, se creará un nuevo sistema.");
            return new SistemaCine();
        }
    }
}