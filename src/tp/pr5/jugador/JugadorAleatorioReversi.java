package tp.pr5.jugador;

import tp.pr5.dinamica.ReglasJuegoReversi;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioReversi extends Jugador{
	
	private static final int SUMAR = 1;
	private static final int RESTAR = -1;
	private static final int IGUAL = 0;

	/**
	 * Calcula la ficha y la columna del jugador aleatorio del reversi
	 * @param tab tablero actual del juego reversi
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
			if ((tab.getCasilla(fila, columna) == Ficha.VACIA) &&
				 (ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, IGUAL, SUMAR) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, IGUAL, RESTAR) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, SUMAR, IGUAL) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, RESTAR, IGUAL) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, SUMAR, SUMAR) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, RESTAR, RESTAR) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, SUMAR, RESTAR) ||
						 ReglasJuegoReversi.comprobarMovimiento(tab, fila, columna, color, RESTAR, SUMAR)))
				fin = true;
		}

		this.columna = columna;
		this.fila = fila;
	}
}
