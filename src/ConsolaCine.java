import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


 //La clase ConsolaCine permite la interaccion del usuario con el sistema de gestion de cine.
 //Proporciona un menu con opciones para registrar peliculas, salas, clientes, funciones y reservas.

public class ConsolaCine {
    private SistemaCine sistema;
    private Scanner scanner;

    // Constructor de la clase ConsolaCine.
    // sistema Instancia del sistema de cine que gestionara los datos.
    public ConsolaCine(SistemaCine sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }


    // Metodo principal que ejecuta el menu de opciones en un bucle.
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            // Captura la opcion elegida por el usuario
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
                case 7:
                    sistema.mostrarCartelera();
                    break;
                case 8 : listarReservas();
                    break;
                case 9 : System.out.println("Saliendo...");
                    break;
                default : System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 9);
    }

    // Metodo para mostrar el menu de opciones al usuario.
    private void mostrarMenu() {
        System.out.println("\n✨🎬 ¡BIENVENIDO AL CINE! 🎬✨");
        System.out.println("==============================");
        System.out.println("1️⃣ Registrar pelicula");
        System.out.println("2️⃣ Registrar sala");
        System.out.println("3️⃣ Registrar cliente");
        System.out.println("4️⃣ Programar funcion");
        System.out.println("5️⃣ Hacer reserva");
        System.out.println("6️⃣ Cancelar reserva");
        System.out.println("7️⃣ Mostrar cartelera");
        System.out.println("8️⃣ Listar reservas por cliente");
        System.out.println("9️⃣ Salir");
        System.out.println("==============================");
        System.out.print("📌 Seleccione una opcion: ");
    }
//METODOS  -registrarPelicula() -registrarSala() -registrarCliente()
// -programarFuncion() -hacerReserva() -cancelarReserva() -listarReservas()

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
