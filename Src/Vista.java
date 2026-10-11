import java.util.Scanner;
public class Vista {
    private final Scanner entrada = new Scanner(System.in);
    public void mostrar(String texto) { System.out.println(texto); }
    public String leer(String mensaje) { System.out.print(mensaje); return entrada.nextLine().trim(); }
    public void menu() {
        mostrar("\n=== DEFENSA DE QTZ2 ===\n1. Listar modulos\n2. Buscar modulo\n3. Catalogo por costo\n4. Avanzar ciclo\n5. Resumen de comunicaciones\n0. Salir");
    }
}
