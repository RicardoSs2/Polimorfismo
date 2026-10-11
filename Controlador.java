public class Controlador {
    private final Mision modelo;
    private final Vista vista;
    public Controlador(Mision modelo, Vista vista) { this.modelo = modelo; this.vista = vista; }
    public void iniciar() {
        String opcion;
        do {
            vista.menu();
            opcion = vista.leer("Seleccione una opcion: ");
            switch (opcion) {
                case "1":
                    for (Modulo m : modelo.getModulos()) vista.mostrar(m.toString());
                    break;
                case "2":
                    Modulo encontrado = modelo.buscar(vista.leer("Ingrese ID o nombre exacto: "));
                    vista.mostrar(encontrado == null ? "Modulo no encontrado." : encontrado.toString());
                    break;
                case "3":
                    for (Modulo m : modelo.catalogo()) vista.mostrar(m.toString());
                    break;
                case "4":
                    for (String resultado : modelo.avanzarCiclo()) vista.mostrar(resultado);
                    break;
                case "5":
                    vista.mostrar(modelo.resumenComunicaciones());
                    break;
                case "0": vista.mostrar("Fin de la simulacion."); break;
                default: vista.mostrar("Opcion no valida.");
            }
        } while (!opcion.equals("0"));
    }
}
