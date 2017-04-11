/**
 * Reversi 1.0.2
 * Clase TableroDeBotones.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import java.awt.Dimension;

import javax.swing.JButton;

import tp.pr5.logica.TableroSoloLectura;

@SuppressWarnings("serial")
public class TableroDeBotones extends JButton{
	private int fila;
	private int columna;
	
	/**
	 * Constructora.
	 * @param fila Filas
	 * @param columna	Columnas
	 */
	public TableroDeBotones(int fila, int columna) {
		super();
		this.setPreferredSize(new Dimension(40,40));
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Actualiza la iconografía de los botones.
	 * @param tab tablero
	 * @param f	fila
	 * @param c	columna
	 */
	public void updateButtons(TableroSoloLectura tab, int f, int c) {
		this.setIcon(tab.getCasilla(f, c).icono());
	}
	
	/**
	 * Obtiene el botón.
	 * @return boton
	 */
	public JButton getBoton() {
		return this;
	}
	
	/**
	 * Obtiene la fila.
	 * @return fila
	 */
	public int getFila() {
		return this.fila;
	}
	
	/**
	 * Obtiene la columna.
	 * @return columna
	 */
	public int getColumna() {
		return this.columna;
	}
}
