package interfaz;

import modelo.Serpiente;
import modelo.Comida;
import modelo.Segmento;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel {

    private static final int TAM_CELDA = 20; // Tamaño de cada bloque en píxeles
    private static final int ANCHO = 30;     // Número de celdas horizontales
    private static final int ALTO = 25;      // Número de celdas verticales

    private Serpiente serpiente;
    private Comida comida;

    public PanelJuego() {
        setPreferredSize(new Dimension(ANCHO * TAM_CELDA, ALTO * TAM_CELDA));
        setBackground(Color.BLACK);
    }

    public void actualizar(Serpiente serpiente, Comida comida) {
        this.serpiente = serpiente;
        this.comida = comida;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja cuadrícula (opcional, útil para ajustar coordenadas)
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < ANCHO; i++) {
            for (int j = 0; j < ALTO; j++) {
                g.drawRect(i * TAM_CELDA, j * TAM_CELDA, TAM_CELDA, TAM_CELDA);
            }
        }

        // Dibuja la serpiente
        if (serpiente != null) {
            g.setColor(new Color(0, 200, 0));
            for (Segmento seg : serpiente.getSegmentos()) {
                int x = seg.getX() * TAM_CELDA;
                int y = seg.getY() * TAM_CELDA;
                g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
            }
        }

        // Dibuja la comida
        if (comida != null) {
            g.setColor(Color.RED);
            int x = comida.getX() * TAM_CELDA;
            int y = comida.getY() * TAM_CELDA;
            g.fillOval(x, y, TAM_CELDA, TAM_CELDA);
        }
    }
}
