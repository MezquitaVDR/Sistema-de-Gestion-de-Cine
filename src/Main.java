public class Main {
    public static void main(String[] args) {
        // Se crea una instancia del sistema de gestion del cine
        SistemaCine sistema = new SistemaCine();

        // Se crea una instancia de la consola para interactuar con el sistema
        ConsolaCine consola = new ConsolaCine(sistema);

        // Se inicia la ejecucion de la consola, lo que permite al usuario interactuar con el sistema de cine
        consola.ejecutar();
    }
}


