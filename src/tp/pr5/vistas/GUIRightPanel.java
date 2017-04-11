/**
 * Reversi 1.0.2
 * Clase GUIRightPanel.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.factoria.FactoriaJuegoComplica;
import tp.pr5.factoria.FactoriaJuegoConecta4;
import tp.pr5.factoria.FactoriaJuegoGravity;
import tp.pr5.factoria.FactoriaJuegoReversi;
import tp.pr5.jugador.ModoJuego;
import tp.pr5.jugador.ModoJuegoAutomatico;
import tp.pr5.jugador.ModoJuegoHumano;
import tp.pr5.jugador.TipoJugador;
import tp.pr5.logica.Ficha;


@SuppressWarnings("serial")
public class GUIRightPanel  extends JPanel{
	
	private JButton reiniciar;
	private JButton deshacer;
	private JButton cambiar;
	private JButton salir;
	private JLabel filasText;
	private JLabel columnasText;
	private JLabel jug1Text;
	private JLabel jug2Text;
	private JTextField filas;
	private JTextField columnas;
	private JComboBox<TipoJuego> juego;
	private JComboBox<TipoJugador> jugadorBlanco;
	private JComboBox<TipoJugador> jugadorNegro;
	private ModoJuego modoJuego;
	private LayoutManager _layout;
	private ControladorGUI contr;
	
	/**
	 * Constructora.
	 * @param c controlador
	 */
	public GUIRightPanel(ControladorGUI c, ModoJuego j1){
		super();
		this.contr = c;
		this.reiniciar = new JButton("Reiniciar");
		this.deshacer = new JButton("Deshacer");
		this.cambiar = new JButton ("Cambiar");
		this.salir = new JButton("Salir");
		this.filasText = new JLabel("Filas");
		this.filasText.setVisible(false);
		this.columnasText = new JLabel("Columnas");
		this.columnasText.setVisible(false);
		this.jug1Text = new JLabel("Jugador Blanco");
		this.jug2Text = new JLabel("Jugador Negro");
		this.filas = new JTextField();
		this.filas.setPreferredSize(new Dimension(90,24));
		this.filas.setVisible(false);
		this.columnas = new JTextField();
		this.columnas.setPreferredSize(new Dimension(90,24));
		this.columnas.setVisible(false);
		this.jugadorBlanco = new JComboBox<TipoJugador>();
		this.jugadorNegro = new JComboBox<TipoJugador>();
		this.juego = new JComboBox<TipoJuego>();
		this.modoJuego = j1;
		this._layout = new BorderLayout();
		setLayout(this._layout);
		Opciones();
	}
	
	/**
	 * Inserta los paneles de opciones.
	 */
	public void Opciones(){
		this.ponerIconosBotones();
		add(parteSuperior(), BorderLayout.NORTH);
		add(parteCentral(), BorderLayout.CENTER);
		add(parteInferior(), BorderLayout.SOUTH);
	}
	
	/**
	 * Crea la parte superior de la interfaz.
	 * @return Panel con la información a agregar
	 */
	private JPanel parteSuperior(){
		JPanel aux = new JPanel();
		
		if (this.contr.getPartida().bloquearDeshacer()) 
			this.deshacer.setEnabled(false);
		aux.add(this.deshacer);
		aux.add(this.reiniciar);
		this.deshacer.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
				modoJuego.deshacerPulsado();
			}
		});
		this.reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				modoJuego.terminar();
				jugadorBlanco.setSelectedItem(TipoJugador.HUMANO);
				jugadorNegro.setSelectedItem(TipoJugador.HUMANO);
				contr.reiniciar();
				updateUI();
			}
		});
		aux.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), 
				"Partida"));
		
		return aux;
	}
	
	private JPanel parteCentral() {
		JPanel aux = new JPanel();
		BorderLayout _layoutPrincipal = new BorderLayout();
		aux.setLayout(_layoutPrincipal);
		
		aux.add(this.parteJugadores(),  BorderLayout.NORTH);
		aux.add(this.parteJuegos(),  BorderLayout.CENTER);
		
		return aux;
	}
	
	private JPanel parteJugadores() {
		JPanel aux = new JPanel();
		JPanel jug1 = new JPanel();
		JPanel jug2 = new JPanel();
		
		BorderLayout _layoutPrincipal = new BorderLayout();
		aux.setLayout(_layoutPrincipal);
		
		jug1.add(this.jug1Text);
		jug1.add(this.jugadorBlanco);
		jug2.add(this.jug2Text);
		jug2.add(this.jugadorNegro);
		
		aux.add(jug1, BorderLayout.NORTH);
		aux.add(jug2, BorderLayout.CENTER);
		aux.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), 
				"Gestion de jugadores"));
		
		return aux;
	}
	
	/**
	 * Crea la parte central de la interfaz.
	 * @return Panel con la información a agregar
	 */
	@SuppressWarnings("static-access")
	private JPanel parteJuegos() {
		JPanel aux = new JPanel();
		JPanel panelDatos = new JPanel();
		JPanel panelCambiar = new JPanel();
		
		BorderLayout _layoutCentral = new BorderLayout();
		FlowLayout _layoutDatos = new FlowLayout(FlowLayout.LEFT, 10, 10);
		
		inicializacomboBox();
		aux.setLayout(_layoutCentral);
		aux.add(this.juego, BorderLayout.NORTH);
		
		panelDatos.setLayout(_layoutDatos);
		
		panelDatos.add(this.filasText, BorderLayout.NORTH);
		panelDatos.add(this.filas);
		panelDatos.add(this.columnasText);
		panelDatos.add(this.columnas);
		panelCambiar.add(this.cambiar);
		this.cambiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				modoJuego.terminar();
				cambiarDeJuego();
			}
		});
		aux.add(panelDatos, _layoutCentral.CENTER);
		aux.add(panelCambiar, _layoutCentral.SOUTH);
		aux.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), 
				"Cambio de juego"));
		
		return aux;
	}
	
	/**
	 * Inicializa la lista de opciones para cambiar de juego.
	 */
	private void inicializacomboBox(){
		this.juego.addItem(TipoJuego.CONECTA4);
		this.juego.addItem(TipoJuego.COMPLICA);
		this.juego.addItem(TipoJuego.GRAVITY);
		this.juego.addItem(TipoJuego.REVERSI);
		this.juego.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (juego.getSelectedIndex() != 2) {
					filasText.setVisible(false);
					columnasText.setVisible(false);
					filas.setVisible(false);
					columnas.setVisible(false);
					revalidate();
					repaint();
				}
				else {
					filasText.setVisible(true);
					columnasText.setVisible(true);
					filas.setVisible(true);
					columnas.setVisible(true);
					revalidate();
					repaint();
				}
			}
		});
		this.juego.setSelectedItem(this.contr.getFactoria().getJuego());
		
		if (this.contr.getFactoria().getFilas() == 0)
			this.filas.setText(Integer.toString(10));
		else
			this.filas.setText(Integer.toString(this.contr.getFactoria().getFilas()));
		if (this.contr.getFactoria().getColumnas() == 0)
			this.columnas.setText(Integer.toString(10));
		else
			this.columnas.setText(Integer.toString(this.contr.getFactoria().getFilas()));
		
		this.jugadorBlanco.addItem(TipoJugador.HUMANO);
		this.jugadorBlanco.addItem(TipoJugador.AUTOMATICO);
		this.jugadorNegro.addItem(TipoJugador.HUMANO);
		this.jugadorNegro.addItem(TipoJugador.AUTOMATICO);
		this.jugadorBlanco.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				inicializarJugadorBlanco();
			}
		});
		this.jugadorNegro.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				inicializarJugadorNegro();
			}
		});
	}
	
	/**
	 * Crea la parte inferior de la interfaz.
	 * @return Panel con la información a agregar
	 */
	private JPanel parteInferior(){
		JPanel aux = new JPanel();
		
		aux.add(this.salir);
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				contr.finalizar();
			}
		});
		
		return aux;
	}
	
	/**
	 * Actualiza el boton de deshacer.
	 */
	public void actualizarEstado(Ficha turno) {
		if (this.contr.getPartida().bloquearDeshacer())
			this.deshacer.setEnabled(false);
		else 
			this.deshacer.setEnabled(true);
		
		if (this.jugadorBlanco.getSelectedItem() == TipoJugador.HUMANO)
			this.jugadorBlanco.setEnabled(true);
		if (this.jugadorNegro.getSelectedItem() == TipoJugador.HUMANO)
			this.jugadorNegro.setEnabled(true);
		if (turno == Ficha.NEGRA)
			inicializarJugadorNegro();
		else
			inicializarJugadorBlanco();
	}
	
	public void finPartida() {
		this.deshacer.setEnabled(false);
		this.cambiar.setEnabled(false);
		this.juego.setEnabled(false);
		this.filas.setEnabled(false);
		this.columnas.setEnabled(false);
		inicializarJugadorBlanco();
	}
	
	public void iniciarPartida(ModoJuego modo, Ficha turno) {
		this.modoJuego = modo;
		this.jugadorBlanco.setSelectedItem(TipoJugador.HUMANO);
		this.jugadorNegro.setSelectedItem(TipoJugador.HUMANO);
		this.actualizarEstado(turno);
		this.cambiar.setEnabled(true);
		this.juego.setEnabled(true);
		this.filas.setEnabled(true);
		this.columnas.setEnabled(true);
	}
	
	/**
	 * Prepara el panel para finalizar la partida, desactivando
	 * todos los botones.
	 */
	private void cambiarDeJuego() {
		FactoriaJuego f = null;
		if (this.juego.getSelectedItem() == TipoJuego.CONECTA4) {
			f = new FactoriaJuegoConecta4();
			this.contr.cambiarJuego(f);
		}
		
		if (this.juego.getSelectedItem() == TipoJuego.COMPLICA) {
			f = new FactoriaJuegoComplica();
			this.contr.cambiarJuego(f);
		}
		
		if (this.juego.getSelectedItem() == TipoJuego.REVERSI) {
			f = new FactoriaJuegoReversi();
			this.contr.cambiarJuego(f);
		}
		
		if (this.juego.getSelectedItem() == TipoJuego.GRAVITY) {
			if(this.filas.getText().equals("") && this.columnas.getText().equals("")) {
				f = new FactoriaJuegoGravity();
				this.contr.cambiarJuego(f);
			}
			else {
				try {
					int fil = Integer.parseInt(this.filas.getText());
					int col = Integer.parseInt(this.columnas.getText());
					f = new FactoriaJuegoGravity();
					this.contr.cambiarJuego(f, fil, col);
				} catch(NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Datos no numéricos","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		inicializarJugadorNegro();
		inicializarJugadorBlanco();
	}
	
	public TipoJugador getBlanco() {
		return (TipoJugador) this.jugadorBlanco.getSelectedItem();
	}
	
	public TipoJugador getNegro() {
		return (TipoJugador) this.jugadorNegro.getSelectedItem();
	}
	
	private void inicializarJugadorBlanco() {
		if (this.jugadorBlanco.getSelectedItem() == TipoJugador.AUTOMATICO) {
			this.modoJuego =  new ModoJuegoAutomatico(this.contr);
			this.jugadorBlanco.setEnabled(false);
		}
		else
			this.modoJuego = new ModoJuegoHumano(this.contr);
		if (this.contr.getPartida().getTurno() == Ficha.BLANCA)
			this.modoJuego.comenzar();
	}
	
	private void inicializarJugadorNegro() {
		if (this.jugadorNegro.getSelectedItem() == TipoJugador.AUTOMATICO) {
			this.modoJuego =  new ModoJuegoAutomatico(this.contr);
			this.jugadorNegro.setEnabled(false);
		}
		else
			this.modoJuego = new ModoJuegoHumano(this.contr);
		if (this.contr.getPartida().getTurno() == Ficha.NEGRA)
			this.modoJuego.comenzar();
	}
	
	/**
	 * Establece los iconos de los botones.
	 */
	private void ponerIconosBotones() {
		this.reiniciar.setIcon(createImageIcon("Reiniciar.png"));
		this.deshacer.setIcon(createImageIcon("Deshacer.png"));
		this.cambiar.setIcon(createImageIcon("Cambiar.png"));
		this.salir.setIcon(createImageIcon("Salir.png"));
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
}
