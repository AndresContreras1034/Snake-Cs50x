package modelo;

import java.util.Random;

public class Comida {

    private int x;
    private int y;

    public Comida() {
        x = 0;
        y = 0;
    }

    public void reubicar(Tablero tablero) {
        Random random = new Random();
        x = random.nextInt(tablero.getAncho());
        y = random.nextInt(tablero.getAlto());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
