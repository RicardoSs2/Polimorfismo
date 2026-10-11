public class ModuloTierra extends Modulo {
    private final String estacion;
    private final int capacidad;
    private final int consumo;
    private int totalDescargado;
    public ModuloTierra(int id, String nombre, int salud, boolean activo, double costo,
                        String estacion, int capacidad, int consumo, int totalDescargado) {
        super(id, nombre, salud, activo, costo);
        this.estacion = estacion; this.capacidad = capacidad;
        this.consumo = consumo; this.totalDescargado = totalDescargado;
    }
    public String getEstacion() { return estacion; }
    public int getCapacidad() { return capacidad; }
    public int getTotalDescargado() { return totalDescargado; }
    @Override public String procesarCiclo(Mision m) {
        if (!isActivo()) return getNombre() + ": inactivo.";
        if (m.getEnergia() < consumo) return getNombre() + ": energia insuficiente.";
        if (m.getDatosOrbita() == 0) return getNombre() + ": no hay datos en orbita.";
        int cantidad = Math.min(capacidad, m.getDatosOrbita());
        m.consumirEnergia(consumo);
        m.descargarDatos(cantidad);
        totalDescargado += cantidad;
        return getNombre() + ": descargo " + cantidad + " MB y gasto " + consumo + " energia.";
    }
    @Override public String toString() {
        return "[TIERRA] " + super.toString() + ", estacion=" + estacion +
            ", capacidad=" + capacidad + " MB/ciclo, consumo=" + consumo +
            ", total descargado=" + totalDescargado + " MB";
    }
}
