/**
 * Reversi 1.0.2
 * Clase Deshacer. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;

/**
 * Realiza las acciones necesarias para deshacer el último movimiento del
 * jugador.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Deshacer implements Comandos {

	/**
	 * Ejecuta el comando deshacer, deshace el último movimiento
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		control.undo();
	}

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return deshacer o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("deshacer")) {
				return new Deshacer();
			} else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda correspondiente a deshacer
	 */
	@Override
	public String textoAyuda() {
		return "DESHACER: Deshace el ultimo movimiento hecho en la partida";
	}
}
