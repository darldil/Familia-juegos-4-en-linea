/**
 * Reversi 1.0.2
 * Interfaz FactoriaJuego.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.factoria;

import java.util.*;

import tp.pr5.control.TipoJuego;
import tp.pr5.dinamica.Movimiento;
import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;

public interface FactoriaJuego {
	
	/**
	 * Crea las reglas de un juego
	 * @return reglas del juego
	 */
	public ReglasJuego creaReglas();
	
	/**
	 * Crear las reglas del juego con ajustes del jugador
	 * @param filas filas que quiere el jugador
	 * @param columnas columnas que quiere el jugador
	 * @return reglas personalizadas
	 */
	public ReglasJuego creaReglas(int filas, int columnas);
	
	public int getFilas();
	
	public int getColumnas();

	/**
	 * Crea un movimiento en funcion deljuego correspondiente
	 * @param fila fila en la que se pone la ficha
	 * @param col columna en la que se pone la ficha
	 * @param color color de la ficha
	 * @return movimiento valido
	 */
	public Movimiento creaMovimiento(int fila, int col, Ficha color);

	/**
	 * Crea un jugador aleatorio
	 * @return jugador aleatorio
	 */
	public Jugador creaJugadorAleatorio();

	/**
	 * Crea un jugador humano
	 * @param sc escaner del teclado
	 * @return jugador humano
	 */
	public Jugador creaJugadorHumano(Scanner sc);
	
	public TipoJuego getJuego();
}
