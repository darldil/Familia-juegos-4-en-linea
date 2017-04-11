/**
 * Reversi 1.0.2
 * Clase Observador.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroSoloLectura;

public interface Observador {
	
	/**
	 * Es invocado por Partida para notificar a las vistas que se reinicia la partida. 
	 * @param tab tablero inicial
	 * @param turno color del turno actual
	 */
	public void onReset(TableroSoloLectura tab, Ficha turno);
	
	/**
	 * Es invocado por Partida para notificar a las vistas que la partida ha terminado. 
	 * @param tablero tablero de solo lectura
	 * @param ganador color del ganador de la partida
	 */
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador);
	
	/**
	 * Es invocado por Partida para notificar a las vistas que se ha cambiado el juego
	 * @param tab tablero de solo lectura con el estado inicial del juego
	 * @param turno color del primer turno, por defecto, blancas.
	 */
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno);
	
	/**
	 * Es invocado por Partida para notificar a las vistas que una operación deshacer no ha
	 * tenido exito porque no se puede deshacer 
	 */
	public void onUndoNotPossible();
	
	/**
	 * Es invocado por Partida para notificar a las vistas que se ha deshecho un movimiento. 
	 * @param tablero tablero de solo lectura con el estado final del tablero
	 * @param turno turno del siguiente jugador
	 * @param hayMas si hay mas movimientos a deshacer o no
	 */
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas);
	
	/**
	 * Es invocado por Partida para notificar a las vistas que se ha terminado de realizar el movimiento
	 * @param tablero tablero de solo lectura con el estado tras el movimiento
	 * @param jugador turno del jugador que ha realizado el movimiento
	 * @param turno turno del siguiente jugador
	 */
	public void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador, Ficha turno);
	
	/**
	 * Es invocado por Partida para notificar que se ha producido un movimiento incorrecto
	 * @param movimientoException excepcion que contiene una explicacion del problema producido
	 */
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
}
