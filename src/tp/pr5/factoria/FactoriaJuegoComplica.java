/**
 * Reversi 1.0.2
 * Clase FactoriaJuegoComplica.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.factoria;

import java.util.Scanner;

import tp.pr5.control.TipoJuego;
import tp.pr5.dinamica.*;
import tp.pr5.jugador.*;
import tp.pr5.logica.Ficha;

public class FactoriaJuegoComplica implements FactoriaJuego {

	private final static TipoJuego juego = TipoJuego.COMPLICA;
	/**
	 * Crea las reglas del complica
	 * @return reglas del complica
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	/**
	 * Crea un movimiento en funcion del complica
	 * @param fila fila en la que se pone la ficha
	 * @param col columna en la que se pone la ficha
	 * @param color color de la ficha
	 * @return movimiento valido
	 */
	@Override
	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	/**
	 * Crea un jugador aleatorio
	 * @return jugador aleatorio
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}
	
	/**
	 * Crea un jugador humano
	 * @param sc escaner del teclado
	 * @return jugador humano
	 */
	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoComplica(sc);
	}

	/**
	 * Crear las reglas del juego con ajustes del jugador. No es posible en esta clase
	 * @param filas filas que quiere el jugador
	 * @param columnas columnas que quiere el jugador
	 * @return reglas personalizadas, siempre null
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
		return FactoriaJuegoComplica.juego;
	}

}
