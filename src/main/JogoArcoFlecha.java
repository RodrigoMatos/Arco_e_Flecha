package main;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;

import threads.ThreadDesenho;
import threads.ThreadGeral;
import threads.ThreadMoverBalao;
import threads.ThreadMoverFlechas;
import threads.ThreadVerificarBalao;
import classes.Cenario;
import classes.Desenho;
import classes.Flecha;

public class JogoArcoFlecha extends Applet {

	private static final long serialVersionUID = -9075169431413415047L;

	/************************ Threads **************************/
	ThreadMoverBalao threadMoverBalao = new ThreadMoverBalao(this);
	ThreadVerificarBalao threadVerificarBalao = new ThreadVerificarBalao(this);
	ThreadGeral threadGeral = new ThreadGeral(this);
	ThreadDesenho threadDesenho = new ThreadDesenho(this);
	ThreadMoverFlechas threadMoverFlecha = new ThreadMoverFlechas(this);
	
	/********* Dados Jogo *******/
	private boolean fim = false;
	private Cenario cenario;
	private Desenho desenhar;
	
	
	
	public void init() {
		desenhar = new Desenho();
		cenario = new Cenario();
		resize(cenario.getWidth(), cenario.getHeigth());

		// Iniciar Threads
		threadGeral.start();
		threadMoverBalao.start();
		threadMoverFlecha.start();
		threadVerificarBalao.start();
		threadDesenho.start();
	}

	/****************** Métodos responsáveis pelos eventos da tela **********************/
	
	// Método que desenha na tela
	@Override
	public void paint(Graphics g) {
		desenhar.desenharBalao(g, this.cenario, this);
		desenhar.desenharBalaoFurado(g, this.cenario, this);
		desenhar.desenharBalaoBoom(g, this.cenario, this);
		desenhar.desenharFlecha(g, this.cenario, this);
		desenhar.desenharArqueiro(g, this.cenario, this);
		desenhar.mostrarPontos(g, cenario, this);
	}

	public boolean mouseDown(Event evt, int x, int y) {
	
		// Evento ao clicar
		if (cenario.getFlechasAtiradas() < cenario.getQtdFlecha()) {
			cenario.getFlechas()[cenario.getFlechasAtiradas()] = new Flecha(new Point(cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y));
			cenario.setFlechasAtiradas(cenario.getFlechasAtiradas()+1);
		}
		return true;
	}

	public boolean mouseMove(Event evt, int x, int y) {
		
		// Evento ao mover o mouse
		cenario.getArqueiro().setPosicao(new Point(cenario.getPosXSeta(), y));
		return true;
	}
	
	
	/*********************************************  Gets / Sets *****************************************/

	public void setFim(boolean fim) {
		this.fim = fim;
	}
	
	public boolean isFim(){
		return this.fim;
	}

	public Cenario getCenario() {
		return cenario;
	}

	public void setCenario(Cenario cenario) {
		this.cenario = cenario;
	}
	
}
