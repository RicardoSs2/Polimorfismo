public class ModuloVuelo extends Modulo {
    private final String instrumento;
    private final int datosPorCiclo;
    private final int consumo;
    private int ciclosAcumulados;
    private int totalRecolectado;
    public ModuloVuelo(int id, String nombre, int salud, boolean activo, double costo,
                       String instrumento, int datosPorCiclo, int consumo, int ciclos, int total) {
        super(id, nombre, salud, activo, costo);
        this.instrumento = instrumento; this.datosPorCiclo = datosPorCiclo;
        this.consumo = consumo; this.ciclosAcumulados = ciclos; this.totalRecolectado = total;
    }
    @Override public String procesarCiclo(Mision m) {
        if (!isActivo()) return getNombre() + ": inactivo.";
        if (m.getEnergia() < consumo) return getNombre() + ": energia insuficiente.";
        m.consumirEnergia(consumo);
        m.agregarDatos(datosPorCiclo);
        ciclosAcumulados++; totalRecolectado += datosPorCiclo;
        return getNombre() + ": recolecto " + datosPorCiclo + " MB y gasto " + consumo + " energia.";
    }
    @Override public String toString() {
        return "[VUELO] " + super.toString() + ", instrumento=" + instrumento +
            ", MB/ciclo=" + datosPorCiclo + ", consumo=" + consumo +
            ", ciclos operados=" + ciclosAcumulados + ", total recolectado=" + totalRecolectado + " MB";
    }
}
