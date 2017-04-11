/**
 * Reversi 1.0.2
 * Clase ControladorConsola. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.*;

import java.util.*;

/**
 * Se encarga de conducir las partidas del jugador durante la ejecución de la
 * aplicación.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class ControladorConsola {
	private Partida partida;
	private Scanner in;
	private Jugador jugador1;
	private Jugador jugador2;
	private FactoriaJuego factoria;
	private boolean salir;

	
	/**
	 * Constructora de la clase controlador
	 * @param f factoria del juego actual
	 * @param partida partida actual
	 * @param in escaner 
	 */
	public ControladorConsola(FactoriaJuego f, Partida partida, Scanner in) {
		this.in = in;
		this.salir = false;
		this.factoria = f;
		this.jugador1 = f.creaJugadorHumano(this.in);
		this.jugador2 = f.creaJugadorHumano(this.in);
		this.partida = partida;
	}

	/**
	 * Reinicia una partida con los ajustes por defecto
	 * @param factoria factoria del juego actual
	 */
	public void reset(FactoriaJuego factoria) {
		this.salir = false;
		this.factoria = factoria;
		this.jugador1 = factoria.creaJugadorHumano(this.in);
		this.jugador2 = factoria.creaJugadorHumano(this.in);
		
		int f = this.factoria.getFilas();
		int c = this.factoria.getColumnas();
		
		if (f == 0 || c == 0)
			this.partida.reset(this.factoria.creaReglas());
		else 
			this.partida.reset(this.factoria.creaReglas(f, c));
		this.partida.mostrarReset();
	}
	
	/**
	 * Reinicia una partida con los ajustes por defecto
	 * @param factoria factoria del juego actual
	 * @param filas filas del tablero
	 * @param columnas columnas del tablero
	 */
	public void reset(FactoriaJuego factoria, int filas, int columnas) {
		this.salir = false;
		this.factoria = factoria;
		this.jugador1 = factoria.creaJugadorHumano(this.in);
		this.jugador2 = factoria.creaJugadorHumano(this.in);
		if (filas == 0 || columnas == 0)
			this.partida.reset(this.factoria.creaReglas());
		else 
			this.partida.reset(this.factoria.creaReglas(filas, columnas));
		this.partida.mostrarReset();
	}

	/**
	 * Ejecuta el juego hasta que acaba la partida o el usuario desea salir
	 */
	public void run() {
		this.partida.mostrarReset();
		while (!this.partida.getTerminada() && !this.salir) {
			String[] comando = this.entrada().split(" ");
			Comandos cmd = ParserAyudaComandos.parsea(comando);
			if (cmd != null)
				try {
					cmd.execute(this);
				} catch (ErrorDeEjecucion e) {
					System.err.println(e);
				}
			else
				System.err.println("Comando no reconocido");
		}
	}

	/**
	 * Introduce un comando por el teclado
	 * @return una cadena de caracteres
	 */
	private String entrada() {
		System.out.print("Introduce el comando: ");
		String comando = this.in.nextLine();

		return comando;
	}

	/**
	 * Deshace un movimiento
	 * @throws ErrorDeEjecucion lanza un mensaje si no se ha podido ejecutar
	 */
	public void undo() throws ErrorDeEjecucion {
		this.partida.undo();
	}

	/**
	 * Obtiene la factoria de juego actual
	 * @return this.factoria
	 */
	public FactoriaJuego getFactoria() {
		return this.factoria;
	}

	/**
	 * Obtiene el color jugador actual
	 * @param jugador ficha del jugador actual 
	 * @return this.jugador1 o this.jugador2 depende de que tipo de jugador sea
	 */
	public Jugador getJugadorActual(Ficha jugador) {
		if (jugador == Ficha.BLANCA)
			return this.jugador1;
		else
			return this.jugador2;
	}

	/**
	 * Obtiene el escaner
	 * @return this.in
	 */
	public Scanner getScanner() {
		return this.in;
	}

	/**
	 * Pone salir a true o false
	 * @param salir indica si el jugador quiere salir de la partida
	 */
	public void setSalir(boolean salir) {
		this.salir = salir;
	}

	/**
	 * Pone el tipo de jugador que maneja las fichas blancas
	 * @param jugador jugador que maneja las fichas blancas
	 */
	public void setJugadorBlanco(Jugador jugador) {
		this.jugador1 = jugador;
	}

	/**
	 * Pone el tipo de jugador que maneja las fichas negras
	 * @param jugador jugador que maneja las fichas negras
	 */
	public void setJugadorNegro(Jugador jugador) {
		this.jugador2 = jugador;
	}

	/**
	 * Obtiene la partida actual
	 * @return this.partida
	 */
	public Partida getPartida() {
		return this.partida;
	}
}
