/**
 * Reversi 1.0.2
 * Clase GUILeftPanel.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.ControladorGUI;
import tp.pr5.dinamica.Movimiento;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.ModoJuego;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroSoloLectura;


@SuppressWarnings("serial")
public class GUILeftPanel extends JPanel implements ActionListener {

	private JButton aleatorio;
	private GUIScorePanel panelMensaje;
	private TableroDeBotones[] tableroBotones;
	private LayoutManager _layout;
	private ControladorGUI contr;
	private Partida partida;
	private TableroSoloLectura tablero;
	private boolean tableroDesactivado;
	private ModoJuego modoJuego;
	
	/**
	 * Constructora.
	 * @param c controlador
	 */
	public GUILeftPanel(ControladorGUI c, ModoJuego modo) {
		super();
		this.aleatorio = new JButton("Aleatorio");
		this.tableroDesactivado = false;
		this.contr = c;
		this.partida = c.getPartida();
		this.tablero = this.partida.getTablero();
		this.modoJuego = modo;
		this.CrearBotones();
		this._layout = new BorderLayout();
		this.panelMensaje = new GUIScorePanel(this.partida);
		setLayout(this._layout);
		this.componerInterfaz();
	}
	
	/**
	 * Crea la interfaz principal.
	 */
	private void componerInterfaz() {
		this.aleatorio.setIcon(createImageIcon("Aleatorio.png"));
		add(parteSuperior(), BorderLayout.CENTER);
		add(parteInferior(), BorderLayout.SOUTH);
	}
	
	/**
	 * Crea la parte superior de la interfaz.
	 * @return Panel con la información a agregar
	 */
	private JPanel parteSuperior() {
		JPanel aux = new JPanel();
		BorderLayout _layoutCentral = new BorderLayout();
		aux.setLayout(_layoutCentral);
		
		aux.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), 
				"Juego"));
		
		aux.add(ObtenerTableroBotones(),  BorderLayout.CENTER);
		aux.add(ObtenerPanelMensaje(),  BorderLayout.SOUTH);
		
		return aux;
	}
	
	/**
	 * Crea la parte inferior de la interfaz.
	 * @return Panel con la información a agregar
	 */
	private JPanel parteInferior(){
		JPanel aux = new JPanel();
		
		this.aleatorio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FactoriaJuego factoria = contr.getFactoria();
				Jugador aux = factoria.creaJugadorAleatorio();
				Partida partida = contr.getPartida();
				aux.getFilaColumna((Tablero) tablero, partida.getTurno());
				Movimiento mov = aux.getMovimiento(factoria, (Tablero) tablero, partida.getTurno());
				int f = mov.getFila(), c = mov.getColumna();
				modoJuego.tableroPulsado(f, c);
			}
		});
		aux.add(this.aleatorio);
		
		return aux;
	}
	
	/**
	 * Crea el array de botones.
	 */
	private void CrearBotones() {
		int dimension = this.tablero.getFilas() * this.tablero.getColumnas();
		this.tableroBotones = new TableroDeBotones[dimension];
		int n = 0;
		
		while (n < dimension) {
			for (int f = this.tablero.getFilas() - 1; f >= 0; f--) {
				for(int c = 0; c < this.tablero.getColumnas(); c++) {
					this.tableroBotones[n] = new TableroDeBotones(f, c);
					this.tableroBotones[n].addActionListener(this);
					this.tableroBotones[n].updateButtons(this.tablero, f, c);
					n++;
				}
			}
		}
	}
	
	/**
	 * Obtiene el tablero de botones actual.
	 * @return Panel con la información de los botones.
	 */
	private JPanel ObtenerTableroBotones() {
		int dimension = this.tablero.getFilas() * this.tablero.getColumnas();
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(this.tablero.getFilas(), this.tablero.getColumnas()));
		for (int n = 0; n < dimension; n++) {
			JButton botonAux = this.tableroBotones[n].getBoton();
			aux.add(botonAux);
		}
		return aux;
	}
	
	/**
	 * Obtiene el panel del mensaje del jugador.
	 * @return Panel con la información.
	 */
	private JPanel ObtenerPanelMensaje() {
		JPanel aux = new JPanel();
		JTextField temp = this.panelMensaje.getText();
	
		aux.add(temp);
		
		return aux;
	}
	
	/**
	 * Prepara el panel para finalizar la partida, desactivando
	 * todos los botones.
	 */
	public void finPartida() {
		int n = 0;
		this.aleatorio.setEnabled(false);
		this.tableroDesactivado = true;
		for (int f = 0; f < this.tablero.getFilas(); f++) {
			for (int c = 0; c < this.tablero.getColumnas(); c++) {
				this.tableroBotones[n].removeActionListener(this);
				n++;
			}
		}
		this.panelMensaje.onPartidaTerminada(null, this.contr.getPartida().getGanador());
	}
	
	public void iniciarPartida(TableroSoloLectura tab) {
		this.tablero = tab;
		int n = 0;
		this.aleatorio.setEnabled(true);
		this.tableroDesactivado = false;
		for (int f = 0; f < this.tablero.getFilas(); f++) {
			for (int c = 0; c < this.tablero.getColumnas(); c++) {
				this.tableroBotones[n].addActionListener(this);
				n++;
			}
		}
		this.panelMensaje.onPartidaTerminada(null, this.contr.getPartida().getGanador());
	}
	
	/**
	 * Actualiza el array de botones.
	 * @param tab tablero actual.
	 */
	public void actualizarParrilla(TableroSoloLectura tab, ModoJuego modo) {
		this.tablero = tab;
		int dimension = tab.getFilas() * tab.getColumnas();
		int n = 0;
		
		while (n < dimension) {
			for (int f = tab.getFilas() - 1; f >= 0; f--) {
				for(int c = 0; c < tab.getColumnas(); c++) {
					this.tableroBotones[n].updateButtons(tab, f, c);
					n++;
				}
			}
		}
		this.modoJuego = modo;
		this.panelMensaje.onMovimientoEnd(tab, null, this.partida.getTurno());
	}
	
	public boolean getTableroDesactivado() {
		return this.tableroDesactivado;
	}
	
	/**
	 * Establece un icono a un boton.
	 * @param path Ruta del icono
	 * @return Ruta del icono
	 */
	private ImageIcon createImageIcon(String path){
		java.net.URL imgURL = this.getClass().getResource(path);
		if(imgURL != null) return new ImageIcon(imgURL);
		return null;
	}

	/**
	 * Controlador de acciones de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean parar = false;
		int n = 0;
		int dimension = this.tablero.getFilas() * this.tablero.getColumnas();
		while (n < dimension && !parar) {
			if (e.getSource() == this.tableroBotones[n]) {
				int fila = this.tableroBotones[n].getFila();
				int columna = this.tableroBotones[n].getColumna();
				this.modoJuego.tableroPulsado(fila, columna);
				parar = true;
			}
			else 
				n++;
		}
	}
}
