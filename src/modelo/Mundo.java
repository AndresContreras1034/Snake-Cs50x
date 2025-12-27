package modelo;

public class Mundo {

    private Serpiente serpiente;
    private Comida comida;
    private Tablero tablero;
    private int puntaje;

    public Mundo() {
        tablero = new Tablero(30, 25); // tamaño del tablero
        serpiente = new Serpiente();
        comida = new Comida();
        comida.reubicar(tablero);
        puntaje = 0;
    }

    // Actualiza el estado del juego en cada tick
    public void actualizar() {
        serpiente.mover();

        if (serpiente.haComido(comida)) {
            serpiente.crecer();
            comida.reubicar(tablero);
            puntaje += 10;
        }

        // Detectar colisiones después de mover
        detectarColision();
    }

    // Detecta colisiones con bordes o consigo misma
    public boolean detectarColision() {
        Segmento cabeza = serpiente.getCabeza();

        // Choque con paredes
        if (!tablero.esPosicionValida(cabeza.getX(), cabeza.getY())) {
            serpiente.setMuerta(true);
            return true;
        }

        // Choque consigo misma
        if (serpiente.colisionaConSigoMisma()) {
            serpiente.setMuerta(true);
            return true;
        }

        return false;
    }

    // Reinicia el mundo a su estado inicial
    public void reiniciar() {
        serpiente = new Serpiente();
        comida.reubicar(tablero);
        puntaje = 0;
    }

    // Getters
    public Serpiente getSerpiente() {
        return serpiente;
    }

    public Comida getComida() {
        return comida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntaje() {
        return puntaje;
    }

    // Devuelve true si la serpiente ha muerto
    public boolean haPerdido() {
        return serpiente.isMuerta();
    }

    // Métodos para cambiar la dirección desde el controlador
    public void moverArriba()    { serpiente.cambiarDireccion(Direccion.ARRIBA); }
    public void moverAbajo()     { serpiente.cambiarDireccion(Direccion.ABAJO); }
    public void moverIzquierda() { serpiente.cambiarDireccion(Direccion.IZQUIERDA); }
    public void moverDerecha()   { serpiente.cambiarDireccion(Direccion.DERECHA); }
}
