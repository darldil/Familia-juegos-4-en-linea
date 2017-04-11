/**
 * Reversi 1.0.2
 * Excepción MovimientoInválido.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.excepciones;

@SuppressWarnings("serial")
public class MovimientoInvalido extends Throwable {
	private String mensaje;

	/**
	 * Lanza un mensaje de que el movimiento no es valido
	 * @param mensaje a lanzar
	 */
	public MovimientoInvalido(String mensaje) {
		 this.mensaje = mensaje;
	}
	
	/**
	 * Convierte la excepcion en texto
	 */
	public String toString(){
		return this.mensaje;
	}
}
