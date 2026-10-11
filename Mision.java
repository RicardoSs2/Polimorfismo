import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mision {
    private final List<Modulo> modulos = new ArrayList<>();
    private int energia = 10;
    private int datosOrbita = 30;
    private int datosDescargados = 75;
    private int ciclo = 0;

    public Mision() {
        // Carga inicial: mision en progreso, con diez modulos en orden fijo.
        modulos.add(new ModuloEnergia(1, "Panel Norte", 100, true, 2500, 20, 4, 80));
        modulos.add(new ModuloVuelo(2, "Camara Quetzal", 95, true, 5000, "Camara", 15, 8, 3, 45));
        modulos.add(new ModuloTierra(3, "Antena UVG", 100, true, 4000, "Campus Central", 10, 5, 40));
        modulos.add(new ModuloEnergia(4, "Panel Sur", 85, true, 2200, 12, 4, 48));
        modulos.add(new ModuloVuelo(5, "Sensor Solar", 80, true, 4500, "Sensor", 12, 6, 2, 24));
        modulos.add(new ModuloTierra(6, "Antena Auxiliar", 75, true, 3200, "Campus Central", 15, 7, 35));
        modulos.add(new ModuloVuelo(7, "Camara Reserva", 70, false, 3600, "Camara", 8, 4, 1, 8));
        modulos.add(new ModuloEnergia(8, "Panel Este", 90, false, 2100, 10, 2, 20));
        modulos.add(new ModuloTierra(9, "Antena Remota", 65, false, 2800, "Estacion Remota", 8, 4, 0));
        modulos.add(new ModuloVuelo(10, "Sensor Magnetico", 100, true, 4700, "Sensor", 9, 5, 3, 27));
    }
    public List<Modulo> getModulos() { return new ArrayList<>(modulos); }
    public int getEnergia() { return energia; }
    public int getDatosOrbita() { return datosOrbita; }
    public int getDatosDescargados() { return datosDescargados; }
    public void agregarEnergia(int cantidad) { energia += cantidad; }
    public void consumirEnergia(int cantidad) { energia -= cantidad; }
    public void agregarDatos(int cantidad) { datosOrbita += cantidad; }
    public void descargarDatos(int cantidad) { datosOrbita -= cantidad; datosDescargados += cantidad; }
    public Modulo buscar(String texto) {
        for (Modulo modulo : modulos)
            if (String.valueOf(modulo.getId()).equals(texto) || modulo.getNombre().equalsIgnoreCase(texto))
                return modulo;
        return null;
    }
    public List<Modulo> catalogo() {
        List<Modulo> lista = getModulos();
        lista.sort(Comparator.comparingDouble(Modulo::getCosto));
        return lista;
    }
    public List<String> avanzarCiclo() {
        ciclo++;
        List<String> resultados = new ArrayList<>();
        resultados.add("CICLO " + ciclo);
        for (Modulo modulo : modulos) {
            resultados.add(modulo.procesarCiclo(this));
            resultados.add("  Recursos: energia=" + energia + ", orbita=" + datosOrbita +
                           " MB, descargados=" + datosDescargados + " MB");
        }
        return resultados;
    }
    public String resumenComunicaciones() {
        int total = 0, activos = 0, capacidad = 0, mayor = -1;
        List<ModuloTierra> lideres = new ArrayList<>();
        int historico = 0;
        for (Modulo modulo : modulos) {
            if (modulo instanceof ModuloTierra) {
                ModuloTierra tierra = (ModuloTierra) modulo;
                total++;
                if (tierra.isActivo()) { activos++; capacidad += tierra.getCapacidad(); }
                historico += tierra.getTotalDescargado();
                if (tierra.getTotalDescargado() > mayor) {
                    mayor = tierra.getTotalDescargado(); lideres.clear(); lideres.add(tierra);
                } else if (tierra.getTotalDescargado() == mayor) lideres.add(tierra);
            }
        }
        StringBuilder texto = new StringBuilder("Modulos de tierra: " + total + "\nActivos: " + activos +
            "\nCapacidad activa: " + capacidad + " MB/ciclo\nTotal historico descargado: " + historico + " MB\nLider(es):\n");
        for (ModuloTierra t : lideres) texto.append(t.getNombre()).append(" (ID ").append(t.getId())
            .append("), estacion: ").append(t.getEstacion()).append(" - ")
            .append(t.getTotalDescargado()).append(" MB\n");
        return texto.toString();
    }
}
