/**
 * Reversi 1.0.2
 * Enum Ficha. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.logica;

import javax.swing.ImageIcon;

/**
 * Son los tipos de fichas que se pueden encontrar en Conecta 4 y Complica.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public enum Ficha {
	VACIA, BLANCA, NEGRA;

	/**
	 * Convierte el enumerado ficha a string (por estética)
	 * @return resultado Devuelve el string con el nombre de la ficha.
	 */

	public String nombre() {

		String resultado = "";

		if (this == Ficha.BLANCA)
			resultado = "blancas";
		else if (this == Ficha.NEGRA)
			resultado = "negras";

		return resultado;
	}
	
	public static Ficha fichaInversa(Ficha color) {
		if (color == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}
	
	/**
	 * Establece un icono de ficha a un boton
	 * @return ruta del icono a colocar.
	 */
	public ImageIcon icono(){
		String path = "";
		
		if (this == Ficha.BLANCA)
			path = "Blancas.png";
		else if (this == Ficha.NEGRA)
			path = "Negras.png";
		else 
			return null;
		java.net.URL imgURL = this.getClass().getResource(path);
		if(imgURL != null) return new ImageIcon(imgURL);
		return null;
	}
	/**
	 * Convierte el enumerado ficha a simbolo (por estética)
	 * @return resultado Devuelve el string con el simbolo de la ficha.
	 */
	@Override
	public String toString() {

		String resultado = "  ";

		if (this == Ficha.BLANCA)
			resultado = "O ";
		else if (this == Ficha.NEGRA)
			resultado = "X ";

		return resultado;
	}
}
