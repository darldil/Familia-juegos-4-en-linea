/**
 * Reversi 1.0.2
 * Clase ReglasReversi.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroSoloLectura;

public class ReglasReversi extends ReglasJuego {
	
	public static final int FILAS_RV = 8;
	public static final int COLUMNAS_RV = 8;
	public static final int FICHASINICIALES = 4;
	private boolean tablas;

	/**
	 * Constructora de la clase ReglasReversi
	 */
	public ReglasReversi() {
		super();
		this.tablas = false;
	}
	
	/**
	 * Crea un tablero nuevo.
	 * @return Tablero nuevo.
	 */
	@Override
	public Tablero crearTablero() {
		return new Tablero(ReglasReversi.FILAS_RV, ReglasReversi.COLUMNAS_RV);
	}

	/**
	 * Indica si existe un ganador en la partida actual. 
	 * @param fila fila en la que se ha colocado la última ficha(no se usa en todas las clases).
	 * @param col columna en la que se ha colocado la última ficha(no se usa en todas las clases).
	 * @param ultimo jugador que ha realizado el último movimiento(no se usa en todas las clases).
	 * @param tab tablero del juego actual.
	 * @return si hay o no ganador y en caso de haberlo, quién es.
	 */
	@Override
	public Ficha hayGanador(int fila, int col, Ficha ultimo, Tablero tab) {
		Ficha ganador = Ficha.VACIA;
		if (tab.completo()) {
			int contador = 0;
			for (int f = 0; f < tab.getFilas(); f++) {
				for (int c = 0; c < tab.getColumnas(); c++) {
					if (tab.getCasilla(f, c) == Ficha.BLANCA)
						contador++;
					else 
						contador--;
				}
			}
			if (contador < 0)
				ganador = Ficha.NEGRA;
			else if (contador > 0)
				ganador = Ficha.BLANCA;
			else
				this.tablas = true;
		}
		else
			ganador = ganadorAbsoluto(tab);
		
		return ganador;
	}
	
	/**
	 * Establece el jugador inicial (por defecto las negras). 
	 * @return Turno del jugador actual.
	 */
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}
	
	/**
	 * Comprueba de quién es el siguiente turno. 
	 * @param tab tablero actual de solo lectura
	 * @param actual turno del jugador actual
	 * @return siguiente turno 
	 */

	public Ficha siguienteTurno(TableroSoloLectura tab, Ficha actual) {
		final int SUMAR = 1, RESTAR = -1, IGUAL = 0;
		boolean parar = false;
		Ficha resultado = Ficha.VACIA;
		if (actual == Ficha.BLANCA)
			resultado = Ficha.NEGRA;
		else
			resultado =  Ficha.BLANCA;
		
		for (int f = 0; f < tab.getFilas() && !parar; f++) {
			for (int c = 0; c < tab.getColumnas() && !parar; c++) {
				Ficha aux = tab.getCasilla(f, c);
				if (aux == Ficha.VACIA) {
					if(ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, IGUAL, SUMAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, IGUAL, RESTAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, SUMAR, IGUAL) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, RESTAR, IGUAL) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, SUMAR, SUMAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, RESTAR, RESTAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, RESTAR, SUMAR) || 
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, resultado, SUMAR, RESTAR))
						parar = true;
				}
			}
		}
		
		if (!parar) {
			if (hayTablas(tab,actual))
				resultado = Ficha.VACIA;
			else
				resultado = actual;
		}
		
		return resultado;
	}
	
	/**
	 * Comprueba si hay tablas si no se ha llenado el tablero
	 * @param tab tablero actual
	 * @param ficha color de la ficha actual
	 * @return Si hay tablas o no
	 */
	private boolean hayTablas(TableroSoloLectura tab, Ficha ficha) {
		boolean hayTablas = true;
		final int SUMAR = 1, RESTAR = -1, IGUAL = 0;
		boolean continuar = true;
		
		for (int f = 0; f < tab.getFilas() && continuar; f++) {
			for (int c = 0; c < tab.getColumnas() && continuar; c++) {
				Ficha aux = tab.getCasilla(f, c);
				if (aux == Ficha.VACIA) {
					if(ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, IGUAL, SUMAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, IGUAL, RESTAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, SUMAR, IGUAL) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, RESTAR, IGUAL) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, SUMAR, SUMAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, RESTAR, RESTAR) ||
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, RESTAR, SUMAR) || 
							ReglasJuegoReversi.comprobarMovimiento(tab, f, c, ficha, SUMAR, RESTAR))
						continuar = false;
				}
			}
		}
		
		hayTablas = continuar;
		
		return hayTablas;
	}

	/**
	 * Comprueba si todas las fichas que hay en un tablero son de un color
	 * @param tab tablero actual
	 * @return color del tablero. Es vacio si no todas las fichas son del mismo color
	 */
	private Ficha ganadorAbsoluto(TableroSoloLectura tab) {
		int f = 0;
		boolean blanca = false, negra = false;
		while (f < tab.getFilas() && (!blanca || !negra)) {
			int c = 0;
			while(c < tab.getColumnas() && (!blanca || !negra)) {
				if (tab.getCasilla(f, c) == Ficha.BLANCA)
					blanca = true;
				else if (tab.getCasilla(f, c) == Ficha.NEGRA)
					negra = true;;
				c++;
			}
			f++;
		}
		if (negra && !blanca) 
			return Ficha.NEGRA;
		else if (blanca && !negra)
			return Ficha.BLANCA;
		
		return Ficha.VACIA;
	}

	/**
	 * Devuelve si hay empate o no. 
	 * @param tab tablero del juego.
	 * @return si hay o no tablas.
	 */
	@Override
	public boolean tablas(Tablero tab) {
		return this.tablas;
	}
	
	/**
	 * Coloca las cuatro fichas centrales del inicio de la partida
	 * @param tab tablero actual
	 */
	public void inicializarTablero(Tablero tab) {
		final int MITADFIL = tab.getFilas() / 2;
		final int MITADCOL = tab.getColumnas() / 2;
		
		tab.setFicha(MITADFIL, MITADCOL, Ficha.NEGRA);
		tab.setFicha(MITADFIL - 1, MITADCOL, Ficha.BLANCA);
		tab.setFicha(MITADFIL, MITADCOL - 1, Ficha.BLANCA);
		tab.setFicha(MITADFIL - 1, MITADCOL - 1, Ficha.NEGRA);
	}
}
