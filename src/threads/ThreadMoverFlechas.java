package threads;

import main.JogoArcoFlecha;
import classes.Flecha;
import config.ConfiguracaoThread;


public class ThreadMoverFlechas extends Thread {

	JogoArcoFlecha jogo;
	int i = 0;
	
	public ThreadMoverFlechas(JogoArcoFlecha jogo) {
		this.jogo = jogo;
	}
	
	@Override
	public void run() {
		while(!jogo.isFim()){
			moverFlechas();
			try {
				Thread.sleep(ConfiguracaoThread.tempoThreadMoverFlecha);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*private void moverFlechas() {

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
	}*/
	
	
	private void moverFlechas() {

		// Movimenta as flechas.
		for (int i = 0; i < jogo.getCenario().getFlechasAtiradas(); i++) {
			if (jogo.getCenario().getFlechas()[i] != null) {
				if (jogo.getCenario().getFlechas()[i].getPosicao().x < jogo.getCenario().getWidth() && jogo.getCenario().getFlechas()[i].getPosicao().y < jogo.getCenario().getHeigth()) {
				Flecha f = jogo.getCenario().getFlechas()[i];
					f.setPosicao(f.getCaminho()[f.getIndice()]);
					f.setIndice(f.getIndice()+1);
					jogo.getCenario().getFlechas()[i] = f;
				}
			}
		}
	}
	
}
