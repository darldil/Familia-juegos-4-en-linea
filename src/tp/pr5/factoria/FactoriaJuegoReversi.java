package tp.pr5.factoria;

import java.util.Scanner;

import tp.pr5.control.TipoJuego;
import tp.pr5.dinamica.Movimiento;
import tp.pr5.dinamica.MovimientoReversi;
import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.dinamica.ReglasReversi;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioReversi;
import tp.pr5.jugador.JugadorHumanoReversi;
import tp.pr5.logica.Ficha;

public class FactoriaJuegoReversi implements FactoriaJuego {
	
	private final static TipoJuego juego = TipoJuego.REVERSI;

	/**
	 * Crea las reglas de un juego
	 * @return reglas del reversi
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	
	/**
	 * No se utiliza en esta clase
	 */
	@Override
	public ReglasJuego creaReglas(int filas, int columnas) {
		return null;
	}

	/**
	 * No se utiliza en esta clase
	 */
	@Override
	public int getFilas() {
		return 0;
	}

	/**
	 * No se utiliza en esta clase
	 */
	@Override
	public int getColumnas() {
		return 0;
	}

	/**
	 * Crea un movimiento
	 * @param fila fila en la que se quiere realizar el movimiento
	 * @param col columna en la que se quiere realizar el movimiento
	 * @param color color de la ficha a poner
	 * @return Devuelve un movimiento del reversi
	 */
	@Override
	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoReversi(fila, col, color);
	}
	
	/**
	 * Crea un jugador aleatorio
	 * @return un jugador aleatorio de la clase reversi
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}
	
	/**
	 * Crea un jugador humano
	 * @param sc Escaner para introducir datos por teclado
	 * @return un jugador de la clase reversi
	 */
	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoReversi(sc);
	}
	
	/**
	 * Devuelve el juego
	 * @return el juego actual
	 */
	public TipoJuego getJuego() {
		return FactoriaJuegoReversi.juego;
	}

}
