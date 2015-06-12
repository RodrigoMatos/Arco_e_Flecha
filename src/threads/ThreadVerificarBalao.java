package threads;

import config.Configuracao;
import classes.Balao;
import classes.Flecha;
import main.JogoArcoFlecha;

public class ThreadVerificarBalao extends Thread {

	JogoArcoFlecha jogo;
	
	public ThreadVerificarBalao(JogoArcoFlecha jogo) {
		this.jogo = jogo;
	}
	
	@Override
	public void run() {
		while(!jogo.isFim()){
			this.verificarBaloes();
			this.verificarBaloesBoom();
			try {
				Thread.sleep(Configuracao.tempoThreadVerificarBalao);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void verificarBaloes() {
		
		// Verifica se a alguma flecha atingiu algum balão
		for (int i = 0; i < jogo.getCenario().getFlechasAtiradas(); i++) {
			if (jogo.getCenario().getFlechas()[i] != null) {
				for (int j = 0; j < jogo.getCenario().getQtdBaloes(); j++) {
					if (jogo.getCenario().getBaloes()[j] != null) {
						if (((jogo.getCenario().getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha() >= jogo.getCenario().getBaloes()[j].getPosicao().x) && (jogo.getCenario().getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha() <= jogo.getCenario().getBaloes()[j].getPosicao().x
								+ Balao.getLargura()))
								&& ((jogo.getCenario().getFlechas()[i].getPosicao().y >= jogo.getCenario().getBaloes()[j].getPosicao().y && jogo.getCenario().getFlechas()[i].getPosicao().y <= jogo.getCenario().getBaloes()[j].getPosicao().y + Balao.getAltura()))) {
							System.out.println("Acertou");
							jogo.getCenario().getBaloesCaindo()[jogo.getCenario().getQtdBaloesCaindo()] = jogo.getCenario().getBaloes()[j];
							jogo.getCenario().getBaloesBoom()[jogo.getCenario().getQtdBaloesCaindo()] = jogo.getCenario().getBaloes()[j];
							jogo.getCenario().getTempoFinalGif()[jogo.getCenario().getQtdBaloesCaindo()] = System.currentTimeMillis() + Balao.getTempoGif();
							jogo.getCenario().getBaloes()[j] = null;
							jogo.getCenario().setPontos(jogo.getCenario().getPontos()+Configuracao.pontosPorBalao);
							jogo.getCenario().setQtdBaloesCaindo(jogo.getCenario().getQtdBaloesCaindo()+1);
						}
					}
				}
			}
		}
	}
	
	
	private void verificarBaloesBoom() {
		
		// Verificar se existem balões estourando e se passou o tempo da explosão
		for(int i = 0; i<jogo.getCenario().getQtdBaloesCaindo(); i++){
			if(jogo.getCenario().getBaloesBoom()[i] != null){
				if(jogo.getCenario().getTempoFinalGif()[i]<=System.currentTimeMillis()){
					jogo.getCenario().getBaloesBoom()[i] = null;
				}
			}
		}
	}
	
}
