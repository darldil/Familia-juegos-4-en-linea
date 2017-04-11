/**
 * Reversi 1.0.2
 * Clase GUIOuterPanel.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.jugador.ModoJuego;

@SuppressWarnings("serial")
public class GUIOuterExterno extends JPanel {
	private GUIRightPanel derecha;
	private GUILeftPanel izquierda;
	
	/**
	 * Constructora
	 * @param controlador controlador
	 */
	public GUIOuterExterno(ControladorGUI controlador, ModoJuego j1/*, ModoJuego j2*/) {
		this.createDerecha(controlador, j1/*, j2*/);
		this.createIzquierda(controlador, j1);
	}
	
	/**
	 * Obtiene el panel derecho.
	 * @return Panel derecho
	 */
	public GUIRightPanel getDerecha() {
		return this.derecha;
	}
	
	/**
	 * Obtiene el panel izquierdo
	 * @return Panel izquierdo.
	 */
	public GUILeftPanel getIzquierda() {
		return this.izquierda;
	}
	
	public void createIzquierda(ControladorGUI controlador, ModoJuego j1) {
		this.izquierda = new GUILeftPanel(controlador, j1);
	}
	
	public void createDerecha(ControladorGUI controlador, ModoJuego j1/*, ModoJuego j2*/) {
		this.derecha = new GUIRightPanel(controlador, j1/*, j2*/);
	}
}
