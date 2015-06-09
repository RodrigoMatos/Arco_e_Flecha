package threads;

import java.awt.Point;
import java.util.Random;

import classes.Balao;

import main.JogoArcoFlecha;


public class ThreadMoverBalao extends Thread {

	JogoArcoFlecha jogo;
	
	public ThreadMoverBalao(JogoArcoFlecha jogo) {
		this.jogo = jogo;
	}
	
	@Override
	public void run() {
		while(!jogo.isFim()){
			this.moverBaloes();
			this.moverBaloesCaindo();
			try {
				Thread.sleep(this.jogo.getTempoThreadMoverBalao());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void moverBaloesCaindo() {

		// Movimenta os bal�es.
		for (int i = 0; i < jogo.getCenario().getQtdBaloesCaindo(); i++) {
			if (jogo.getCenario().getBaloesCaindo()[i] != null) {
				if(jogo.getCenario().getBaloesCaindo()[i].getPosicao().y <= jogo.getCenario().getHeigth() - Balao.getAltura()){
					// Se n�o chegou no fim continuar movendo
					jogo.getCenario().getBaloesCaindo()[i] = new Balao(new Point(jogo.getCenario().getBaloesCaindo()[i].getPosicao().x, jogo.getCenario().getBaloesCaindo()[i].getPosicao().y + Balao.getVelocidadeDescendo()));
				} else {
					// Se chegar no fim do cenario, deve-se remover o bal�o.
					jogo.getCenario().getBaloesCaindo()[i] = null;
				}
			}
		}
	}
	
	private void moverBaloes() {

		// Movimenta os bal�es
		for (int i = 0; i < jogo.getCenario().getQtdBaloes(); i++) {
			if (jogo.getCenario().getBaloes()[i] != null) {
				// Faz o bal�o subir
				jogo.getCenario().getBaloes()[i] = new Balao(new Point(jogo.getCenario().getBaloes()[i].getPosicao().x, jogo.getCenario().getBaloes()[i].getPosicao().y - Balao.getVelocidadeSubindo()));
				// Flag random para saber se o bal�o vai para a direita ou esquerda.
				boolean moverDireita = new Random().nextBoolean();
				if(moverDireita && jogo.getCenario().getBaloes()[i].getPosicao().x < jogo.getWidth() - Balao.getLargura()){
					// Faz o bal�o ir para a direita
					jogo.getCenario().getBaloes()[i] = new Balao(new Point(jogo.getCenario().getBaloes()[i].getPosicao().x + Balao.getVelocidadeSubindo() - 1, jogo.getCenario().getBaloes()[i].getPosicao().y));
				} else if(jogo.getCenario().getBaloes()[i].getPosicao().x > jogo.getCenario().getAreaInicialBalao()) {
					// Faz o bal�o ir para esquerda
					jogo.getCenario().getBaloes()[i] = new Balao(new Point(jogo.getCenario().getBaloes()[i].getPosicao().x - Balao.getVelocidadeSubindo() - 1, jogo.getCenario().getBaloes()[i].getPosicao().y));
				}
			}
		}
	}
}
