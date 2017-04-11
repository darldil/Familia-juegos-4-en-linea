/**
 * Reversi 1.0.2
 * Excepción PosiciónIncorrecta.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class PosicionIncorrecta extends MovimientoInvalido {

	/**
	 * Lanza un mensaje de que la posicion no es correcta
	 */
	public PosicionIncorrecta() {
		super("Posicion incorrecta");
	}

}
