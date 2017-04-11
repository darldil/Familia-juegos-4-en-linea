/**
 * Reversi 1.0.2
 * Interfaz Comandos. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;

/**
 * La interfaz de Comandos contiene las funciones necesarias para gestionar los
 * comandos insertados por el usuario.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public interface Comandos {
	
	/**
	 * Ejecuta el comando
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	public void execute(ControladorConsola control) throws ErrorDeEjecucion;

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return comando a ejecutar
	 */
	public Comandos parsea(String[] cadena);

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda de cada comando
	 */
	public String textoAyuda();
}
