/**
 * Reversi 1.0.2
 * Excepción ColumnaIncorrecta.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class ColumnaIncorrecta extends MovimientoInvalido {

	/**
	 * Muestra el mensaje de que la columna es incorrecta
	 * @param num numero maximo de columnas del tablero
	 */
	public ColumnaIncorrecta(int num) {
		super("Columna incorrecta. Debe estar entre 1 y " + num);
	}

}
