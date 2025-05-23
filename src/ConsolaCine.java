import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ConsolaCine {
    private SistemaCine sistema;
    private Scanner scanner;

    public ConsolaCine(SistemaCine sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 : registrarPelicula();
                break;
                case 2 : registrarSala();
                break;
                case 3 : registrarCliente();
                break;
                case 4 : programarFuncion();
                break;
                case 5 : hacerReserva();
                break;
                case 6 : cancelarReserva();
                break;
                case 7 : mostrarCartelera();
                break;
                case 8 : listarReservas();
                break;
                case 9 :List<Cliente> clientes = sistema.getClientes();

                    if (clientes.isEmpty()) {
                        System.out.println("❌ No hay clientes registrados.");
                        break;
                    }

                    System.out.println("Seleccione el cliente:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + ". " + clientes.get(i).getNombre());
                    }
                    int indexCliente = scanner.nextInt();
                    Cliente cliente = clientes.get(indexCliente);

                    if (cliente.getReservas().isEmpty()) {
                        System.out.println("❌ Este cliente no tiene reservas.");
                        break;
                    }

                    System.out.println("Seleccione la reserva a cancelar:");
                    List<Reserva> reservas = cliente.getReservas();
                    for (int i = 0; i < reservas.size(); i++) {
                        System.out.println(i + ". " + reservas.get(i));
                    }
                    int indexReserva = scanner.nextInt();
                    Reserva reserva = reservas.get(indexReserva);

                    cliente.cancelarReserva(reserva); // ✅ Cancelación y liberación de asientos
                    break;

                case 10 : System.out.println("Saliendo...");
                break;
                default : System.out.println("Opción inválida.");
                break;
            }
        } while (opcion != 9);
    }

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
        System.out.println("9. Cancelar reserva");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void registrarPelicula() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Duración (minutos): ");
        int duracion = Integer.parseInt(scanner.nextLine());
        System.out.print("Clasificación: ");
        String clasificacion = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();

        sistema.registrarPelicula(new Pelicula(titulo, duracion, clasificacion, genero));
        System.out.println("Película registrada.");
    }

    private void registrarSala() {
        System.out.print("Número de sala: ");
        int numero = Integer.parseInt(scanner.nextLine());
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Tipo (2D, 3D, VIP): ");
        String tipo = scanner.nextLine();

        sistema.registrarSala(new Sala(numero, capacidad, tipo));
        System.out.println("Sala registrada.");
    }

    private void registrarCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo del cliente: ");
        String correo = scanner.nextLine();

        sistema.registrarCliente(new Cliente(nombre, correo));
        System.out.println("Cliente registrado.");
    }

    private void programarFuncion() {
        List<Pelicula> peliculas = sistema.getPeliculas();
        List<Sala> salas = sistema.getSalas();

        if (peliculas.isEmpty() || salas.isEmpty()) {
            System.out.println("Debe registrar al menos una película y una sala.");
            return;
        }

        System.out.println("Seleccione una película:");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println(i + ". " + peliculas.get(i).getTitulo());
        }
        int pIndex = Integer.parseInt(scanner.nextLine());

        System.out.println("Seleccione una sala:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(i + ". Sala " + salas.get(i).getNumero());
        }
        int sIndex = Integer.parseInt(scanner.nextLine());

        System.out.print("Horario (ej. 19:00): ");
        String horario = scanner.nextLine();

        sistema.programarFuncion(peliculas.get(pIndex), salas.get(sIndex), horario);
        System.out.println("Función programada.");
    }

    private void hacerReserva() {
        List<Cliente> clientes = sistema.getClientes();
        List<Sala> salas = sistema.getSalas();

        if (clientes.isEmpty() || salas.isEmpty()) {
            System.out.println("Debe haber al menos un cliente y funciones programadas.");
            return;
        }

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

        if (todas.isEmpty()) {
            System.out.println("No hay funciones disponibles.");
            return;
        }

        System.out.println("Seleccione función:");
        for (int i = 0; i < todas.size(); i++) {
            Funcion f = todas.get(i);
            System.out.println(i + ". " + f.getPelicula().getTitulo() + " - Sala " +
                    f.getSala().getNumero() + " - " + f.getHorario() +
                    " - Asientos disponibles: " + f.getAsientosDisponibles());
        }
        int fIndex = Integer.parseInt(scanner.nextLine());
        Funcion funcion = todas.get(fIndex);

        System.out.print("Cantidad de asientos: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        try {
            sistema.hacerReserva(cliente, funcion, cantidad);
            System.out.println("Reserva exitosa.");
        } catch (Exception e) {
            System.out.println("Error al reservar: " + e.getMessage());
        }
    }

    private void cancelarReserva() {
        List<Cliente> clientes = sistema.getClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Seleccione cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }
        int cIndex = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clientes.get(cIndex);

        List<Reserva> reservas = cliente.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("Este cliente no tiene reservas.");
            return;
        }

        System.out.println("Seleccione reserva a cancelar:");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println(i + ". " + reservas.get(i));
        }
        int rIndex = Integer.parseInt(scanner.nextLine());
        Reserva reserva = reservas.get(rIndex);

        sistema.cancelarReserva(cliente, reserva);
        System.out.println("Reserva cancelada.");
    }

    private void mostrarCartelera() {
        for (Sala sala : sistema.getSalas()) {
            System.out.println("Sala " + sala.getNumero() + " (" + sala.getTipo() + "):");
            for (Funcion f : sala.getFunciones()) {
                System.out.println("  - " + f.getPelicula().getTitulo() + " a las " + f.getHorario() +
                        " | Asientos disponibles: " + f.getAsientosDisponibles());
            }
        }
    }

    private void listarReservas() {
        List<Cliente> clientes = sistema.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Seleccione cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNombre());
        }
        int cIndex = Integer.parseInt(scanner.nextLine());
        Cliente cliente = clientes.get(cIndex);

        List<Reserva> reservas = cliente.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("No tiene reservas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }
}