package tp.pr5.jugador;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorHumanoReversi extends Jugador{
	
	private Scanner sc;
	
	/**
	 * Constructora de la clase JugadorHumanoReversi
	 * @param sc Escaner para introducir datos por teclado
	 */
	public JugadorHumanoReversi(Scanner sc) {
		this.sc = sc;
	}

	/**
	 * Obtiene la fila y la columna en la que realizar el movimiento pidiendo datos al usuario
	 * @param tab tablero actual
	 * @param color color del jugador actual
	 */
	@Override
	public void getFilaColumna(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		sc.nextLine();

		this.columna = columna - 1;
		this.fila = fila - 1;
	}

}
