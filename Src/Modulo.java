public abstract class Modulo {
    private final int id;
    private final String nombre;
    private int salud;
    private boolean activo;
    private final double costo;

    public Modulo(int id, String nombre, int salud, boolean activo, double costo) {
        this.id = id; this.nombre = nombre; this.salud = salud;
        this.activo = activo; this.costo = costo;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public void setSalud(int salud) { this.salud = Math.max(0, Math.min(100, salud)); }
    public boolean isActivo() { return activo && salud > 0; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public double getCosto() { return costo; }
    public abstract String procesarCiclo(Mision mision);
    @Override public String toString() {
        return "ID=" + id + ", nombre=" + nombre + ", salud=" + salud +
               ", activo=" + isActivo() + ", costo=Q" + costo;
    }
    @Override public boolean equals(Object otro) {
        return otro instanceof Modulo && ((Modulo) otro).id == id;
    }
    @Override public int hashCode() { return Integer.hashCode(id); }
}
