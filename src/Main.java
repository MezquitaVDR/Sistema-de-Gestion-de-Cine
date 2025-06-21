public class Main {
    public static void main(String[] args) {
        // Nombre del archivo donde se almacenan los datos del sistema de cine
        String archivo = "cine.dat";
        // Carga los datos del archivo en una instancia de SistemaCine
        SistemaCine sistema = SistemaCine.cargarDatos(archivo);
        // Crea una consola de cine para interactuar con el sistema
        ConsolaCine consola = new ConsolaCine(sistema);
        // Ejecuta la consola para permitir la interacción con el usuario
        consola.ejecutar();
        // Guarda los datos del sistema nuevamente en el archivo
        sistema.guardarDatos(archivo);
    }
}