/**
 * Reversi 1.0.2
 * Clase Ayuda. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;

public class Ayuda implements Comandos {

	@Override
	/**
	 * Ejecuta el comando ayuda, muestra por pantalla todos los mensajes de ayuda de todos los comandos
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		System.out.println(ParserAyudaComandos.AyudaComandos());
	}

	/**
	 * Traduce la opción introducida por el usuario
	 * @param cadena cadena introducida por el usuario
	 * @return el comando ayuda o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length != 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("AYUDA"))
			return new Ayuda();
		else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return el mensaje correspondiente a ayuda
	 */
	@Override
	public String textoAyuda() {
		return "AYUDA: Muestra esta ayuda";
	}
}
