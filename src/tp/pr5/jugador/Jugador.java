/**
 * Reversi 1.0.2
 * Interfaz Jugador.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import java.util.InputMismatchException;

import tp.pr5.dinamica.*;
import tp.pr5.excepciones.*;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.logica.*;

abstract public class Jugador {
	protected int fila;
	protected int columna;

	/**
	 * El jugador introduce la ficha y la columna en la que 
	 * desea introducir una ficha. 
	 * @param tab tablero actual
	 * @param color color de la ficha
	 */
	public abstract void getFilaColumna(Tablero tab, Ficha color);

	/**
	 * Obtiene el movimiento del jugador. 
	 * @param factoria factoria del juego actual
	 * @param tab tablero actual
	 * @param color color del jugador actual
	 * @return devuelve el movimiento creador con los datos correspondientes
	 * @throws DatosIncorrectos si los datos introducidos no son numericos
	 */
	public Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab,
			Ficha color) throws DatosIncorrectos {
		try {
			this.getFilaColumna(tab, color);
			return factoria.creaMovimiento(this.fila, this.columna, color);
		} catch (InputMismatchException e) {
			throw new DatosIncorrectos("Los datos introducidos no son numericos");
		}
	}
}
