/**
 * Reversi 1.0.2
 * Clase MovimientoGravity. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.excepciones.CasillaOcupada;
import tp.pr5.excepciones.ColumnaIncorrecta;
import tp.pr5.excepciones.FilaIncorrecta;
import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class MovimientoGravity extends Movimiento {
	
	/**
	 * Constructora de la clase MovimientoGravity 
	 * @param fila fila a poner
	 * @param col columna a poner
	 * @param turno turno actual
	 */
	public MovimientoGravity(int fila, int col, Ficha turno) {
		super(fila, col, turno);
	}

	/**
	 * Indica si la fila introducida es correcta.
	 * @param tab Tablero actual
	 * @return booleano que indica si la fila es correcta.
	 */
	private boolean filaCorrecta(Tablero tab) {
		boolean incorrecto = false;

		if ((this.fil >= tab.getFilas()) || (this.fil < 0))
			incorrecto = true;

		return incorrecto;
	}

	/**
	 * Indica si la posicion introducida ya esta utilizada.
	 * @param tab Tablero actual
	 * @param f fila
	 * @param c columna
	 * @return booleano que indica si la posicion esta utilizada.
	 */
	private boolean posicionUsada(Tablero tab, int f, int c) {
		boolean usada = false;

		if ((tab.getCasilla(f, c)) != Ficha.VACIA)
			usada = true;

		return usada;
	}

	/**
	 * Gestiona el sistema de gravedad que atrae las fichas a los bordes
	 * @param tab tablero actual
	 */
	private void gravedad(Tablero tab) {
		final int SUMAR = 1, RESTAR = -1, IGUAL = 0;
		int dimFilas = tab.getFilas() - 1, dimCol = tab.getColumnas() - 1;
		final double MITFILAS = dimFilas / 2.0, MITCOL = dimCol / 2.0;
		
		if(!this.fichaMitad(MITFILAS, MITCOL)){
			if (this.fil > MITFILAS ) {
				if (this.col > MITCOL){
					//primer cuadrante
					if(this.fil < this.col)
						this.calcularPosicion(this.fil, this.col, tab, IGUAL, SUMAR);
					else if(this.fil > this.col)
						this.calcularPosicion(this.fil, this.col, tab, SUMAR, IGUAL);
					else
						this.calcularPosicion(this.fil, this.col, tab, SUMAR, SUMAR);
				}
				else if(this.col < MITCOL){
					//segundo cuadrante
					if((dimFilas - this.fil) > this.col)
						this.calcularPosicion(this.fil, this.col, tab, IGUAL, RESTAR);
					else if((dimFilas - this.fil) < this.col)
						this.calcularPosicion(this.fil, this.col, tab, SUMAR, IGUAL);
					else
						this.calcularPosicion(this.fil, this.col, tab, SUMAR, RESTAR);
				}
				//Justo en la mitad
				else
					this.calcularPosicion(this.fil, this.col, tab, SUMAR, IGUAL);
			}
			else if (this.fil < MITFILAS) {
				if(this.col < MITCOL){
					//tercer cuadrante
					if(this.fil > this.col)
						this.calcularPosicion(this.fil, this.col, tab, IGUAL, RESTAR);
					else if(this.fil < this.col)
						this.calcularPosicion(this.fil, this.col, tab, RESTAR, IGUAL);
					else
						this.calcularPosicion(this.fil, this.col, tab, RESTAR, RESTAR);
				}
				else if (this.col > MITCOL){
					//cuarto cuadrante
					if(this.fil > (dimCol - this.col))
						this.calcularPosicion(this.fil, this.col, tab, IGUAL, SUMAR);
					else if(this.fil < (dimCol - this.col))
						this.calcularPosicion(this.fil, this.col, tab, RESTAR, IGUAL);
					else
						this.calcularPosicion(this.fil, this.col, tab, RESTAR, SUMAR);
				}
				//Justo en la mitad
				else
					this.calcularPosicion(this.fil, this.col, tab, RESTAR, IGUAL);
			}
			else{
				//Justo en la mitad
				if (this.col < MITCOL)
					this.calcularPosicion(this.fil, this.col, tab, IGUAL, RESTAR);
				else 
					this.calcularPosicion(this.fil, this.col, tab, IGUAL, SUMAR);
			}	
		}
	}

	/**
	 * Calcula la posición exacta en la que va la ficha
	 * @param fila fila que indica el usuario
	 * @param columna columna que indica el usuario
	 * @param tab tablero actual
	 * @param df parameto que indica hacia donde se mueve la ficha en la fila
	 * @param dc parameto que indica hacia donde se mueve la ficha en la columna
	 */
	private void calcularPosicion(int fila, int columna, Tablero tab, int df,
			int dc) {
		int fil = fila;
		int col = columna;
		while (((fil > 0 && fil < tab.getFilas() - 1) && tab.getCasilla(fil, col) == Ficha.VACIA)
				&& ((col > 0 && col < tab.getColumnas() - 1) && tab.getCasilla(
						fil, col) == Ficha.VACIA)) {
			fil = fil + df;
			col = col + dc;

			if (tab.getCasilla(fil, col) == Ficha.VACIA) {
				this.fil = fil;
				this.col = col;
			}
		}
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

		else if (this.filaCorrecta(tab))
			throw new FilaIncorrecta(tab.getFilas());

		else if (this.posicionUsada(tab, this.fil, this.col))
			throw new CasillaOcupada();

		else {
			this.gravedad(tab);
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

	/**
	 * Indica si la ficha está en el centro del tablero o no
	 * @param mitf centro de las filas
	 * @param mitc centro de las columnas
	 * @return fila
	 */
	private boolean fichaMitad(double mitf, double mitc) {
		return this.fil == mitf && this.col == mitc;
	}
}
