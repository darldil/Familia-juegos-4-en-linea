/**
 * Reversi 1.0.2
 * Excepción ColumnaLlena.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class ColumnaLlena extends MovimientoInvalido {

	/**
	 * Indica que la columna está llena
	 */
	public ColumnaLlena() {
		super("Columna llena");
	}

}
