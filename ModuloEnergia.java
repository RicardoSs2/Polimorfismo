public class ModuloEnergia extends Modulo {
    private final int generacion;
    private int ciclosAcumulados;
    private int totalGenerado;
    public ModuloEnergia(int id, String nombre, int salud, boolean activo, double costo,
                         int generacion, int ciclos, int total) {
        super(id, nombre, salud, activo, costo);
        this.generacion = generacion; this.ciclosAcumulados = ciclos; this.totalGenerado = total;
    }
    @Override public String procesarCiclo(Mision m) {
        if (!isActivo()) return getNombre() + ": inactivo.";
        m.agregarEnergia(generacion);
        ciclosAcumulados++; totalGenerado += generacion;
        return getNombre() + ": genero " + generacion + " unidades de energia.";
    }
    @Override public String toString() {
        return "[ENERGIA] " + super.toString() + ", generacion/ciclo=" + generacion +
            ", ciclos operados=" + ciclosAcumulados + ", total generado=" + totalGenerado;
    }
}
