/**
 * Reversi 1.0.2
 * Excepción ErrorDeEjecución.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class ErrorDeEjecucion extends Throwable {

	/**
	 * Lanza un mensaje de error de ejecución
	 * @param mensaje mensaje a lanzar
	 */
	public ErrorDeEjecucion(String mensaje) {
		super(mensaje);
	}
}
