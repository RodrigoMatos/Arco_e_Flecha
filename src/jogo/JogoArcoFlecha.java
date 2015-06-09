package jogo;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

public class JogoArcoFlecha extends Applet implements Runnable {

	private static final long serialVersionUID = -9075169431413415047L;

	private volatile Thread mainThread = null;
	
	int pontos = 0;
	boolean fim = false;
	
	// dados cenario
	int width = 400;
	int heigth = 300;
	
	// Posições iniciais de objetos
	int posXPontos = 0;
	int posXSeta = 0;

	// Dados do arqueiro
	Point seta;
	int larguraSeta = 20;

	// Dados baloes
	Point baloes[];
	Point baloesCaindo[];
	int larguraBalao = 20;
	int alturaBalao = 30;
	int qtdBaloes = 10;
	int qtdBaloesCaindo = 0;
	int velocidadeSubindo = 3;
	int velocidadeDescendo = 5;
	int areaInicialBalao = 160;// Posição X que os balões não irão ultrapassar na tela
		Long tempoGif = 600L;
		Image imgBalao = null;
		Image ImgBalaoFurado = null;
		Image ImgBalaoBoom = null;
		Point[] baloesBoom;
		Long[] tempoFinalGif;
	

	// Dados flechas
	Point flechas[];
	int qtdFlecha = 10;
	int larguraFlecha = 10;
	int flechasAtiradas = 0;
	int velocidadeFlecha = 5;

	public void init() {
		
		resize(width, heigth);
		baloes = new Point[qtdBaloes];
		baloesCaindo = new Point[qtdBaloes];
		baloesBoom = new Point[qtdBaloes];
		tempoFinalGif = new Long[qtdBaloes];
		flechas = new Point[qtdFlecha];
		seta = new Point(0, 0);
		
		criarBaloes();
		
		imgBalao = getImage(getCodeBase(), "jogo/balao.png");
		ImgBalaoFurado = getImage(getCodeBase(), "jogo/balaoFurado.png");
		ImgBalaoBoom = getImage(getCodeBase(), "jogo/boom.gif");;
	}

	@Override
	public void start() {

		if (mainThread == null) {
			mainThread = new Thread(this, "Main");
			mainThread.start();
		}
	}

	@Override
	public void run() {

		Thread myThread = Thread.currentThread();
		while (mainThread == myThread || !fim) {
			moverBaloes();
			moverBaloesCaindo();
			moverFlechas();
			verificarBaloes();
			verificarFim();
			verificarBaloesBoom();
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		mainThread = null;
	}

	
	// Método que desenha na tela
	@Override
	public void paint(Graphics g) {
		
		// Desenhar balões subindo
		desenharBalao(g);
		
		// Desenhar balões caindo
		desenharBalaoFurado(g);
		
		// Desenhar balões explodindo
		desenharBalaoBoom(g);
		
		// Desenhar flechas
		desenharFlecha(g);
		
		// Desenhar seta
		desenharSeta(g);
		
		// Exibir pontuação atual
		mostrarPontos(g);
	}

	public void criarBaloes() {

		// Criar balões em posições aleatorias.
		Random num = new Random();
		int criados = 0;
		int x, y;
		boolean criar = true;

		while (criados < qtdBaloes) {
			x = num.nextInt(width);
			y = num.nextInt(heigth);

			// Diminuir a probabilidade de que um balão fique em cima de outro:
			if (x > areaInicialBalao && x < width - larguraBalao) {
				if (criados == 0) {
					baloes[criados] = new Point(x, y);
					criados++;
				} else {
					criar = true;
					for (int i = 0; i < criados && criar; i++) {
						if (x >= baloes[i].x-larguraBalao && x <= baloes[i].x + larguraBalao) {
							if (y >= baloes[i].y-alturaBalao && y <= baloes[i].y + alturaBalao) {
								criar = false;
							}
						}
					}
					if (criar) {
						baloes[criados] = new Point(x, y);
						criados++;
						criar = false;
					}
				}
			}
		}
	}

	public void verificarBaloes() {
		
		// Verifica se a alguma flecha atingiu algum balão
		for (int i = 0; i < flechasAtiradas; i++) {
			if (flechas[i] != null) {
				for (int j = 0; j < qtdBaloes; j++) {
					if (baloes[j] != null) {
						if (((flechas[i].x + larguraFlecha >= baloes[j].x) && (flechas[i].x + larguraFlecha <= baloes[j].x
								+ larguraBalao))
								&& (flechas[i].y >= baloes[j].y && flechas[i].y <= baloes[j].y + alturaBalao)) {
							System.out.println("Acertou");
							baloesCaindo[qtdBaloesCaindo] = baloes[j];
							baloesBoom[qtdBaloesCaindo] = baloes[j];
							tempoFinalGif[qtdBaloesCaindo] = System.currentTimeMillis() + tempoGif;
							baloes[j] = null;
							pontos++;
							qtdBaloesCaindo++;
						}
					}
				}
			}
		}
	}
	
	public void verificarBaloesBoom() {
		
		// Verificar se existem balões estourando e se passou o tempo da explosão
		for(int i = 0; i<qtdBaloesCaindo; i++){
			if(baloesBoom[i] != null){
				if(tempoFinalGif[i]<=System.currentTimeMillis()){
					baloesBoom[i] = null;
				}
			}
		}
	}
	
	public void verificarFim(){
		
		if(qtdBaloesCaindo >= qtdBaloes || flechasAtiradas >= qtdFlecha) {
			fim = true;
		}
	}

	/****************** Métodos responsáveis pelos eventos da tela **********************/
	
	public boolean mouseDown(Event evt, int x, int y) {
	
		// Evento ao clicar
		if (flechasAtiradas < qtdFlecha) {
			flechas[flechasAtiradas] = new Point(seta.x + larguraSeta, seta.y);
			flechasAtiradas++;
		}
		return true;
	}

	public boolean mouseMove(Event evt, int x, int y) {
		
		// Evento ao mover o mouse
		seta = new Point(posXSeta, y);
		return true;
	}
	
	
	/****************************** Métodos responsáveis por movimentar objetos ***********************************/
	
	public void moverBaloes() {

		// Movimenta os balões
		for (int i = 0; i < qtdBaloes; i++) {
			if (baloes[i] != null) {
				// Faz o balão subir
				baloes[i] = new Point(baloes[i].x, baloes[i].y - velocidadeSubindo);
				// Flag random para saber se o balão vai para a direita ou esquerda.
				boolean moverDireita = new Random().nextBoolean();
				if(moverDireita && baloes[i].x < width-larguraBalao){
					// Faz o balão ir para a direita
					baloes[i] = new Point(baloes[i].x+velocidadeSubindo-1, baloes[i].y);
				} else if(baloes[i].x > areaInicialBalao) {
					// Faz o balão ir para esquerda
					baloes[i] = new Point(baloes[i].x-velocidadeSubindo-1, baloes[i].y);
				}
			}
		}
	}
	
	public void moverBaloesCaindo() {

		// Movimenta os balões.
		for (int i = 0; i < qtdBaloesCaindo; i++) {
			if (baloesCaindo[i] != null) {
				if(baloesCaindo[i].y <= heigth - alturaBalao){
					// Se não chegou no fim continuar movendo
					baloesCaindo[i] = new Point(baloesCaindo[i].x, baloesCaindo[i].y + velocidadeDescendo);
				} else {
					// Se chegar no fim do cenario, deve-se remover o balão.
					baloesCaindo[i] = null;
				}
			}
		}
	}

	public void moverFlechas() {

		// Movimenta as flechas.
		for (int i = 0; i < flechasAtiradas; i++) {
			if (flechas[i] != null) {
				if (flechas[i].x < width) {
					flechas[i] = new Point(flechas[i].x + velocidadeFlecha, flechas[i].y);
				} else {
					flechas[i] = null;
				}
			}
		}
	}
	
	
	/**********************  Métodos responsáveis por desenhar na tela: *********************/
	
	public void mostrarPontos(Graphics g) {
		
		// Exibir pontuação atual do jogador
		g.drawString("Pontos: " + pontos, posXPontos, heigth);
	}
	
	public void desenharSeta(Graphics g) {
		
		// Desenha uma seta (no lugar do arqueiro).
		g.drawLine(seta.x, seta.y, seta.x + larguraSeta, seta.y);
		g.drawLine(seta.x + larguraSeta - 5, seta.y - 5, seta.x + larguraSeta, seta.y);
		g.drawLine(seta.x + larguraSeta - 5, seta.y + 5, seta.x + larguraSeta, seta.y);
	}

	public void desenharBalao(Graphics g) {
		
		// Desenhar balões
		for (int i = 0; i < qtdBaloes; i++) {
			if (baloes[i] != null) {
				if (baloes[i].y < -alturaBalao) {
					baloes[i].y = heigth - 1;
				}
				g.drawImage(imgBalao, baloes[i].x, baloes[i].y, larguraBalao, alturaBalao, this);
			}
		}
	}
	
	public void desenharBalaoFurado(Graphics g) {
		
		// Desenhar balões furados
		for (int i = 0; i < qtdBaloesCaindo; i++) {
			if (baloesCaindo[i] != null) {
				g.drawImage(ImgBalaoFurado, baloesCaindo[i].x, baloesCaindo[i].y, larguraBalao, alturaBalao, this);
			}
		}
	}
	
	public void desenharBalaoBoom(Graphics g) {

		// Desenhar explosão
		for (int i = 0; i < qtdBaloesCaindo; i++) {
			if (baloesBoom[i] != null) {
				g.drawImage(ImgBalaoBoom, baloesBoom[i].x, baloesBoom[i].y, larguraBalao, alturaBalao, this);
			}
		}
		
	}

	public void desenharFlecha(Graphics g) {

		// Desenha flechas.
		for (int i = 0; i < flechasAtiradas; i++) {
			if (flechas[i] != null) {
				g.drawLine(flechas[i].x, flechas[i].y, flechas[i].x + larguraFlecha, flechas[i].y);
				g.drawLine(flechas[i].x + larguraFlecha - 3, flechas[i].y - 3, flechas[i].x + larguraFlecha, flechas[i].y);
				g.drawLine(flechas[i].x + larguraFlecha - 3, flechas[i].y + 3, flechas[i].x + larguraFlecha, flechas[i].y);
			}
		}
	}

}
