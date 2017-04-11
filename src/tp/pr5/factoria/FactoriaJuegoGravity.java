/**
 * Reversi 1.0.2
 * Clase FactoriaJuegoGravity.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.factoria;

import java.util.Scanner;

import tp.pr5.control.TipoJuego;
import tp.pr5.dinamica.Movimiento;
import tp.pr5.dinamica.MovimientoGravity;
import tp.pr5.dinamica.ReglasGravity;
import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.jugador.*;
import tp.pr5.logica.Ficha;

public class FactoriaJuegoGravity implements FactoriaJuego {
	
	private final static TipoJuego juego = TipoJuego.GRAVITY;
	
	private int filas = 0;
	private int columnas = 0;
	/**
	 * Crea las reglas del gravity
	 * @return reglas del gravity
	 */
	@Override
	public ReglasJuego creaReglas() {
		this.filas = 0;
		this.columnas = 0;
		return new ReglasGravity();
	}

	/**
	 * Crea un movimiento en funcion del gravity
	 * @param fila fila en la que se pone la ficha
	 * @param col columna en la que se pone la ficha
	 * @param color color de la ficha
	 * @return movimiento valido
	 */
	@Override
	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoGravity(fila, col, color);
	}

	/**
	 * Crea un jugador aleatorio
	 * @return jugador aleatorio
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	/**
	 * Crea un jugador humano
	 * @param sc escaner del teclado
	 * @return jugador humano
	 */
	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoGravity(sc);
	}

	/**
	 * Crear las reglas del juego con ajustes del jugador
	 * @param filas filas que quiere el jugador
	 * @param columnas columnas que quiere el jugador
	 * @return reglas personalizadas
	 */
	@Override
	public ReglasJuego creaReglas(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		
		if (this.filas == 0 || this.columnas == 0)
			return new ReglasGravity();
		return new ReglasGravity(filas, columnas);
	}

	@Override
	public int getFilas() {
		return this.filas;
	}

	@Override
	public int getColumnas() {
		return this.columnas;
	}
	
	public TipoJuego getJuego() {
		return FactoriaJuegoGravity.juego;
	}

}
