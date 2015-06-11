package classes;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import config.Configuracao;

public class Desenho {

	public void desenharArqueiro(Graphics g, Cenario cenario, ImageObserver imgObs) {

		// Desenha uma seta (no lugar do arqueiro).
		g.drawLine(cenario.getArqueiro().getPosicao().x, cenario.getArqueiro().getPosicao().y, cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y);
		g.drawLine(cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura() - 5, cenario.getArqueiro().getPosicao().y - 5, cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y);
		g.drawLine(cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura() - 5, cenario.getArqueiro().getPosicao().y + 5, cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y);
	}


	public void desenharBalao(Graphics g, Cenario cenario, ImageObserver imgObs) {

		// Desenhar balões
		for (int i = 0; i < cenario.getQtdBaloes(); i++) {
			if (cenario.getBaloes()[i] != null) {
				if (cenario.getBaloes()[i].getPosicao().y < - Balao.getAltura()) {
					cenario.getBaloes()[i].getPosicao().y = Configuracao.alturaCenario - 1;
				}
				g.drawImage(new Balao().getImgBalao(), cenario.getBaloes()[i].getPosicao().x, cenario.getBaloes()[i].getPosicao().y, Balao.getLargura(), Balao.getAltura(), imgObs);
			}
		}
	}

	public void desenharBalaoFurado(Graphics g, Cenario cenario, ImageObserver imgObs) {

		// Desenhar balões furados
		for (int i = 0; i < cenario.getQtdBaloesCaindo(); i++) {
			if (cenario.getBaloesCaindo()[i] != null) {
				g.drawImage(new Balao().getImgBalaoFurado(), cenario.getBaloesCaindo()[i].getPosicao().x, cenario.getBaloesCaindo()[i].getPosicao().y, Balao.getLargura(), Balao.getAltura(), imgObs);
			}
		}
	}

	public void desenharBalaoBoom(Graphics g, Cenario cenario, ImageObserver imgObs) {

		// Desenhar explosão
		for (int i = 0; i < cenario.getQtdBaloesCaindo(); i++) {
			if (cenario.getBaloesBoom()[i] != null) {
				g.drawImage(new Balao().getImgBalaoBoom(), cenario.getBaloesBoom()[i].getPosicao().x, cenario.getBaloesBoom()[i].getPosicao().y, Balao.getLargura(), Balao.getAltura(), imgObs);
			}
		}

	}

	public void desenharFlecha(Graphics g, Cenario cenario, ImageObserver imgObs) {

		// Desenha flechas.
		for (int i = 0; i < cenario.getFlechasAtiradas(); i++) {
			if (cenario.getFlechas()[i] != null) {
				/*int x = cenario.getFlechas()[i].getCaminho()[cenario.getFlechas()[i].getIndice()].x;
				int y = cenario.getFlechas()[i].getCaminho()[cenario.getFlechas()[i].getIndice()].y;
				g.drawLine(x, y, x + Flecha.getLarguraFlecha(), y);
				g.drawLine(x + Flecha.getLarguraFlecha() - 3, y - 3, x + Flecha.getLarguraFlecha(), y);
				g.drawLine(x + Flecha.getLarguraFlecha() - 3, y + 3, x + Flecha.getLarguraFlecha(), y);*/
			
				g.drawLine(cenario.getFlechas()[i].getPosicao().x, cenario.getFlechas()[i].getPosicao().y, cenario.getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha(), cenario.getFlechas()[i].getPosicao().y);
				/*	
			 	g.drawLine(cenario.getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha() - 3, cenario.getFlechas()[i].getPosicao().y - 3, cenario.getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha(), cenario.getFlechas()[i].getPosicao().y);
				g.drawLine(cenario.getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha() - 3, cenario.getFlechas()[i].getPosicao().y + 3, cenario.getFlechas()[i].getPosicao().x + Flecha.getLarguraFlecha(), cenario.getFlechas()[i].getPosicao().y);
				*/
			}
		}
	}

	public void mostrarPontos(Graphics g, Cenario cenario, ImageObserver imgObs) {
		
		// Exibir pontuação atual do jogador
		g.drawString("Pontos: " + cenario.getPontos(), cenario.getPosXPontos(), Configuracao.alturaCenario);
	}
	
}
