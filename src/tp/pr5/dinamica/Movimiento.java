/**
 * Reversi 1.0.2
 * Clase Abstracta Movimiento.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase abstracta encargada de gestionar los movimientos del usuario en una
 * partida genérica.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public abstract class Movimiento {
	protected int col;
	protected int fil;
	protected Ficha color;

	/**
	 * Constructora de Movimiento.
	 * @param col columna
	 * @param color color de la ficha a colocar
	 * Inicializa la fila a 0
	 */
	public Movimiento(int col, Ficha color) {
		this.col = col;
		this.color = color;
		this.fil = 0;
	}
	
	/**
	 * Constructora de Movimiento.
	 * @param f fila
	 * @param c columna
	 * @param color turno de la ficha a colocar
	 */
	public Movimiento(int f, int c, Ficha color) {
		this.fil = f;
		this.col = c;
		this.color = color;
	}

	/**
	 * Obtiene el jugador actual.
	 * @return el color del jugador actual
	 */
	public Ficha getJugador() {
		return this.color;
	}

	/**
	 * Obtiene la columna actual.
	 * @return columna actual.
	 */
	public int getColumna() {
		return this.col;
	}

	/**
	 * Obtiene la fila actual.
	 * @return fila actual.
	 */
	public int getFila() {
		return this.fil;
	}

	/**
	 * Indica si la columna es incorrecta
	 * @param tab tablero actual 
	 * @return true si la columna es incorrecta y false si es correcta
	 */
	public boolean columnaIncorrecta(Tablero tab) {
		boolean incorrecto = false;

		if ((this.col >= tab.getColumnas()) || (this.col < 0))
			incorrecto = true;

		return incorrecto;
	}

	/**
	 * Indica si la columna está llena o no
	 * @param tab tablero del juago actual
	 * @return true si está llena y false si no
	 */
	public boolean columnaLlena(Tablero tab) { 
		boolean lleno = false;

		if ((tab.getCasilla(tab.getFilas() - 1, this.col)) != Ficha.VACIA)
			lleno = true;

		return lleno;

	}

	/**
	 * Ejecuta el movimiento seleccionado por el usuario.
	 * @param tab tablero del juego actual.
	 * @throws MovimientoInvalido si el movimiento no es correcto y lanza el correspondiente mensaje
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;

	/**
	 * Deshace el último movimiento.
	 * @param tab tablero del juego actual.
	 */
	public abstract void undo(Tablero tab);

}
