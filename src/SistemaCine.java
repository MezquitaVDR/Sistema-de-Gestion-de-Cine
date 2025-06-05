import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * La clase SistemaCine representa el sistema de gestion de un cine,
 * permitiendo el registro de peliculas, salas, clientes, funciones y reservas.
 * Implementa Serializable para permitir la persistencia de datos.
 */
public class SistemaCine implements Serializable {
    private List<Reserva> reservas = new ArrayList<>(); // Lista de reservas realizadas
    private List<Pelicula> peliculas = new ArrayList<>(); // Lista de peliculas disponibles
    private List<Sala> salas = new ArrayList<>(); // Lista de salas del cine
    private List<Cliente> clientes = new ArrayList<>(); // Lista de clientes registrados

    /**
     * Metodo para registrar una nueva pelicula en el sistema.
     * @param p Pelicula a registrar.
     */
    public void registrarPelicula(Pelicula p) {
        peliculas.add(p);
    }

    /**
     * Metodo para registrar una nueva sala en el sistema.
     * @param s Sala a registrar.
     */
    public void registrarSala(Sala s) {
        salas.add(s);
    }

    /**
     * Metodo para registrar un nuevo cliente en el sistema.
     * @param c Cliente a registrar.
     */
    public void registrarCliente(Cliente c) {
        clientes.add(c);
    }

    /**
     * Metodo para obtener la lista de peliculas registradas.
     * @return Lista de peliculas.
     */
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    /**
     * Metodo para obtener la lista de salas registradas.
     * @return Lista de salas.
     */
    public List<Sala> getSalas() {
        return salas;
    }

    /**
     * Metodo para obtener la lista de clientes registrados.
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Metodo para programar una nueva funcion en una sala.
     * @param p Pelicula a proyectar.
     * @param s Sala donde se proyectara.
     * @param horario Horario de la funcion.
     */
    public void programarFuncion(Pelicula p, Sala s, String horario) {
        Funcion f = new Funcion(p, s, horario);
        s.agregarFuncion(f); // Se agrega la funcion a la sala correspondiente
    }

    /**
     * Metodo para realizar una reserva de asientos para una funcion.
     * @param cliente Cliente que realiza la reserva.
     * @param funcion Funcion para la que se realiza la reserva.
     * @param asientos Cantidad de asientos a reservar.
     */
    public void hacerReserva(Cliente cliente, Funcion funcion, int asientos) {
        if (asientos <= funcion.getAsientosDisponibles()) { // Se verifica la disponibilidad de asientos
            funcion.reservarAsientos(asientos); // Se descuentan los asientos reservados
            Reserva r = new Reserva(cliente, funcion, asientos); // Se crea la reserva
            cliente.agregarReserva(r); // Se asocia la reserva con el cliente
        }
    }

    /**
     * Metodo para cancelar una reserva realizada por un cliente.
     * @param cliente Cliente que cancela la reserva.
     * @param reserva Reserva a cancelar.
     */
    public void cancelarReserva(Cliente cliente, Reserva reserva) {
        reserva.cancelar(); // Se liberan los asientos reservados
        cliente.cancelarReserva(reserva); // Se elimina la reserva del cliente
    }

    /**
     * Metodo para guardar los datos del sistema en un archivo para su persistencia.
     * @param archivo Nombre del archivo donde se guardaran los datos.
     */
    public void guardarDatos(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
            System.out.println("✅ Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar datos: " + e.getMessage());
        }
    }

    /**
     * Metodo para mostrar las reservas asociadas a un cliente.
     * @param cliente Cliente cuyas reservas se desean mostrar.
     */
    public void mostrarReservas(Cliente cliente) {
        List<Reserva> reservas = cliente.getReservas();

        if (reservas.isEmpty()) {
            System.out.println("Este cliente no tiene reservas.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r); // Se muestran las reservas
                System.out.println("-----------");
            }
        }
    }

    /**
     * Metodo para mostrar la cartelera actual del cine, con todas las funciones programadas.
     */
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

    /**
     * Metodo estatico para cargar datos previamente guardados desde un archivo.
     * @param archivo Nombre del archivo a cargar.
     * @return Instancia de SistemaCine con los datos recuperados, o una nueva instancia si la carga falla.
     */
    public static SistemaCine cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (SistemaCine) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo cargar datos, se creara un nuevo sistema.");
            return new SistemaCine();
        }
    }
}
