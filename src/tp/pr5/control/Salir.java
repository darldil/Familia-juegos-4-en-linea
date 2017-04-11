/**
 * Reversi 1.0.2
 * Clase Salir.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;

/**
 * Indica a la aplicación que debe finalizar la ejecución inmediatamente.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Salir implements Comandos {

	/**
	 * Ejecuta el comando salir, termina con la partida y la ejecución
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		control.setSalir(true);
	}

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return salir o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("salir"))
				return new Salir();
			else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda correspondiente a salir
	 */
	@Override
	public String textoAyuda() {
		return "SALIR: Termina la aplicación";
	}
}
