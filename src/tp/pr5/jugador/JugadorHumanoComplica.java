/**
 * Reversi 1.0.2
 * Clase JugadorHumanoComplica.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

import java.util.*;

public class JugadorHumanoComplica extends Jugador {

	private Scanner sc;
	
	/**
	 * Constructora de la clase JugadorHumanoComplica
	 * @param sc , escaner para introducir datos por el teclado
	 */
	public JugadorHumanoComplica(Scanner sc) {
		this.sc = sc;
	}

	
	/**
	 * El jugador introduce columna en la que 
	 * desea introducir una ficha. 
	 * @param tab tablero actual
	 * @param color de la ficha
	 */
	@Override
	public void getFilaColumna(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		sc.nextLine();

		this.columna = columna - 1;
		this.fila = 0;
	}

}
