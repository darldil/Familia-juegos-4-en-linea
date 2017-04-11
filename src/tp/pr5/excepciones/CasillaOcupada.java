/**
 * Reversi 1.0.2
 * Excepción CasillaOcupada.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class CasillaOcupada extends MovimientoInvalido {

	/**
	 * Muestra el mensaje de que la casilla está ocupada
	 */
	public CasillaOcupada() {
		super("Casilla Ocupada");
	}
}
