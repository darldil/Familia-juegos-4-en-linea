/**
 * Reversi 1.0.2
 * Clase ReglasComplica.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.dinamica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Contiene las reglas del juego Complica, a su vez se encarga de que estas se
 * cumplan.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class ReglasComplica extends ReglasJuego {

	public static final int FILAS_CO = 7;
	public static final int COLUMNAS_CO = 4;

	/**
	 * Constructora de la clase ReglasComplica, inicializa los atributos de las
	 * clases.
	 */
	public ReglasComplica() {
		super();
	}

	/**
	 * Crea un tablero nuevo con las dimensiones del Complica.
	 * @return Tablero nuevo.
	 */
	@Override
	public Tablero crearTablero() {
		return new Tablero(ReglasComplica.FILAS_CO, ReglasComplica.COLUMNAS_CO);
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
		Ficha aux;
		boolean grupoB = false, grupoN = false;

		for (int f = 0; f < ReglasComplica.FILAS_CO; f++) {
			for (int c = 0; c < ReglasComplica.COLUMNAS_CO; c++) {
				aux = tab.getCasilla(f, c);
				if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tab, aux, f, c)) {
					if (aux == Ficha.BLANCA)
						grupoB = true;
					else if (aux == Ficha.NEGRA)
						grupoN = true;
				}
			}
		}
		if (grupoB && !grupoN)
			ganador = Ficha.BLANCA;
		else if (grupoN && !grupoB)
			ganador = Ficha.NEGRA;

		return ganador;
	}

	/**
	 * Devuelve si hay empate o no. No se utiliza en el complica por sus normas de juego
	 * @param tab tablero del juego.
	 * @return false porque las reglas del juego impiden tablas
	 */
	@Override
	public boolean tablas(Tablero tab) {
		return false;
	}

	@Override
	public void inicializarTablero(Tablero tab) {
		//No se usa aqui
	}

}
