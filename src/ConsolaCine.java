import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
/**
 * La clase ConsolaCine permite la interaccion del usuario con el sistema de gestion de cine.
 * Proporciona un menu con opciones para registrar peliculas, salas, clientes, funciones y reservas.
 */
public class ConsolaCine {
    private SistemaCine sistema;// Instancia del sistema de cine
    private Scanner scanner;// Scanner para leer la entrada del usuario

    /**
     * Constructor de la clase ConsolaCine.
     * sistema Instancia del sistema de cine que gestionara los datos.
     */
    public ConsolaCine(SistemaCine sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Metodo principal que ejecuta el menu de opciones en un bucle.
     */
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();// Muestra el menu al usuario
            opcion = Integer.parseInt(scanner.nextLine());// Captura la opcion elegida

            switch (opcion) {
                case 1 : registrarPelicula();// Registra una nueva pelicula
                break;
                case 2: registrarSala(); // Registra una nueva sala
                    break;
                case 3: registrarCliente(); // Registra un nuevo cliente
                    break;
                case 4: programarFuncion(); // Programa una nueva funcion de cine
                    break;
                case 5: hacerReserva(); // Realiza una reserva de asientos
                    break;
                case 6: cancelarReserva(); // Cancela una reserva existente
                    break;
                case 7: sistema.mostrarCartelera(); // Muestra la cartelera con las funciones programadas
                    break;
                case 8: listarReservas(); // Lista las reservas por cliente
                    break;
                case 9:
                    limpiarArchivo("cine.dat");
                    break;

                case 10 : System.out.println("Saliendo...");// Opcion de salida
                break;
                default : System.out.println("Opción inválida.");// Mensaje de error
                break;
            }
        } while (opcion != 10);
    }

    /**
     * Metodo para mostrar el menu de opciones al usuario.
     */
    private void mostrarMenu() {
        System.out.println("\n===== MENÚ DEL CINE =====");
        System.out.println("1. Registrar película");
        System.out.println("2. Registrar sala");
        System.out.println("3. Registrar cliente");
        System.out.println("4. Programar función");
        System.out.println("5. Hacer reserva");
        System.out.println("6. Cancelar reserva");
        System.out.println("7. Mostrar cartelera");
        System.out.println("8. Listar reservas por cliente");
        System.out.println("9. Limpiar todos los registros");

        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void registrarPelicula() {
        // Solicitar el titulo de la pelicula
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        // Solicitar la duracion en minutos y convertirla a entero
        System.out.print("Duracion (minutos): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        // Solicitar la clasificacion de la pelicula
        System.out.print("Clasificacion: ");
        String clasificacion = scanner.nextLine();

        // Solicitar el genero de la pelicula
        System.out.print("Genero: ");
        String genero = scanner.nextLine();

        // Registrar la pelicula en el sistema
        sistema.registrarPelicula(new Pelicula(titulo, duracion, clasificacion, genero));
        System.out.println("Pelicula registrada.");
    }

    private void registrarSala() {
        // Solicitar el numero de la sala
        System.out.print("Numero de sala: ");
        int numero = Integer.parseInt(scanner.nextLine());

        // Solicitar la capacidad de la sala
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());

        // Solicitar el tipo de sala (2D, 3D, VIP)
        System.out.print("Tipo (2D, 3D, VIP): ");
        String tipo = scanner.nextLine();

        // Registrar la sala en el sistema
        sistema.registrarSala(new Sala(numero, capacidad, tipo));
        System.out.println("Sala registrada.");
    }

    private void registrarCliente() {
        // Solicitar el nombre del cliente
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        // Solicitar el correo del cliente
        System.out.print("Correo del cliente: ");
        String correo = scanner.nextLine();

        // Registrar el cliente en el sistema
        sistema.registrarCliente(new Cliente(nombre, correo));
        System.out.println("Cliente registrado.");
    }

    private void programarFuncion() {
        // Obtener la lista de peliculas y salas disponibles en el sistema
        List<Pelicula> peliculas = sistema.getPeliculas();
        List<Sala> salas = sistema.getSalas();

        // Verificar si hay al menos una pelicula y una sala registradas
        if (peliculas.isEmpty() || salas.isEmpty()) {
            System.out.println("Debe registrar al menos una pelicula y una sala.");
            return;
        }

        // Mostrar la lista de peliculas disponibles y permitir la seleccion por indice
        System.out.println("Seleccione una pelicula:");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println(i + ". " + peliculas.get(i).getTitulo());
        }
        int pIndex = Integer.parseInt(scanner.nextLine());

        // Mostrar la lista de salas disponibles y permitir la seleccion por indice
        System.out.println("Seleccione una sala:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(i + ". Sala " + salas.get(i).getNumero());
        }
        int sIndex = Integer.parseInt(scanner.nextLine());

        // Solicitar el horario de la funcion
        System.out.print("Horario (ej. 19:00): ");
        String horario = scanner.nextLine();

        // Programar la funcion con la pelicula, la sala y el horario seleccionados
        sistema.programarFuncion(peliculas.get(pIndex), salas.get(sIndex), horario);
        System.out.println("Funcion programada.");
    }

    private void hacerReserva() {
        // Obtener la lista de clientes y salas disponibles
        List<Cliente> clientes = sistema.getClientes();
        List<Sala> salas = sistema.getSalas();

        // Verificar que haya clientes y funciones disponibles
        if (clientes.isEmpty() || salas.isEmpty()) {
            System.out.println("Debe haber al menos un cliente y funciones programadas.");
            return;
        }

        // Mostrar la lista de clientes y permitir la seleccion por indice
        System.out.println("Seleccione cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }
        int cIndex = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clientes.get(cIndex);

        // Recoger todas las funciones disponibles
        List<Funcion> todas = salas.stream()
                .flatMap(s -> s.getFunciones().stream())
                .toList();

        // Verificar que haya funciones disponibles
        if (todas.isEmpty()) {
            System.out.println("No hay funciones disponibles.");
            return;
        }

        // Mostrar la lista de funciones y permitir la seleccion por indice
        System.out.println("Seleccione funcion:");
        for (int i = 0; i < todas.size(); i++) {
            Funcion f = todas.get(i);
            System.out.println(i + ". " + f.getPelicula().getTitulo() + " - Sala " +
                    f.getSala().getNumero() + " - " + f.getHorario() +
                    " - Asientos disponibles: " + f.getAsientosDisponibles());
        }
        int fIndex = Integer.parseInt(scanner.nextLine());
        Funcion funcion = todas.get(fIndex);

        // Solicitar la cantidad de asientos que desea reservar
        System.out.print("Cantidad de asientos: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        // Intentar hacer la reserva, manejando posibles excepciones
        try {
            sistema.hacerReserva(cliente, funcion, cantidad);
            System.out.println("Reserva exitosa.");
        } catch (Exception e) {
            System.out.println("Error al reservar: " + e.getMessage());
        }
    }
    public static void limpiarArchivo(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo, false)) {
            writer.write(""); // Borra el contenido del archivo
            System.out.println("Archivo '" + nombreArchivo + "' limpiado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al limpiar el archivo: " + e.getMessage());
        }
    }

    private void cancelarReserva() {
        // Obtener la lista de clientes registrados en el sistema
        List<Cliente> clientes = sistema.getClientes();

        // Validar si hay clientes en el sistema
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Mostrar la lista de clientes y permitir la seleccion por indice
        System.out.println("Seleccione cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }
        int cIndex = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clientes.get(cIndex);

        // Obtener las reservas del cliente seleccionado
        List<Reserva> reservas = cliente.getReservas();

        // Validar si el cliente tiene reservas activas
        if (reservas.isEmpty()) {
            System.out.println("Este cliente no tiene reservas.");
            return;
        }

        // Mostrar las reservas disponibles y permitir la seleccion por indice
        System.out.println("Seleccione reserva a cancelar:");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println(i + ". " + reservas.get(i));
        }
        int rIndex = Integer.parseInt(scanner.nextLine());
        Reserva reserva = reservas.get(rIndex);

        // Cancelar la reserva seleccionada
        sistema.cancelarReserva(cliente, reserva);
        System.out.println("Reserva cancelada.");
    }

    private void listarReservas() {
        // Obtener la lista de clientes registrados en el sistema
        List<Cliente> clientes = sistema.getClientes();

        // Validar si hay clientes disponibles
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Mostrar la lista de clientes y permitir la seleccion por indice
        System.out.println("Seleccione cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }
        int cIndex = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clientes.get(cIndex);

        // Obtener las reservas del cliente seleccionado
        List<Reserva> reservas = cliente.getReservas();

        // Validar si el cliente tiene reservas activas
        if (reservas.isEmpty()) {
            System.out.println("No tiene reservas.");
        } else {
            // Mostrar las reservas del cliente
            reservas.forEach(System.out::println);
        }
    }
}