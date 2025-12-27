package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class PanelScore extends JPanel {

    private int puntaje;
    private JLabel etiquetaPuntaje;
    private JTextArea areaHistorial;

    // Preferences para guardar en Windows/Mac/Linux
    private final Preferences prefs = Preferences.userRoot().node("SnakeGame");

    private List<JugadorScore> top3 = new ArrayList<>();

    public PanelScore() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 140));

        top3 = cargarRanking();

        etiquetaPuntaje = new JLabel("Puntaje: 0");
        etiquetaPuntaje.setFont(new Font("Consolas", Font.BOLD, 20));
        etiquetaPuntaje.setForeground(Color.WHITE);
        etiquetaPuntaje.setHorizontalAlignment(SwingConstants.CENTER);

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaHistorial.setForeground(Color.WHITE);
        areaHistorial.setBackground(Color.DARK_GRAY);
        JScrollPane scroll = new JScrollPane(areaHistorial);

        add(etiquetaPuntaje, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        mostrarTop3EnArea();
    }


    // ================================================================
    // CARGAR RANKING DESDE JAVA PREFERENCES
    // ================================================================
    public List<JugadorScore> cargarRanking() {
        List<JugadorScore> lista = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String nombre = prefs.get("nombre" + i, "---");
            int score = prefs.getInt("score" + i, 0);
            lista.add(new JugadorScore(nombre, score));
        }

        return lista;
    }

    // ================================================================
    // GUARDAR TOP 3 EN JAVA PREFERENCES
    // ================================================================
    public void guardarRanking(List<JugadorScore> lista) {

        for (int i = 1; i <= 3; i++) {
            prefs.put("nombre" + i, lista.get(i - 1).getNombre());
            prefs.putInt("score" + i, lista.get(i - 1).getPuntaje());
        }
    }

    // ================================================================
    // MOSTRAR TOP 3 EN TEXTAREA
    // ================================================================
    public void mostrarTop3EnArea() {
        StringBuilder sb = new StringBuilder("=========== TOP 3 ===========\n");
        for (int i = 0; i < top3.size(); i++) {
            JugadorScore js = top3.get(i);
            sb.append((i + 1)).append(". ").append(js.getNombre()).append(" - ").append(js.getPuntaje()).append("\n");
        }
        areaHistorial.setText(sb.toString());
    }

    // ================================================================
    // ACTUALIZAR PUNTAJE EN PANTALLA
    // ================================================================
    public void actualizarPuntaje(int nuevoPuntaje) {
        this.puntaje = nuevoPuntaje;
        etiquetaPuntaje.setText("Puntaje: " + puntaje);
    }

    // ================================================================
    // ACTUALIZAR DESDE CONTROLADOR
    // ================================================================
    public void setTop3(List<JugadorScore> lista) {
        this.top3 = lista;
        guardarRanking(top3);
        mostrarTop3EnArea();
    }

    public int getPuntaje() {
        return puntaje;
    }

    // ================================================================
    // CLASE INTERNA
    // ================================================================
    public static class JugadorScore {
        private final String nombre;
        private int puntaje;

        public JugadorScore(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        public String getNombre() { return nombre; }
        public int getPuntaje() { return puntaje; }
        public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    }
}
