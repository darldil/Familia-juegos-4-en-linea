/**
 * Reversi 1.0.2
 * Clase Partida.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.logica;

import java.util.ArrayList;
import java.util.Stack;

import tp.pr5.dinamica.Movimiento;
import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.excepciones.DatosIncorrectos;
import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.vistas.Observador;

/**
 * Clase encargada de gestionar una partida completa de Conecta 4 o Complica.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Partida implements Observable{
	private ReglasJuego reglas;
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private Stack<Movimiento> undoStack;
	private ArrayList<Observador> obs;

	/**
	 * Constructora Partida. Inicializa un tablero y la pila de enteros para
	 * deshacer.
	 * @param reglas reglas del juego actual.
	 */
	public Partida(ReglasJuego reglas) {
		this.undoStack = new Stack<Movimiento>();
		this.obs = new ArrayList<Observador>();
		reset(reglas);
	}

	/**
	 * Ejecuta Movimiento. Realiza las acciones necesarias para colocar una ficha en el tablero.
	 * @param mov mov movimiento deseado.
	 */	
	public void ejecutaMovimiento(Movimiento mov) {
		if(this.terminada)
			for(Observador o : obs)
				o.onMovimientoIncorrecto(new MovimientoInvalido("Partida ya terminada"));
		
		Ficha color = mov.getJugador();
		if(color != this.getTurno()) 
			for(Observador o : obs)
				o.onMovimientoIncorrecto(new MovimientoInvalido("Turno incorrecto"));
		
		try {
			mov.ejecutaMovimiento(this.tablero);
			int fila = mov.getFila();
			int columna = mov.getColumna();
			
			this.ganador = this.reglas.hayGanador(fila, columna, this.turno,
					this.tablero);
			if (this.turno != Ficha.VACIA && this.ganador == Ficha.VACIA) {
				this.turno = this.reglas.siguienteTurno(this.tablero, this.turno);
				this.undoStack.push(mov);
				for(Observador o :obs)
					o.onMovimientoEnd(this.tablero, this.reglas.turnoAnterior(turno), this.turno);
			}
			
			else {
				this.turno = Ficha.VACIA;
				this.terminada = true;
				for(Observador o :obs)
					o.onPartidaTerminada(this.tablero, this.ganador);
			}
			
			if (this.turno == Ficha.VACIA && this.ganador == Ficha.VACIA) {
				this.terminada = true;
			}

			if ((this.ganador == Ficha.VACIA) && (this.reglas.tablas(this.tablero))) {
				this.terminada = true;
				this.turno = Ficha.VACIA;
				for(Observador o :obs)
					o.onPartidaTerminada(this.tablero, this.ganador);
			}
			
		}
		catch (MovimientoInvalido e) {
			for(Observador o :obs)
				o.onMovimientoIncorrecto(e);
		}
	}
	
	/**
	 * Crea y ejecuta un movimiento automático para el modod de juego automatico
	 * @param factoria del juego actual
	 * @return movimiento automatico
	 */
	public Movimiento getMovAutomatico(FactoriaJuego factoria) {
		Jugador aux = factoria.creaJugadorAleatorio();
		aux.getFilaColumna(this.tablero, this.getTurno());
		return aux.getMovimiento(factoria, (Tablero) this.tablero, this.getTurno());
	}

	/**
	 * Obtiene el número de columnas del tablero.
	 * @return número de columnas del tablero.
	 */
	public int getColumnas() {
		return this.tablero.getColumnas();
	}

	/**
	 * Devuelve el jugador que está actualmente.
	 * @return this.turno Devuelve el jugador.
	 */
	public Ficha getTurno() {
		return this.turno;
	}

	/**
	 * Devuelve el jugador que ha ganado (Si está en VACIA la partida acaba en tablas). 
	 * @return this.ganador Devuelve el jugador.
	 */
	public Ficha getGanador() {
		return this.ganador;
	}

	/**
	 * Devuelve true si la partida ha finalizado
	 * @return this.terminada Devuelve true si la partida ha finalizado, false en caso contrario.
	 */
	public boolean getTerminada() {
		return this.terminada;
	}

	/**
	 * Obtiene el tablero actual
	 * @return  tablero actual
	 */
	public Tablero getTablero() {
		return this.tablero;
	}
	
	/**
	 * Indica si se puede deshacer un movimiento
	 * @return true si se puede deshacer, false en caso
	 * contrario
	 */
	public boolean bloquearDeshacer() {
		if (this.undoStack.empty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Deshace la ultima jugada realizada (si se pudiese).
	 */
	public void undo() {
		if(this.undoStack.empty()) {
			for(Observador o : obs)
				o.onUndoNotPossible();
			return;
		}
		Movimiento mov = this.undoStack.pop();
		mov.undo(this.tablero);
		this.turno = mov.getJugador();
		
		for(Observador o : obs)
			o.onUndo(tablero, turno, !undoStack.empty());
	}

	/**
	 * Reinicia la partida actual.
	 * @param reglas reglas del juego actual.
	 */
	public void reset(ReglasJuego reglas) {
		this.reglas = reglas;
		this.tablero = reglas.crearTablero();
		this.tablero.reset();
		reglas.inicializarTablero(this.tablero);
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		this.ganador = Ficha.VACIA;
		this.undoStack.clear();
	}
	
	/**
	 * Muestra el reset
	 */
	public void mostrarReset() {
		for(Observador o : obs)
			o.onReset(this.tablero, this.turno);
	}
	
	/**
	 * Muestra el cambio de juego
	 */
	public void mostrarNuevoJuego() {
		for(Observador o : obs)
			o.onCambioJuego(this.tablero, this.turno);
	}
	
	/**
	 * Obtiene un movimiento.
	 * @param factoria factoria del juego
	 * @param jugador jugador actual
	 * @return Movimiento realizado
	 * @throws DatosIncorrectos Datos no insertados correctamente
	 */
	public Movimiento getMovimiento(FactoriaJuego factoria, Jugador jugador) throws DatosIncorrectos {
		try {
			return jugador.getMovimiento(factoria, this.tablero, this.turno);
		} catch (DatosIncorrectos e) {
			throw e;
		}
	}

	/**
	 * Agrega un observador
	 */
	@Override
	public void addObservador(Observador o) {
		if(!this.obs.contains(o))
			this.obs.add(o);
	}

	/**
	 * Elimina un observador
	 */
	@Override
	public void removeObservador(Observador o) {
		this.obs.remove(o);
	}
}
