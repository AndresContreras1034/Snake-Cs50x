package modelo;

import java.util.LinkedList;
import java.util.List;

public class Serpiente {

    private final LinkedList<Segmento> segmentos;
    private Direccion direccionActual;
    private boolean crece;

    public Serpiente() {
        segmentos = new LinkedList<>();
        // Posición inicial de la serpiente
        segmentos.add(new Segmento(5, 5));
        segmentos.add(new Segmento(4, 5));
        segmentos.add(new Segmento(3, 5));
        direccionActual = Direccion.DERECHA;
        crece = false;
    }

    // Mueve la serpiente según su dirección actual
    public void mover() {
        Segmento cabeza = getCabeza();
        int x = cabeza.getX();
        int y = cabeza.getY();

        switch (direccionActual) {
            case ARRIBA -> y--;
            case ABAJO -> y++;
            case IZQUIERDA -> x--;
            case DERECHA -> x++;
        }

        Segmento nuevo = new Segmento(x, y);
        segmentos.addFirst(nuevo);

        if (!crece) {
            segmentos.removeLast();
        } else {
            crece = false;
        }
    }

    // Cambia la dirección evitando invertirla directamente
    public void cambiarDireccion(Direccion nueva) {
        if ((direccionActual == Direccion.ARRIBA && nueva == Direccion.ABAJO) ||
            (direccionActual == Direccion.ABAJO && nueva == Direccion.ARRIBA) ||
            (direccionActual == Direccion.IZQUIERDA && nueva == Direccion.DERECHA) ||
            (direccionActual == Direccion.DERECHA && nueva == Direccion.IZQUIERDA)) {
            return;
        }
        direccionActual = nueva;
    }

    // La serpiente crecerá en el próximo movimiento
    public void crecer() {
        crece = true;
    }

    // Verifica si la serpiente ha comido la comida
    public boolean haComido(Comida comida) {
        Segmento cabeza = getCabeza();
        return cabeza.getX() == comida.getX() && cabeza.getY() == comida.getY();
    }

    // Detecta si la serpiente colisiona consigo misma
    public boolean colisionaConSigoMisma() {
        Segmento cabeza = getCabeza();
        for (int i = 1; i < segmentos.size(); i++) {
            Segmento s = segmentos.get(i);
            if (s.getX() == cabeza.getX() && s.getY() == cabeza.getY()) {
                return true;
            }
        }
        return false;
    }

    public Segmento getCabeza() {
        return segmentos.getFirst();
    }

    public List<Segmento> getSegmentos() {
        return segmentos;
    }

private boolean muerta;

public boolean isMuerta() { return muerta; }
public void setMuerta(boolean muerta) { this.muerta = muerta; }

}
