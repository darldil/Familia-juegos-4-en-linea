/**
 * Reversi 1.0.2
 * Clase Jugar. 
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.excepciones.ErrorDeEjecucion;
import tp.pr5.factoria.FactoriaJuego;
import tp.pr5.factoria.FactoriaJuegoComplica;
import tp.pr5.factoria.FactoriaJuegoConecta4;
import tp.pr5.factoria.FactoriaJuegoGravity;
import tp.pr5.factoria.FactoriaJuegoReversi;

/**
 * Realiza las acciones necesarias para cambiar de juego en tiempo de ejecución.
 * 
 * @author Irene González Velasco y Mauricio Abbati Loureiro
 */
public class Jugar implements Comandos {
	private FactoriaJuego factoria;
	private int filas;
	private int columnas;

	/**
	 * Constructora de la clase Jugar.
	 */
	public Jugar() {
		this.factoria = new FactoriaJuegoConecta4();
		this.filas = -1;
		this.columnas = -1;
	}
	
	/**
	 * Constructora de la clase Jugar.
	 * @param factoria factoria actual.
	 */
	public Jugar(FactoriaJuego factoria) {
		this.factoria = factoria;
		this.filas = -1;
		this.columnas = -1;
	}
	
	/**
	 * Constructora de la clase Jugar.
	 * @param factoria factoria actual.
	 * @param f fila actual.
	 * @param c columna actual.
	 */
	public Jugar(FactoriaJuego factoria, int f, int c) {
		this.factoria = factoria;
		this.filas = f;
		this.columnas = c;
	}

	/**
	 * Traduce la cadena de caracteres introducida por el usuario a comandos
	 * @param cadena de caracteres introducida por el usuario
	 * @return jugar o null
	 */
	@Override
	public Comandos parsea(String[] cadena) {
		if (cadena.length >= 2) {
			if (cadena[0].equalsIgnoreCase("jugar")) {
				if (cadena[1].equalsIgnoreCase("co"))
					return new Jugar(new FactoriaJuegoComplica());
				else if (cadena[1].equalsIgnoreCase("c4"))
					return new Jugar(new FactoriaJuegoConecta4());
				else if (cadena[1].equalsIgnoreCase("rv"))
					return new Jugar(new FactoriaJuegoReversi());
				else if (cadena[1].equalsIgnoreCase("gr"))
					if (cadena.length == 4) {
						int filas = Integer.parseInt(cadena[2]);
						int columnas = Integer.parseInt(cadena[3]);
						return new Jugar(new FactoriaJuegoGravity(), filas, columnas);
					}
					else 
						return new Jugar(new FactoriaJuegoGravity());
				else
					return null;
			}else
				return null;
		} else
			return null;
	}

	/**
	 * Obtiene el texto con la ayuda relativa a cada comando
	 * @return la ayuda correspondiente a jugar
	 */
	@Override
	public String textoAyuda() {
		return "JUGAR [co | c4 | gr | rv] [tamX tamY]: Elige el juego al que se juega";
	}

	/**
	 * Ejecuta el comando Jugar, cambia el juego y, en el caso del gravity, redimensiona el tablero si asi lo desea el usuario
	 * @param control controlador de la partida en ejecución
	 * @throws ErrorDeEjecucion lanza un mensaje de que el comando no se ha podido ejecutar correctamente
	 */
	@Override
	public void execute(ControladorConsola control) throws ErrorDeEjecucion {
		if (this.filas == -1 || this.columnas == -1)
			control.reset(this.factoria);
		else
			control.reset(this.factoria, this.filas, this.columnas);
	}
}
