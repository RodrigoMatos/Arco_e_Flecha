package threads;

import java.awt.Point;

import classes.Flecha;

import main.JogoArcoFlecha;


public class ThreadMoverFlechas extends Thread {

	JogoArcoFlecha jogo;
	
	public ThreadMoverFlechas(JogoArcoFlecha jogo) {
		this.jogo = jogo;
	}
	
	@Override
	public void run() {
		while(!jogo.isFim()){
			moverFlechas();
			try {
				Thread.sleep(this.jogo.getTempoThreadMoverFlecha());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void moverFlechas() {

		// Movimenta as flechas.
		for (int i = 0; i < jogo.getCenario().getFlechasAtiradas(); i++) {
			if (jogo.getCenario().getFlechas()[i] != null) {
				if (jogo.getCenario().getFlechas()[i].getPosicao().x < jogo.getCenario().getWidth()) {
					jogo.getCenario().getFlechas()[i] = new Flecha(new Point(jogo.getCenario().getFlechas()[i].getPosicao().x + Flecha.getVelocidadeFlecha(), jogo.getCenario().getFlechas()[i].getPosicao().y));
				} else {
					jogo.getCenario().getFlechas()[i] = null;
				}
			}
		}
	}
	
}
