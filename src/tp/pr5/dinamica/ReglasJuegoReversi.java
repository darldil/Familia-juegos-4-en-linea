package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroSoloLectura;

public class ReglasJuegoReversi {

	/**
	 * Comprueba si se puede realizar el movimiento
	 * @param tab tablero actual
	 * @param f fila en la que se quiere comprobar
	 * @param c columna en la que se quiere comprobar
	 * @param orig ficha desde la que se empieza a comprobar
	 * @param df direccion de la fila
	 * @param dc direccion de la columna
	 * @return si el movimiento es correcto o no
	 *
	*/
	public static boolean comprobarMovimiento(TableroSoloLectura tab, int f, 
			int c, Ficha orig, int df, int dc) {
		int fila = f + df;
		int columna = c + dc;
		Ficha aux = Ficha.VACIA;
		if ((fila >= 0 && fila < tab.getFilas())
				&& (columna >= 0 && columna < tab.getColumnas())) {
			aux = tab.getCasilla(fila, columna);
		}

		if (orig != aux && aux != Ficha.VACIA) {
			while (	(fila < tab.getFilas())
					&& (columna < tab.getColumnas()) && (fila >= 0)
					&& (columna >= 0) && aux != Ficha.VACIA) {
					
				if (aux == orig)
					return true;
				
				else {
					aux = tab.getCasilla(fila, columna);
					fila = fila + df;
					columna = columna + dc;
				}
			}
			if (orig == aux)
				return true;
		}
		return false;
	}
}
