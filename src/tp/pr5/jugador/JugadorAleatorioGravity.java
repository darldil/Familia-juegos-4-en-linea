/**
 * Reversi 1.0.2
 * Clase JugadorAleatorioGravity.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity extends Jugador {

	
	/**
	 * Calcula la ficha y la columna del jugador aleatorio del gravity
	 * @param tab tablero actual del juego conecta 4
	 * @param color color del jugador actual
	 */
	@Override
	public void getFilaColumna(Tablero tab, Ficha color) {
		boolean fin = false;
		int columna = 0;
		int fila = 0;

		while (!fin) {
			columna = (int) (tab.getColumnas() * Math.random());
			fila = (int) (tab.getFilas() * Math.random());
			if (tab.getCasilla(fila, columna) == Ficha.VACIA)
				fin = true;
		}

		this.columna = columna;
		this.fila = fila;
	}
}
