/**
 * Reversi 1.0.2
 * Interfaz ModoJuego.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;


public interface ModoJuego {

	/**
	 * Inicia un movimiento.
	 */
	public void comenzar();
	
	/**
	 * Finaliza la ejecución de las hebras (no se usa en algunas clases).
	 */
	public void terminar();
	
	/**
	 * Deshace un movimiento de la partida (no se usa en algunas clases).
	 */
	public void deshacerPulsado();
	
	/**
	 * Realiza la acción de poner una ficha en el tablero (no se usa en algunas clases).
	 * @param f fila pulsada
	 * @param c columna pulsada
	 */
	public void tableroPulsado(int f, int c);
}
