package threads;

import main.JogoArcoFlecha;

public class ThreadGeral extends Thread {

	JogoArcoFlecha jogo;
	
	public ThreadGeral(JogoArcoFlecha jogo) {
		this.jogo = jogo;
	}
	
	@Override
	public void run() {
		while(!jogo.isFim()){
			verificarFim();
			try {
				Thread.sleep(this.jogo.getTempoThreadGeral());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void verificarFim(){
		
		if(jogo.getCenario().getQtdBaloesCaindo() >= jogo.getCenario().getQtdBaloes() || jogo.getCenario().getFlechasAtiradas() >= jogo.getCenario().getQtdFlecha()) {
			//jogo.setFim(true);
		}
	}
	
}
