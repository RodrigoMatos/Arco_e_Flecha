package threads;

import config.ConfiguracaoThread;
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
			// Solicitar coleta de lixo.
			System.gc();
			try {
				Thread.sleep(ConfiguracaoThread.tempoThreadGeral);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void verificarFim(){
		
		boolean fim = true;
		if(jogo.getCenario().getQtdBaloesCaindo() >= jogo.getCenario().getQtdBaloes() || jogo.getCenario().getFlechasAtiradas() >= jogo.getCenario().getQtdFlecha()) {
			loop1:
				for(int i=0; i<jogo.getCenario().getQtdFlecha();i++){
					if(jogo.getCenario().getFlechas()[i] != null){
						fim = false;
						break loop1;
					}
				}
			if(fim){
				System.out.println("Fim do jogo!!!");
				jogo.setFim(true);
			}
		}
	}
	
}
