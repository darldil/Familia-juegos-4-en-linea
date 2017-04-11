/**
 * Reversi 1.0.2
 * Clase PonJugador.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;

public class PonJugador implements Comandos {

	private Ficha color;
	private String tipoJugador;

	/**
	 * Constructora de PonJugador.
	 * @param color turno elegido.
	 * @param tipoJugador humano o aleatorio.
	 */
	public PonJugador(Ficha color, String tipoJugador) {
		this.color = color;
		this.tipoJugador = tipoJugador;
	}

	/**
	 * Constructora de PonJugador.
	 */
	public PonJugador() {
		this.color = Ficha.BLANCA;
		this.tipoJugador = "humano";
	}

	/**
	 * Ejecuta el comando ponjugador, establece si un jugador es humano o aleatorio y su color
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		FactoriaJuego aux = control.getFactoria();
		Jugador jugador = null;

		if (this.color == Ficha.BLANCA) {
			if (this.tipoJugador == "humano")
				jugador = aux.creaJugadorHumano(control.getScanner());
			else
				jugador = aux.creaJugadorAleatorio();

			control.setJugadorBlanco(jugador);
		}

		else {
			if (this.tipoJugador == "humano")
				jugador = aux.creaJugadorHumano(control.getScanner());
			else
				jugador = aux.creaJugadorAleatorio();

			control.setJugadorNegro(jugador);
		}
	}
	
	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return ponjugador con la configuración del jugador correspondiente o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		String jugadorAux;
		Ficha fichaAux;

		if (cadena.length == 3) {
			if (cadena[0].equalsIgnoreCase("ponjugador")) {
				if (cadena[1].equalsIgnoreCase("humano"))
					jugadorAux = "humano";
				else if (cadena[1].equalsIgnoreCase("aleatorio"))
					jugadorAux = "aleatorio";
				else
					return null;
				if (cadena[2].equalsIgnoreCase("blanco"))
					fichaAux = Ficha.BLANCA;
				else if (cadena[2].equalsIgnoreCase("negro"))
					fichaAux = Ficha.NEGRA;
				else
					return null;

				return new PonJugador(fichaAux, jugadorAux);
			} else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda correspondiente a ponjugador
	 */
	@Override
	public String textoAyuda() {
		return "PON JUGADOR [humano | aleatorio] [blanco | negro]: Cambia el tipo de jugador";
	}
}
