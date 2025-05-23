public class Main {
    public static void main(String[] args) {
        String archivo = "cine.dat";
        SistemaCine sistema = SistemaCine.cargarDatos(archivo);
        ConsolaCine consola = new ConsolaCine(sistema);
        consola.ejecutar();
        sistema.guardarDatos(archivo);
    }
}