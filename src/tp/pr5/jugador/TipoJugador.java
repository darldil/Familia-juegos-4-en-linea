/**
 * Reversi 1.0.2
 * Enum TipoJugador.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

public enum TipoJugador {
	HUMANO, AUTOMATICO;
	
	/**
	 * 
	 */
	public String toString() {
		if (this == TipoJugador.HUMANO)
			return "Humano";
		else
			return "Automatico";
	}
	
}
