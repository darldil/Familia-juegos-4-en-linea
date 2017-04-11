/**
 * Reversi 1.0.2
 * Clase Main.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.principal;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorGUI;
import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.factoria.*;
import tp.pr5.logica.Partida;
import tp.pr5.vistas.VistaConsola;
import tp.pr5.vistas.VistaGUI;

import java.util.*;

/**
 * Reversi 1.0.2: Creado por Irene González Velasco y Mauricio
 * Abbati Loureiro, 2º A Tecnología Programación Diseñado en Madrid
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Main {

	public static final String NEWLINE = System.getProperty("line.separator");

	
	public static void main(String[] args) {
		
		Opciones opc = new Opciones();
		
		boolean crearGUI = opc.parser(args);
		
		Scanner in = opc.getScanner();
		FactoriaJuego factoria = opc.getFactoria();
		ReglasJuego juego = opc.getReglas();
		
		if (factoria != null && !crearGUI) {
			Partida partida = new Partida(juego);
			ControladorConsola c = new ControladorConsola(factoria, partida, in);
			new VistaConsola(partida);
			c.run();
			in.close();
		}
		
		else if (factoria != null) {
			Partida partida = new Partida(juego);
			ControladorGUI g = new ControladorGUI(partida, factoria);
			new VistaGUI(g, partida);
		}
	}

}
