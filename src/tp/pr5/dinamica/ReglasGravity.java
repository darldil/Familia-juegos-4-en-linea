/**
 * Reversi 1.0.2
 * Clase ReglasGravity.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class ReglasGravity extends ReglasJuego {

	public static final int FILAS_GR = 10;
	public static final int COLUMNAS_GR = 10;
	
	private int maxFilas = ReglasGravity.FILAS_GR;
	private int maxColumnas = ReglasGravity.COLUMNAS_GR;

	/**
	 * Constructora de la clase ReglasGravity inicializa los atributos de las
	 * clases.
	 */
	public ReglasGravity() {
		super();
	}
	
	public ReglasGravity(int filas, int columnas) {
		super();
		this.maxFilas = filas;
		this.maxColumnas = columnas;
	}

	/**
	 * Crea un tablero nuevo con las dimensiones del Complica.
	 * @return Tablero nuevo.
	 */
	@Override
	public Tablero crearTablero() {
		return new Tablero(this.maxFilas, this.maxColumnas);
	}
	

	/**
	 * Indica si existe un ganador en la partida actual.
	 * @param fila en esta clase no se utiliza.
	 * @param col en esta clase no se utiliza.
	 * @param ultimo en esta clase no se utiliza.
	 * @param tab tablero del juego actual.
	 * @return si hay o no ganador y en caso de haberlo, quién es.
	 */
	@Override
	public Ficha hayGanador(int fila, int col, Ficha ultimo, Tablero tab) {
		Ficha ganador = Ficha.VACIA;

		if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tab, ultimo, fila, col))
			ganador = ultimo;

		return ganador;
	}

	/**
	 * Devuelve si hay empate o no (no se usa en todas las clases). 
	 * @param tab tablero del juego.
	 * @return si hay o no tablas.
	 */
	@Override
	public boolean tablas(Tablero tab) {
		return tab.completo();
	}
	
	public void inicializarTablero(Tablero tab) {
		//No se usa aqui
	}
}
