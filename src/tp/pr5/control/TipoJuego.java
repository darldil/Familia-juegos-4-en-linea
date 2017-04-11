/**
 * Reversi 1.0.2
 * Enum TipoJuego. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

/**
 * Son los tipos de juegos a los que se puede jugar
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public enum TipoJuego {
	CONECTA4, COMPLICA, GRAVITY, REVERSI;

	/**
	 * Convierte el enumerado TipoJuego en un String.
	 * 
	 * @return String con el juego en cuestión o null si no se ha reconocido.
	 */
	@Override
	public String toString() {
		String resultado;

		if (this == TipoJuego.COMPLICA)
			resultado = "Complica";
		else if (this == TipoJuego.CONECTA4)
			resultado = "Conecta 4";
		else if (this == TipoJuego.GRAVITY)
			resultado = "Gravity";
		else if (this == TipoJuego.REVERSI) 
			resultado = "Reversi";
		else
			resultado = null;

		return resultado;
	}
	
	/**
	 * Dad un String devuelve el tipo de juego.
	 * @param tipo string insertado
	 * @return tipo de juego
	 */
	public static TipoJuego toTipo(String tipo) {
		
		if (tipo.equals("CO"))
			return TipoJuego.COMPLICA;
		else if(tipo.equals("C4"))
			return TipoJuego.CONECTA4;
		else if(tipo.equals("GR"))
			return TipoJuego.GRAVITY;
		else
			return TipoJuego.REVERSI;
	}
}
