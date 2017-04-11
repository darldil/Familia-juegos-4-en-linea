/**
 * Reversi 1.0.2
 * Clase JugadorAleatorioComplica.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica extends Jugador {


	/**
	 * Calcula la ficha y la columna del jugador aleatoriodel complica
	 * @param tab tablero actual del juego conecta 4
	 * @param color color del jugador actual
	 */
	@Override
	public void getFilaColumna(Tablero tab, Ficha color) {
		this.columna = (int) (tab.getColumnas() * Math.random());
	}

}
