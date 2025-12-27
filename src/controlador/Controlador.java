package controlador;

import interfaz.PanelScore;
import interfaz.PanelScore.JugadorScore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**

* Controla el juego Snake, puntuación y ranking TOP 3 con nombre.
  */
  public class Controlador implements ActionListener {

  private final interfaz.InterfazApp ventana;
  private final modelo.Mundo mundo;
  private final Timer timer;

  private boolean enEjecucion;
  private boolean pausado;

  private String nombreJugador;

  // Ranking: lista de JugadorScore (nombre + puntaje)
  private List<JugadorScore> ranking;

  public Controlador(interfaz.InterfazApp ventana) {
  this.ventana = ventana;
  this.mundo = new modelo.Mundo();
  this.timer = new Timer(130, this);

  
   this.enEjecucion = false;
   this.pausado = false;

   // Pide el nombre al iniciar
   this.nombreJugador = pedirNombreJugador();

   // Inicializa ranking desde el PanelScore
   this.ranking = new ArrayList<>(ventana.getPanelScore().cargarRanking());

   ventana.requestFocusInWindow();
  

  }

  // ============================================================
  //  PEDIR NOMBRE
  // ============================================================
  private String pedirNombreJugador() {
  String nombre;

  
   do {
       nombre = JOptionPane.showInputDialog(null, "Ingresa tu nombre:", "Jugador");
       if (nombre == null || nombre.trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Debes ingresar un nombre");
       }
   } while (nombre == null || nombre.trim().isEmpty());

   return nombre.trim();
  

  }

// ============================================================
//  CONTROL DEL JUEGO
// ============================================================
public void reiniciarJuego() {
    mundo.reiniciar();
    enEjecucion = true;
    pausado = false;
    timer.start();
}

public void pausarJuego() {
    pausado = !pausado;
}

@Override
public void actionPerformed(ActionEvent e) {

    if (!enEjecucion || pausado) return;

    mundo.actualizar();
    ventana.getPanelScore().actualizarPuntaje(mundo.getPuntaje());

    actualizarTop3EnTiempoReal();

    if (mundo.haPerdido()) {
        timer.stop();
        enEjecucion = false;

        int opcion = JOptionPane.showOptionDialog(
                null,
                "¡Perdiste!\nPuntaje final: " + mundo.getPuntaje() +
                        "\n¿Deseas jugar otra vez?",
                "Fin del Juego",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Mismo nombre", "Cambiar nombre", "Salir"},
                "Mismo nombre"
        );

        if (opcion == 1) { // Cambiar nombre
            nombreJugador = pedirNombreJugador();
        }

        if (opcion != 2) { // No salir
            reiniciarJuego();
        } else {
            System.exit(0);
        }
    }

    ventana.actualizarVista();
}



  // ============================================================
  //  MOVIMIENTOS
  // ============================================================
  public void moverArriba() {
  if (!pausado) mundo.moverArriba();
  }

  public void moverAbajo() {
  if (!pausado) mundo.moverAbajo();
  }

  public void moverIzquierda() {
  if (!pausado) mundo.moverIzquierda();
  }

  public void moverDerecha() {
  if (!pausado) mundo.moverDerecha();
  }

// ============================================================
//  PUNTAJE Y RANKING
// ============================================================
private void ordenarRanking(List<JugadorScore> lista) {
    lista.sort((a, b) -> Integer.compare(b.getPuntaje(), a.getPuntaje()));
}

private void actualizarTop3EnTiempoReal() {
    int puntajeActual = mundo.getPuntaje();
    boolean modificado = false;

    // Ver si el jugador ya existe en ranking
    for (JugadorScore js : ranking) {
        if (js.getNombre().equalsIgnoreCase(nombreJugador)) {
            if (puntajeActual > js.getPuntaje()) {
                js.setPuntaje(puntajeActual);
                modificado = true;
            }
            break;
        }
    }

    // Si no existe, agregar al ranking
    if (!ranking.stream().anyMatch(js -> js.getNombre().equalsIgnoreCase(nombreJugador))) {
        ranking.add(new JugadorScore(nombreJugador, puntajeActual));
        modificado = true;
    }

    // Si hubo cambios, actualizar ranking
    if (modificado) {
        ordenarRanking(ranking);

        // Mantener solo top 3
        if (ranking.size() > 3) ranking = ranking.subList(0, 3);

        // Actualizar visualización en PanelScore y guardar en archivo
        ventana.getPanelScore().setTop3(ranking);
        ventana.getPanelScore().guardarRanking(ranking);
    }
}



  // ============================================================
  //  GETTERS
  // ============================================================
  public int getPuntaje() {
  return mundo.getPuntaje();
  }

  public modelo.Serpiente getSerpiente() {
  return mundo.getSerpiente();
  }

  public modelo.Comida getComida() {
  return mundo.getComida();
  }
  public boolean isPausado() {
    return pausado;
}

  }
