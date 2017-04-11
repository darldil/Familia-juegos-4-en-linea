/**
 * Reversi 1.0.2
 * Clase Opciones.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.principal;

import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr5.dinamica.ReglasJuego;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.factoria.FactoriaJuegoComplica;
import tp.pr5.factoria.FactoriaJuegoConecta4;
import tp.pr5.factoria.FactoriaJuegoGravity;
import tp.pr5.factoria.FactoriaJuegoReversi;

public class Opciones {
	
	private Scanner in;
	private FactoriaJuego factoria;
	private ReglasJuego juego;

	/**
	 * Constructora Opciones.
	 */
	public Opciones() {
		this.in = new Scanner(System.in);
		this.factoria = null;
		this.juego = null;
	}

	/**
	 * Parser. Dados unos argumentos de arranque crea un juego determinado por 
	 * el usuario o muestra una ayuda y finaliza la ejecución.
	 * @param args Argumentos pasados a través de la linea de comandos.
	 * @return cargar GUI(true) o consola(false)
	 */
	public boolean parser(String[] args) {
		boolean gui = false;
		Options cmdLineOptions = new Options();
		CommandLineParser parser = new BasicParser(); 
		cmdLineOptions.addOption("g", "game", true, "Tipo de juego (c4, co, gr, rv). Por defecto, c4.");
		cmdLineOptions.addOption("h", "help", false, "Muestra la ayuda por pantalla.");
		cmdLineOptions.addOption("u", "ui", true, "Tipo de interfaz (console, window). "
				+ "Por defecto, console");
		cmdLineOptions.addOption("x", "tamX", true, "Número de columnas del tablero "
				+ "(solo para Gravity). Por defecto, 10.");
		cmdLineOptions.addOption("y", "tamY", true, "Número de filas del tablero "
				+ "(solo para Gravity). Por defecto, 10.");
		
		try {
			CommandLine cmdLine = parser.parse(cmdLineOptions, args);
			
			if(cmdLine.hasOption("h"))
				 new HelpFormatter().printHelp("Gravity", cmdLineOptions); 
			
			else {
				if(cmdLine.hasOption("u")) {
					if (this.crearInterfaz(cmdLine)) {
						this.in.close();
						gui = true;
					}
				}
					
				if(cmdLine.hasOption("g"))
					this.crearJuego(cmdLine);
					
				else {
					this.factoria = new FactoriaJuegoConecta4();
					this.juego = this.factoria.creaReglas();
				}
			}		
				
		} catch (ParseException e) {
			this.factoria = new FactoriaJuegoConecta4();
			this.juego = this.factoria.creaReglas();
		}
		
		return gui;
	}
	
	/**
	 * Dados los parametros crea el juego.
	 * @param cmdLine Parametros del usuario.
	 */
	private void crearJuego(CommandLine cmdLine) {
		String juego = cmdLine.getOptionValue("g");
			
		if(juego.equals("c4")){
			this.factoria = new FactoriaJuegoConecta4();
			this.juego = this.factoria.creaReglas();
		}
		else if(juego.equals("co")){
			this.factoria = new FactoriaJuegoComplica();
			this.juego = this.factoria.creaReglas();
		}
		else if(juego.equals("rv")){
			this.factoria = new FactoriaJuegoReversi();
			this.juego = this.factoria.creaReglas();
		}
		else if(juego.equals("gr")){
			try{
				int x = Integer.parseInt(cmdLine.getOptionValue("x"));
				int y = Integer.parseInt(cmdLine.getOptionValue("y"));
				this.factoria = new FactoriaJuegoGravity();
				this.juego = this.factoria.creaReglas(x, y);
			}catch (NumberFormatException e){
				this.factoria = new FactoriaJuegoGravity();
				this.juego = this.factoria.creaReglas();
			}
		}
		else{
			this.factoria = new FactoriaJuegoConecta4();
			this.juego = this.factoria.creaReglas();
		}
	}
	
	private boolean crearInterfaz(CommandLine cmdLine) {
		String interfaz = cmdLine.getOptionValue("u");
		boolean crearWindow = false;
		
		if(interfaz.equals("window")){
			crearWindow = true;
		}
		
		return crearWindow;
	}

	/**
	 * Devuelve el Scanner de la clase.
	 * @return Scanner actual
	 */
	public Scanner getScanner() {
		return this.in;
	}

	/**
	 * Devuelve la factoria de la clase.
	 * @return Factoria actual
	 */
	public FactoriaJuego getFactoria() {
		return this.factoria;
	}

	/**
	 * Devuelve las reglas del juego de la clase.
	 * @return Reglas actual
	 */
	public ReglasJuego getReglas() {
		return this.juego;
	}
}
