/**
 * Reversi 1.0.2
 * Clase MovimientoReversi. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import java.util.Stack;
import tp.pr5.excepciones.CasillaOcupada;
import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;


public class MovimientoReversi extends Movimiento{
	
	private static final int SUMAR = 1;
	private static final int RESTAR = -1;
	private static final int IGUAL = 0;
	private Stack<Integer> stackFilas;
	private Stack<Integer> stackColumnas;
	private Stack<Ficha> stackFichas;
	
	/**
	 * Constructora de Movimiento.
	 * @param fil fila
	 * @param col columna
	 * @param turno turno de la ficha a colocar
	 */
	public MovimientoReversi(int fil, int col, Ficha turno) {
		super(fil, col, turno);
		this.stackFilas = new Stack<Integer>();
		this.stackColumnas = new Stack<Integer>();
		this.stackFichas = new Stack<Ficha>();
	}

	
	/**
	 * Ejecuta el movimiento seleccionado por el usuario.
	 * @param tab tablero del juego actual.
	 * @throws MovimientoInvalido si el movimiento no es correcto y lanza el correspondiente mensaje
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean ejecutado = false;
		
		if (this.posicionUsada(tab, this.fil, this.col))
			throw new CasillaOcupada();
		
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, IGUAL, SUMAR)) {
			this.transformarFichas(tab, IGUAL, SUMAR);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, IGUAL, RESTAR)) {
			this.transformarFichas(tab, IGUAL, RESTAR);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, SUMAR, IGUAL)) {
			this.transformarFichas(tab, SUMAR, IGUAL);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, RESTAR, IGUAL)) {
			this.transformarFichas(tab, RESTAR, IGUAL);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, SUMAR, SUMAR)) {
			this.transformarFichas(tab, SUMAR, SUMAR);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, RESTAR, RESTAR)) {
			this.transformarFichas(tab, RESTAR, RESTAR);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, SUMAR, RESTAR)) {
			this.transformarFichas(tab, SUMAR, RESTAR);
			ejecutado = true;
		}
		if(ReglasJuegoReversi.comprobarMovimiento(tab, this.fil, this.col, this.color, RESTAR, SUMAR)) {
			this.transformarFichas(tab, RESTAR, SUMAR);
			ejecutado = true;
		}
		if (!ejecutado) {
			throw new MovimientoInvalido ("La posicion no es correcta");
		}
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
	 * Cambia de color una serie de fichas
	 * @param tab tablero actual
	 * @param df direccion de la fila
	 * @param dc direccion de la columna
	 */
	private void transformarFichas(Tablero tab, int df, int dc) {
		int f = this.fil, c = this.col;
		Ficha aux = Ficha.VACIA;
		boolean parar = false;
		
		while ((f >= 0 && f < tab.getFilas()) && (this.color != aux) && !parar) {
			boolean contCol = false;
			while ((c >= 0 && c < tab.getColumnas()) && (!contCol) &&
					(this.color != aux)) {
				this.stackFichas.push(tab.getCasilla(f, c));
				this.stackFilas.push(f);
				this.stackColumnas.push(c);
				tab.setFicha(f, c, this.color);
				if (dc == 0) {
					contCol = true;
					c = this.col;
				}
				else if ((df != 0 && dc != 0)) 
					contCol = true;
				c = c + dc;
				if (c >= 0 && c < tab.getColumnas())
					aux = tab.getCasilla(f, c);
			}
			f = f + df;
			if ((f >= 0 && f < tab.getFilas()) && 
					(c >= 0 && c < tab.getColumnas()))
					aux = tab.getCasilla(f, c);
			else
				parar = true;
		}
	}

	/**
	 * Deshace el último movimiento.
	 * @param tab tablero del juego actual.
	 */
	@Override
	public void undo(Tablero tab) {
		while (!this.stackFichas.isEmpty()) {
			int f = this.stackFilas.pop();
			int c = this.stackColumnas.pop();
			tab.setFicha(f, c, this.stackFichas.pop());
		}
	}

}
