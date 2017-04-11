/**
 * Reversi 1.0.2
 * Clase VistaConsola.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TableroSoloLectura;

public class VistaConsola implements Observador{

	/**
	 * Es la constructora 
	 * @param p partida
	 */
	public VistaConsola(Partida p){
		p.addObservador(this);
	}
	
	/**
	 * Muestra la información tras resetear partida.
	 */
	@Override
	public void onReset(TableroSoloLectura tab, Ficha turno) {
		System.out.println("Bienvenido/a a Reversi 1.0.2");
		System.out.println(tab.toString());
		System.out.println("Juegan las " + turno.nombre());
	}

	/**
	 * Muestra la información tras finalizar la partida.
	 */
	@Override
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador) {
		
		System.out.println(tablero.toString());
		
		if(ganador == Ficha.BLANCA)
			System.out.println("Han ganado las " + Ficha.BLANCA.nombre());
		else if(ganador == Ficha.NEGRA)
			System.out.println("Han ganado las " + Ficha.NEGRA.nombre());
		else
			System.out.println("La partida finaliza en tablas");
		
		System.out.println("Gracias por jugar. Hasta pronto!");
	}

	/**
	 * Muestra la información tras cambiar de juego.
	 */
	@Override
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
		//No se usa aquí
	}

	/**
	 * Muestra error al deshacer.
	 */
	@Override
	public void onUndoNotPossible() {
		System.err.println("No es posible deshacer la jugada :(");
	}

	/**
	 * Muestra la información tras deshacer.
	 */
	@Override
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas) {
		System.out.println("Deshaciendo jugada...");
		System.out.println(tablero.toString());
		
		if (hayMas)
			System.out.println("Puede deshacer otra jugada");	
		else 
			System.out.println("No puede deshacer mas jugadas");
		
		System.out.println("Juegan las " + turno.nombre());
		
	}

	/**
	 * Muestra la informacióntras finalizar el movimiento.
	 */
	@Override
	public void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador,
			Ficha turno) {
		System.out.println("Han jugado las " + jugador.nombre());
		System.out.println(tablero.toString());
		System.out.println("Juegan las " + turno.nombre());
	}

	/**
	 * Muestra error al ejecutar un movimiento incorrecto.
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		System.out.println(movimientoException);
		
	}

}
