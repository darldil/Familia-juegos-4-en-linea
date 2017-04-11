/**
 * Reversi 1.0.2
 * Clase TableroSoloLectura.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.logica;

public interface TableroSoloLectura {
	/**
	 * Obtiene las filas del tablero.
	 * @return filas
	 */
	public int getFilas();
	
	/**
	 * Obtiene las columnas del tablero.
	 * @return	columnas
	 */
	public int getColumnas();
	
	/**
	 * Obtiene la ficha dada una fila y columna.
	 * @param fila fila
	 * @param col columna
	 * @return casilla
	 */
	public Ficha getCasilla(int fila, int col);
	
	/**
	 * Muestra el tablero.
	 * @return String con el tablero
	 */
	public String toString();
}
