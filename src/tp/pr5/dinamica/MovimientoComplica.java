/**
 * Reversi 1.0.2
 * Clase MovimientoComplica.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.excepciones.ColumnaIncorrecta;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase encargada de gestionar los movimientos del usuario en una partida de
 * Complica.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class MovimientoComplica extends Movimiento {
	private Ficha fichaDesplazada;

	/**
	 * Constructora de la clase MovimientoComplica
	 * @param col  columna a poner.
	 * @param turno turno actual.
	 */
	public MovimientoComplica(int col, Ficha turno) {
		super(col, turno);
		this.fichaDesplazada = Ficha.VACIA;
	}

	/**
	 * Ejecuta el movimiento seleccionado por el usuario.
	 * @param tab tablero del juego actual.
	 * @throws ColumnaIncorrecta si la columna no es correcta y lanza el correspondiente mensaje
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws ColumnaIncorrecta {
		if (this.columnaIncorrecta(tab))
			throw new ColumnaIncorrecta(tab.getColumnas());

		else if (this.columnaLlena(tab))
			this.despColAbajo(tab);

		int f = tab.filaVacia(this.col);
		this.fil = f;
		tab.setFicha(this.fil, this.col, this.color);
	}

	/**
	 * Deshace el último movimiento.
	 * @param tab tablero del juego actual.
	 */
	@Override
	public void undo(Tablero tab) {
		if (this.fichaDesplazada == Ficha.VACIA)
			tab.setFicha(this.fil, this.col, Ficha.VACIA);
		else {
			this.despColArriba(tab);
			tab.setFicha(0, this.col, this.fichaDesplazada);
		}
	}

	/**
	 * Desplaza una columna hacia abajo.
	 * @param tab tablero del juego complica a desplazar.
	 */
	private void despColAbajo(Tablero tab) {
		this.fichaDesplazada = tab.getCasilla(0, this.col);

		for (int f = 0; f < ReglasComplica.FILAS_CO - 1; f++) {
			Ficha aux = tab.getCasilla(f + 1, this.col);
			tab.setFicha(f, this.col, aux);
		}

		tab.setFicha(ReglasComplica.FILAS_CO - 1, this.col, this.color);
		this.fil = ReglasComplica.FILAS_CO - 1;
	}

	/**
	 * Desplaza una columna hacia arriba.
	 * @param tab tablero del juego complica a desplazar.
	 */
	private void despColArriba(Tablero tab) {
		for (int f = ReglasComplica.FILAS_CO - 1; f > 0; f--) {
			Ficha aux = tab.getCasilla(f - 1, this.col);
			tab.setFicha(f, this.col, aux);
		}
	}
}
