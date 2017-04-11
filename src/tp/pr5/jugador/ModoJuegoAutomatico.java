/**
 * Reversi 1.0.2
 * Clase ModoJuegoAutomático.
 * Creado por Irene González Velasco y Mauricio Abbati Loureiro, 2º A Tecnología Programación
 * Diseñado en Madrid
 */
package tp.pr5.jugador;

import tp.pr5.control.ControladorGUI;

public class ModoJuegoAutomatico implements ModoJuego, Runnable {

	private ControladorGUI contr;
	private Thread hebra;
	
	/**
	 * Constructor de la clase ModoJuegoAutomatico
	 * @param contr controlador actual.
	 */
	public ModoJuegoAutomatico(ControladorGUI contr){
		this.contr = contr;
	}
	
	/**
	 * Inicia un movimiento, creando una hebra distinta.
	 */
	@Override
	public void comenzar() {
		Runnable runnable = new ModoJuegoAutomatico(this.contr);
		hebra = new Thread(runnable);
		hebra.start();
	}

	/**
	 * Finaliza la ejecución de la hebra.
	 */
	@Override
	public void terminar() {
		try {
			if (hebra != null && !hebra.isInterrupted())
				hebra.interrupt();
			if (hebra != null)
				hebra.join();
		} catch (InterruptedException e) {
			hebra.interrupt();
		}
	}

	/**
	 * No se utiliza en esta clase
	 */
	@Override
	public void deshacerPulsado() {
		
	}
	
	/**
	 * No se utiliza en esta clase
	 */
	public void tableroPulsado(int f, int c) {
		
	}

	/**
	 * Implementacion del nuevo Thread
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			if(!Thread.currentThread().isInterrupted()){
				this.contr.ponerAutomatico();
			}
		} catch (InterruptedException e) {
			
		}
		
	}
}
