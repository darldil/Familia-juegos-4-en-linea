/**
 * Reversi 1.0.2
 * Clase ControladorGUI.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.dinamica.Movimiento;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.logica.Partida;

public class ControladorGUI {
	
	private Partida partida;
	private FactoriaJuego factoria;
	
	/**
	 * Constructora de la clase controlador
	 * @param f factoria del juego actual
	 * @param p partida actual
	 */
	public ControladorGUI(Partida p, FactoriaJuego f){
		this.partida = p;
		this.factoria = f;
	}
	
	/**
	 * Cambia el juego actual
	 * @param f factoria del juego al que se quiere cambiar
	 */
	public void cambiarJuego (FactoriaJuego f){
		this.factoria = f;
		this.partida.reset(factoria.creaReglas());
		this.partida.mostrarNuevoJuego();
	}
	
	/**
	 * Cambia el juego actual (Solo para gravity)
	 * @param fact factoria del juego al que se quiere cambiar
	 * @param f numero de filas del nuevo tablero 
	 * @param c numero de columnas del nuevo tablero
	 */
	public void cambiarJuego (FactoriaJuego fact, int f, int c){
		this.factoria = fact;
		this.partida.reset(factoria.creaReglas(f, c));
		this.partida.mostrarNuevoJuego();
	}
	
	/**
	 * Deshace un movimiento
	 */
	public void undo(){
		this.partida.undo();
	}
	
	/**
	 * Pone una ficha
	 * @param fila fila en la que se quiere poner la ficha
	 * @param columna columna en la que se quiere poner la ficha
	 */
	public void poner(int fila, int columna){

		Movimiento mov = this.factoria.creaMovimiento(fila, columna, this.partida.getTurno());
		partida.ejecutaMovimiento(mov);
	}
	
	/**
	 * Coloca una ficha automáticamente.
	 */
	public void ponerAutomatico() {
		Movimiento mov = this.partida.getMovAutomatico(this.factoria);
		this.partida.ejecutaMovimiento(mov);
	}
	
	/**
	 * Reinicia una partida con los ajustes por defecto
	 */
	public void reiniciar(){
		int f = this.factoria.getFilas();
		int c = this.factoria.getColumnas();
		
		if (f == 0 || c == 0)
			this.partida.reset(this.factoria.creaReglas());
		else 
			this.partida.reset(this.factoria.creaReglas(f, c));
		this.partida.mostrarReset();
	}
	
	/**
	 * Indica al sistema que se quiere finalizar la ejecucion del programa
	 */
	public void finalizar(){
		System.exit(0);
	}
	
	/**
	 * Obtiene la partida actual
	 * @return this.partida
	 */
	public Partida getPartida() {
		return this.partida;
	}
	
	/**
	 * Obtiene la factoria actual
	 * @return this.factoria
	 */
	public FactoriaJuego getFactoria() {
		return this.factoria;
	}
}
