/**
 * Reversi 1.0.2
 * Clase Reiniciar.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;

public class Reiniciar implements Comandos{

	@Override
	/**
	 * Ejecuta el comando reiniciar, reinicia la partida en ejecución
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		control.reset(control.getFactoria());
	}

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return reiniciar o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("reiniciar")) {
				return new Reiniciar();
			} else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda correspondiente a reiniciar
	 */
	@Override
	public String textoAyuda() {
		return "REINICIAR: Reinicia la partida";
	}

}
