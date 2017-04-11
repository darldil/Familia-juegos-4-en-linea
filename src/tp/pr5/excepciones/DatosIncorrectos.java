/**
 * Reversi 1.0.2
 * Excepción DatosIncorrectos.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class DatosIncorrectos extends InputMismatchException {

	/**
	 * Lanza un mensaje de que los datos introducidos son incorrectos
	 * @param s mensaje a lanzar
	 */
	public DatosIncorrectos(String s) {
		super(s);
	}
}
