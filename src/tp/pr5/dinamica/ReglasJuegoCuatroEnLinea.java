/**
 * Reversi 1.0.2
 * Clase Estática ReglasJuegoCuatroEnLinea.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase estática que comprueba para cualquier juego de la familia Cuatro en
 * linea que se hagan grupos de 4 fichas.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class ReglasJuegoCuatroEnLinea {

	final static int TAM_GRUPOS = 4;

	/**
	 * Comprueba si hay cuatro en linea.
	 * @param tab tablero a comprobar.
	 * @param turno turno actual a comprobar.
	 * @param fila fila actual.
	 * @param col columna actual.
	 * @return si hay o no cuatro en linea.
	 */
	public static boolean cuatroEnLinea(Tablero tab, Ficha turno, int fila,
			int col) {
		boolean grupo = false;
		final int SUMAR = 1, RESTAR = -1, IGUAL = 0;
		
		//Comprueba las columnas hacia abajo
		int tmp = comprobarGrupos(tab, turno, fila, col, RESTAR, IGUAL);

		if (tmp < ReglasJuegoCuatroEnLinea.TAM_GRUPOS) { // Comprobar Filas
			tmp = comprobarGrupos(tab, turno, fila, col, IGUAL, SUMAR)
					+ comprobarGrupos(tab, turno, fila, col, IGUAL, RESTAR) - 1;

			if (tmp < ReglasJuegoCuatroEnLinea.TAM_GRUPOS) { // Comprobar Diagonal derecha
				tmp = comprobarGrupos(tab, turno, fila, col, SUMAR, SUMAR)
						+ comprobarGrupos(tab, turno, fila, col, RESTAR, RESTAR)
						- 1;
				if (tmp < ReglasJuegoCuatroEnLinea.TAM_GRUPOS) { // Comprobar Diagonal Izquierda
					tmp = comprobarGrupos(tab, turno, fila, col, SUMAR, RESTAR)
							+ comprobarGrupos(tab, turno, fila, col, RESTAR,
									SUMAR) - 1;
					if (tmp < ReglasJuegoCuatroEnLinea.TAM_GRUPOS) { // Comprobar Columnas hacia arriba
						tmp = comprobarGrupos(tab, turno, fila, col, SUMAR,
								IGUAL);
						
						if (tmp < ReglasJuegoCuatroEnLinea.TAM_GRUPOS) {
							tmp = comprobarGrupos(tab, turno, fila, col, RESTAR, IGUAL) + 
									comprobarGrupos(tab, turno, fila, col, SUMAR, IGUAL) - 1;
						}
					}
				}
			}
		}

		if (tmp >= ReglasJuegoCuatroEnLinea.TAM_GRUPOS)
			grupo = true;

		return grupo;
	}

	/**
	 * Comprueba un grupo de fichas
	 * @param tablero tablero a comprobar.
	 * @param turno turno aactual.
	 * @param fila fila actual.
	 * @param columna columna, actual.
	 * @param df indica que hacer, si sumar, restar o mantener igual las filas.
	 * @param dc indica que hacer, si sumar, restar o mantener igual las columnas.
	 * @return número de fichas juntas.
	 */
	private static int comprobarGrupos(Tablero tablero, Ficha turno, int fila,
			int columna, int df, int dc) {
		int numFichas = 0;
		Ficha aux = tablero.getCasilla(fila, columna);

		fila = fila + df;
		columna = columna + dc;

		while ((numFichas < ReglasJuegoCuatroEnLinea.TAM_GRUPOS)
				&& (fila < tablero.getFilas())
				&& (columna < tablero.getColumnas()) && (fila >= 0)
				&& (columna >= 0)
				&& (aux == tablero.getCasilla(fila, columna) && (aux == turno))) {
			numFichas++;
			fila = fila + df;
			columna = columna + dc;
		}
		numFichas++;

		return numFichas;
	}
}
