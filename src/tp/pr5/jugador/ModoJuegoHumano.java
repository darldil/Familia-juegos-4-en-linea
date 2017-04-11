/**
 * Reversi 1.0.2
 * Clase ModoJuegoHumano.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import tp.pr5.control.ControladorGUI;


public class ModoJuegoHumano implements ModoJuego{
	
	private ControladorGUI contr;
	
	/**
	 * Constructora ModoJuegoHumano
	 * @param contr controlador del modo grafico
	 */
	public ModoJuegoHumano(ControladorGUI contr) {
		this.contr = contr;
	}

	/**
	 * No se utiliza
	 */
	@Override
	public void comenzar() {
		
	}

	/**
	 * No se utiliza
	 */
	@Override
	public void terminar() {
		
	}

	/**
	 * Deshace un movimiento de la partida.
	 */
	@Override
	public void deshacerPulsado() {
		this.contr.undo();
	}
	
	/**
	 * Realiza la acción de poner una ficha en el tablero (no se usa en algunas clases).
	 * @param f fila pulsada
	 * @param c columna pulsada
	 */
	public void tableroPulsado(int f, int c) {
		this.contr.poner(f, c);
	}

}
