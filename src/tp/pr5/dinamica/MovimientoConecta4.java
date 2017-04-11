/**
 * Reversi 1.0.2
 * Clase MovimientoConecta4. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.excepciones.*;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase encargada de gestionar los movimientos del usuario en una partida de
 * Conecta 4.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class MovimientoConecta4 extends Movimiento {

	/**
	 *  Constructora de la clase MovimientoConecta4
	 * @param col columna a poner.
	 * @param turno turno actual.
	 */
	public MovimientoConecta4(int col, Ficha turno) {
		super(col, turno);
	}

	/**
	 * Ejecuta el movimiento seleccionado por el usuario.
	 * @param tab tablero del juego actual.
	 * @throws MovimientoInvalido si el movimiento no es correcto y lanza el correspondiente mensaje
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (this.columnaIncorrecta(tab))
			throw new ColumnaIncorrecta(tab.getColumnas());
		else if (this.columnaLlena(tab))
			throw new ColumnaLlena();
		else {
			int f = tab.filaVacia(this.col);
			this.fil = f;
			tab.setFicha(this.fil, this.col, this.color);
		}
	}

	/**
	 * Deshace el último movimiento.
	 * @param tab tablero del juego actual.
	 */
	@Override
	public void undo(Tablero tab) {
		tab.setFicha(this.fil, this.col, Ficha.VACIA);
	}

}
