/**
 * Reversi 1.0.2
 * Clase Poner.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.dinamica.Movimiento;
import tp.pr5.excepciones.DatosIncorrectos;
import tp.pr5.excepciones.ErrorDeEjecucion;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;

/**
 * Se encarga de realizar las acciones necesarias para realizar un movimiento en
 * la partida actual.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Poner implements Comandos {

	
	@Override
	/**
	 * Ejecuta el comando poner, coloca una ficha en la posición indicada por el usuario
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {

		Partida partida = control.getPartida();
		Ficha color = partida.getTurno();
		FactoriaJuego factoria = control.getFactoria();
		Tablero tab = partida.getTablero();
		Jugador jugador = control.getJugadorActual(color);
		
		try {
			Movimiento movimiento = partida.getMovimiento(factoria, jugador);
			movimiento.columnaIncorrecta(tab);
			partida.ejecutaMovimiento(movimiento);
		} catch (DatosIncorrectos d) {
			System.err.println("Los datos insertados no son numericos");
		}
	}

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return poner o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("poner")) {
				return new Poner();
			} else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return obtiene la ayuda correspondiente a poner
	 */
	@Override
	public String textoAyuda() {
		return "PONER: utilizalo para poner la siguiente ficha";
	}
}
