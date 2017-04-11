/**
 * Reversi 1.0.2
 * Clase Abstracta ReglasJuego. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroSoloLectura;

/**
 * Contiene las reglas básicas de los juegos de la familia Cuatro en línea, a su
 * vez se encarga de que estas se cumplan.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 * @author Mauricio
 */
public abstract class ReglasJuego {
	final private static Ficha JUGADOR_POR_DEFECTO = Ficha.BLANCA;

	/**
	 * Crea un tablero nuevo.
	 * @return Tablero nuevo.
	 */
	public abstract Tablero crearTablero();
	
	public abstract void inicializarTablero(Tablero tab);

	/**
	 * Establece el jugador inicial (por defecto las blancas). 
	 * @return Turno del jugador actual.
	 */
	public Ficha jugadorInicial() {
		return JUGADOR_POR_DEFECTO;
	}

	/**
	 * Indica si existe un ganador en la partida actual. 
	 * @param fila fila en la que se ha colocado la última ficha(no se usa en todas las clases).
	 * @param col columna en la que se ha colocado la última ficha(no se usa en todas las clases).
	 * @param ultimo jugador que ha realizado el último movimiento(no se usa en todas las clases).
	 * @param tab tablero del juego actual.
	 * @return si hay o no ganador y en caso de haberlo, quién es.
	 */
	public abstract Ficha hayGanador(int fila, int col, Ficha ultimo, Tablero tab);

	/**
	 * Devuelve si hay empate o no (no se usa en todas las clases). 
	 * @param tab tablero del juego.
	 * @return si hay o no tablas.
	 */
	public abstract boolean tablas(Tablero tab);

	/**
	 * Devuleve el turno del siguiente jugador.
	 * @param actual jugador actual
	 * @return siguiente juegador.
	 */
	public Ficha siguienteTurno(TableroSoloLectura tab, Ficha actual) {
		if (actual == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}
	
	/**
	 * Devuleve el turno del anterior jugador.
	 * @param actual jugador actual
	 * @return siguiente juegador.
	 */
	public Ficha turnoAnterior(Ficha actual) {
		if (actual == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}

}
