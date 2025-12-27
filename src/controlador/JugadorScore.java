package controlador;

public class JugadorScore {

    private String nombre;
    private int puntaje;

    public JugadorScore(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        return nombre + ";" + puntaje;
    }
}
