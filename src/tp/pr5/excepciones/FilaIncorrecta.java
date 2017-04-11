/**
 * Reversi 1.0.2
 * Excepción FilaIncorrecta.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class FilaIncorrecta extends MovimientoInvalido {

	/**
	 * Muestra el mensaje de que la fila es incorrecta
	 * @param num numero maximo de filas del tablero
	 */
	public FilaIncorrecta(int num) {
		super("Fila incorrecta. Debe estar entre 1 y " + num);
	}

}
