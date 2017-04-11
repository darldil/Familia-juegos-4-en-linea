/**
 * Reversi 1.0.2
 * Interfaz Observable.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.logica;

import tp.pr5.vistas.Observador;

public interface Observable {
	/**
	 * Agrega un observador
	 * @param o observador
	 */
	void addObservador (Observador o);
	
	/**
	 * Elimina un observador
	 * @param o observador
	 */
	void removeObservador (Observador o);
}
