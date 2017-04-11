/**
 * Reversi 1.0.2
 * Clase Tablero.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.logica;

import tp.pr5.principal.Main;

/**
 * Contiene y gestiona el tablero de una partida.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Tablero implements TableroSoloLectura{
	private Ficha[][] tablero;
	private int columnas;
	private int filas;
	private int lleno;

	/**
	 * Constructora Tablero. Inicializa un tablero y las filas/columnas.
	 * @param filas Fila
	 * @param columnas Columnas
	 */
	public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.tablero = new Ficha[this.filas][this.columnas];
		this.reset();
	}

	/**
	 * Reinicia el tablero actual.
	 */
	public void reset() {
		for (int n = 0; n < this.filas; n++)
			for (int i = 0; i < this.columnas; i++)
				this.tablero[n][i] = Ficha.VACIA;

		this.lleno = 0;
	}

	/**
	 * Devuelve la dimension del tablero actual
	 * @return this.filas Devuelve las filas del tablero actual.
	 */
	public int getFilas() {
		return this.filas;
	}

	/**
	 * Devuelve la dimension del tablero actual
	 * @return this.filas Devuelve las columnas del tablero actual.
	 */
	public int getColumnas() {
		return this.columnas;
	}

	/**
	 * Devuelve la Ficha de una posicion dada.
	 * @param f fila
	 * @param c Columnas
	 * @return this.tablero Devuelve la Ficha.
	 */
	public Ficha getCasilla(int f, int c) {
		return this.tablero[f][c];
	}

	/**
	 * Coloca una Ficha en una posicion dada.
	 * @param f fila
	 * @param c Columnas
	 * @param jugada Indica la ficha a colocar.
	 */
	public void setFicha(int f, int c, Ficha jugada) {
		if (jugada == Ficha.VACIA)
			this.restarFichasTab();
		else if (this.tablero[f][c] == Ficha.VACIA)
			this.sumarFichasTab();
		this.tablero[f][c] = jugada;
	}

	/**
	 * Indica que cual es la última fila usada en una columna
	 * @param c Columna
	 * @return f Fila usada
	 */
	public int filaVacia(int c) {
		int f = 0, columna = c;
		while (f < this.getFilas() && this.getCasilla(f, columna) != Ficha.VACIA)
			f++;

		if (f == this.getFilas())
			f--;

		return f;
	}

	/**
	 * Una vez agregada una ficha al tablero se le suma + 1 en el atributo
	 * lleno.
	 */
	private void sumarFichasTab() {
		this.lleno++;
	}

	/**
	 * Una vez agregada una ficha al tablero se le resta - 1 en el atributo
	 * lleno.
	 */
	private void restarFichasTab() {
		if (this.lleno > 0)
			this.lleno--;
	}

	/**
	 * Comprueba que el tablero no este completo.
	 * @return true En caso de que esté completo, false en caso contrario
	 */
	public boolean completo() {
		return this.lleno >= (this.filas * this.columnas);
	}

	/**
	 * Convierte toda la información de la clase a String.
	 * @return res Información en string.
	 */
	@Override
	public String toString() {
		String res = Main.NEWLINE;

		for (int f = this.filas - 1; f >= 0; f--) {
			res = res + "| ";
			for (int c = 0; c < this.columnas; c++) {
				res = res + this.tablero[f][c].toString();
			}
			res = res + "| " + (f + 1) + Main.NEWLINE;
		}

		res = res + "+-";

		for (int n = 0; n < (this.columnas * 2); n++) {
			res = res + "-";
		}

		res = res + "+" + Main.NEWLINE + "  ";

		for (int i = 1; i <= this.columnas; i++) {
			res = res + i + " ";
		}

		res = res + Main.NEWLINE + Main.NEWLINE;

		return res;
	}
}
