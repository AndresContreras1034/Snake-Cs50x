package interfaz;

import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazApp extends JFrame {

    private PanelJuego panelJuego;
    private PanelScore panelScore;
    private Controlador controlador;

    private JPanel panelSuperior;
    private JPanel panelInferiorContenedor;

    public InterfazApp() {
        setTitle("Proyecto Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // ===== PANEL SUPERIOR =====
        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(240, 240, 240));
        panelSuperior.setPreferredSize(new Dimension(600, 40));

        JLabel lblUsuario = new JLabel("SNAKE");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblUsuario);
        add(panelSuperior, BorderLayout.NORTH);

        // ===== PANEL CENTRAL =====
        panelJuego = new PanelJuego();
        add(panelJuego, BorderLayout.CENTER);

        // ===== PANEL INFERIOR =====
        panelScore = new PanelScore();
        panelInferiorContenedor = new JPanel(new BorderLayout());
        panelInferiorContenedor.add(panelScore, BorderLayout.CENTER);
        add(panelInferiorContenedor, BorderLayout.SOUTH);

        // ===== INICIALIZAR CONTROLADOR AHORA =====
        controlador = new Controlador(this);

        // ===== CAPTURA DE TECLAS =====
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                // Si el juego está pausado y presionan una flecha, despausar primero
                if (controlador.isPausado()) {
                    if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN ||
                        code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
                        controlador.pausarJuego(); // Despausa
                    }
                }

                // Movimientos normales
                switch (code) {
                    case KeyEvent.VK_UP -> controlador.moverArriba();
                    case KeyEvent.VK_DOWN -> controlador.moverAbajo();
                    case KeyEvent.VK_LEFT -> controlador.moverIzquierda();
                    case KeyEvent.VK_RIGHT -> controlador.moverDerecha();
                    case KeyEvent.VK_SPACE -> controlador.pausarJuego(); // Pausa / despausa
                }
            }
        });

        // Configuración final de la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Asegura que la ventana tenga foco real para los controles
        SwingUtilities.invokeLater(() -> {
            setFocusable(true);
            requestFocusInWindow();
        });

        // Iniciar el juego
        controlador.reiniciarJuego();
    } // <-- Cierre del constructor

    // ===== MÉTODO LLAMADO POR EL CONTROLADOR =====
    public void actualizarVista() {
        panelJuego.actualizar(controlador.getSerpiente(), controlador.getComida());
        panelScore.actualizarPuntaje(controlador.getPuntaje());

        panelScore.repaint();
        panelJuego.repaint();
    }

    // ===== GETTERS =====
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public PanelScore getPanelScore() {
        return panelScore;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazApp::new);
    }
}
