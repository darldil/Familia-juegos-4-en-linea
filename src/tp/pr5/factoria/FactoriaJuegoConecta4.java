/**
 * Reversi 1.0.2
 * Clase FactoriaJuegoConecta4.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.factoria;

import java.util.Scanner;

import tp.pr5.control.TipoJuego;
import tp.pr5.dinamica.*;
import tp.pr5.jugador.*;
import tp.pr5.logica.Ficha;

/**
 *
 * 
 */
public class FactoriaJuegoConecta4 implements FactoriaJuego {

	private final static TipoJuego juego = TipoJuego.CONECTA4;
	
	/**
	 * Crea las reglas del conecta 4
	 * @return reglas del juego conecta 4
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	/**
	 * Crea un movimiento en funcion del conecta 4
	 * @param fila fila en la que se pone la ficha
	 * @param col columna en la que se pone la ficha
	 * @param color color de la ficha
	 * @return movimiento valido
	 */
	@Override
	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	/**
	 * Crea un jugador aleatorio
	 * @return jugador aleatorio
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	/**
	 * Crea un jugador humano
	 * @param sc escaner del teclado
	 * @return jugador humano
	 */
	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoConecta4(sc);
	}

	/**
	 * Crear las reglas del juego con ajustes del jugador. No se utiliza en esta clase
	 * @param filas filas que quiere el jugador
	 * @param columnas columnas que quiere el jugador
	 * @return reglas personalizadas, siempre a null
	 */
	@Override
	public ReglasJuego creaReglas(int filas, int columnas) {
		return null;
	}

	@Override
	public int getFilas() {
		return 0;
	}

	@Override
	public int getColumnas() {
		return 0;
	}
	
	public TipoJuego getJuego() {
		return FactoriaJuegoConecta4.juego;
	}
}
