/**
 * Reversi 1.0.2
 * Clase VistaGUI.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tp.pr5.control.ControladorGUI;
import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.jugador.ModoJuego;
import tp.pr5.jugador.ModoJuegoAutomatico;
import tp.pr5.jugador.ModoJuegoHumano;
import tp.pr5.jugador.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TableroSoloLectura;

@SuppressWarnings("serial")
public class VistaGUI extends JFrame implements Observador{
	
	private ControladorGUI controlador;
	private GUIOuterExterno ventana;
	private ModoJuego modoJuego;
	
	/**
	 * Constructora de la clase VistaGUI
	 * @param c controlador grafico
	 * @param partida partida actual
	 */
	public VistaGUI(ControladorGUI c, Partida partida) {
		super("Reversi 1.0.2");
		this.initGUI();
		this.controlador = c;
		this.modoJuego = new ModoJuegoHumano(c);
		this.ventana = new GUIOuterExterno(controlador, modoJuego);
		add(this.ventana.getDerecha(), BorderLayout.EAST);
		add(this.ventana.getIzquierda(), BorderLayout.CENTER);
		partida.addObservador(this);
		this.setVisible(true);
	}
	
	/**
	 * Inicializa todos los componentes de la ventana
	 */
	private void initGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
		}	catch(UnsupportedLookAndFeelException |
				ClassNotFoundException |
				InstantiationException |
				IllegalAccessException e) {
				e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que se reinicia la partida. 
	 * @param tab tablero inicial
	 * @param turno color del turno actual
	 */
	@Override
	public void onReset(TableroSoloLectura tab, Ficha turno) {
		GUIRightPanel derecha = this.ventana.getDerecha();
		GUILeftPanel izquierda = this.ventana.getIzquierda();
		this.modoJuego = new ModoJuegoHumano(this.controlador);
		derecha.iniciarPartida(this.modoJuego, this.controlador.getPartida().getTurno());
		if (izquierda.getTableroDesactivado()) {
			izquierda.iniciarPartida(tab);
		}
		izquierda.actualizarParrilla(tab, this.modoJuego);
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que la partida ha terminado. 
	 * @param tablero tablero de solo lectura
	 * @param ganador color del ganador de la partida
	 */
	@Override
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador) {
		GUIRightPanel derecha = this.ventana.getDerecha();
		GUILeftPanel izquierda = this.ventana.getIzquierda();
		izquierda.actualizarParrilla(tablero, this.modoJuego);
		izquierda.finPartida();
		derecha.finPartida();
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que se ha cambiado el juego
	 * @param tab tablero de solo lectura con el estado inicial del juego
	 * @param turno color del primer turno, por defecto, blancas.
	 */
	@Override
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
		
		getContentPane().removeAll();
		getContentPane().repaint();
		this.ventana.createIzquierda(this.controlador, new ModoJuegoHumano(this.controlador));
		GUIRightPanel derecha = this.ventana.getDerecha();
		derecha.iniciarPartida(this.modoJuego, this.controlador.getPartida().getTurno());
		add(this.ventana.getDerecha(), BorderLayout.EAST);
		add(this.ventana.getIzquierda(), BorderLayout.CENTER);
		this.revalidate();
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que una operación deshacer no ha
	 * tenido exito porque no se puede deshacer 
	 * No se utiliza en esta clase
	 */
	@Override
	public void onUndoNotPossible() {
		
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que se ha deshecho un movimiento. 
	 * @param tablero tablero de solo lectura con el estado final del tablero
	 * @param turno turno del siguiente jugador
	 * @param hayMas si hay mas movimientos a deshacer o no
	 */
	@Override
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas) {
		GUILeftPanel izquierda = this.ventana.getIzquierda();
		GUIRightPanel derecha = this.ventana.getDerecha();
		izquierda.actualizarParrilla(tablero, this.modoJuego);
		derecha.actualizarEstado(turno);
	}

	/**
	 * Es invocado por Partida para notificar a las vistas que se ha terminado de realizar el movimiento
	 * @param tablero tablero de solo lectura con el estado tras el movimiento
	 * @param jugador turno del jugador que ha realizado el movimiento
	 * @param turno turno del siguiente jugador
	 */
	@Override
	public void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador,
			Ficha turno) {
		GUIRightPanel derecha = this.ventana.getDerecha();
		GUILeftPanel izquierda = this.ventana.getIzquierda();
		derecha.actualizarEstado(this.controlador.getPartida().getTurno());
		if (turno == Ficha.BLANCA) {
			if (derecha.getBlanco() == TipoJugador.AUTOMATICO)
				this.modoJuego = new ModoJuegoAutomatico(this.controlador);
			else 
				this.modoJuego = new ModoJuegoHumano(this.controlador);
		}
		else {
			if (derecha.getNegro() == TipoJugador.AUTOMATICO) 
				this.modoJuego = new ModoJuegoAutomatico(this.controlador);
			else 
				this.modoJuego = new ModoJuegoHumano(this.controlador);
		}
		izquierda.actualizarParrilla(tablero, this.modoJuego);
		izquierda.updateUI();
	}

	/**
	 * Es invocado por Partida para notificar que se ha producido un movimiento incorrecto
	 * @param movimientoException excepcion que contiene una explicacion del problema producido
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		JOptionPane.showMessageDialog(null, movimientoException,"Error", JOptionPane.ERROR_MESSAGE);
	}

}
