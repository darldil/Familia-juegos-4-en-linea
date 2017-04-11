/**
 * Reversi 1.0.2
 * Clase ParserAyudaComandos.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.control;

import tp.pr5.principal.Main;

public class ParserAyudaComandos {

	private static Comandos[] cmds = { new Poner(), new Reiniciar(), new Salir(), new Jugar(),
			new Deshacer(), new PonJugador(), new Ayuda() };

	/**
	 * Obtiene el texto de la ayuda de todos los comandos
	 * @return texto de la ayuda de todos los comandos
	 */
	static public String AyudaComandos() {
		String texto = "Los comandos disponibles son:" + Main.NEWLINE;

		for (int i = 0; i < cmds.length; i++) {
			texto = texto + cmds[i].textoAyuda() + Main.NEWLINE;
		}

		return texto;
	}

	/**
	 * Traduce la opción del usuario y elige que accion ejecutar.
	 * @param s comando introducido por el usuario.
	 * @return el objeto corrspondiente o null si es erróneo.
	 */
	public static Comandos parsea(String[] s) {
		int i = 0;
		boolean fin = false;
		Comandos cmd = null;
		while (i < cmds.length && !fin) {
			cmd = cmds[i].parsea(s);
			if (cmd != null)
				fin = true;
			else
				i++;
		}
		return cmd;
	}
}
