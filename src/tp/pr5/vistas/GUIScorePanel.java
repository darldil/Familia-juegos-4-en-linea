/**
 * Reversi 1.0.2
 * Clase GUIScorePanel.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.excepciones.MovimientoInvalido;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TableroSoloLectura;

@SuppressWarnings("serial")
public class GUIScorePanel extends JPanel implements Observador{
	
	private JTextField texto;
	
	/**
	 * Constructora.
	 */
	public GUIScorePanel(Partida p) {
		super();
		this.texto = new JTextField();
		this.texto.setEditable(false);
		this.texto.setBackground(Color.WHITE);
		this.texto.setText("Juegan las " + p.getTurno().nombre());
		this.texto.setPreferredSize( new Dimension( 200, 24 ) );
		this.texto.setHorizontalAlignment(JTextField.CENTER);
	}
	
	/**
	 * Obtiene el texto.
	 * @return texto del JTextField
	 */
	public JTextField getText() {
		return this.texto;
	}

	/**
	 * Muestra la información tras resetear partida.
	 */
	@Override
	public void onReset(TableroSoloLectura tab, Ficha turno) {
		this.texto.setText("Juegan las " + turno.nombre());
		this.updateUI();
	}

	/**
	 * Muestra la información tras finalizar la partida.
	 */
	@Override
	public void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador) {
		if (ganador != Ficha.VACIA)
			this.texto.setText("Partida Finalizada. Ganan las " + ganador.nombre());
		else
			this.texto.setText("La partida finaliza en tablas");
		this.updateUI();
	}

	/**
	 * Muestra la información tras cambiar de juego.
	 */
	@Override
	public void onCambioJuego(TableroSoloLectura tab, Ficha turno) {
		this.texto.setText("Juegan las " + turno.nombre());
		this.updateUI();
	}

	/**
	 * Muestra error al deshacer.
	 */
	@Override
	public void onUndoNotPossible() {
		//No se usa aquí
	}

	/**
	 * Muestra la información tras deshacer.
	 */
	@Override
	public void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas) {
		this.texto.setText("Juegan las " + turno.nombre());
		this.updateUI();
	}

	/**
	 * Muestra la informacióntras finalizar el movimiento.
	 */
	@Override
	public void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador,
			Ficha turno) {
		this.texto.setText("Juegan las " + turno.nombre());
		this.updateUI();
		
	}
	
	/**
	 * Muestra error al ejecutar un movimiento incorrecto.
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		//No se usa aquí
	}

}
