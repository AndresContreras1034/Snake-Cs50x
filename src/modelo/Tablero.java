package modelo;

public class Tablero {

    private int ancho;
    private int alto;

    public Tablero(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public boolean esPosicionValida(int x, int y) {
        return x >= 0 && y >= 0 && x < ancho && y < alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
