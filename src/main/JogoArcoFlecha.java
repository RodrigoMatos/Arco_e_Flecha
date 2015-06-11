package main;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import threads.ThreadDesenho;
import threads.ThreadGeral;
import threads.ThreadMoverBalao;
import threads.ThreadMoverFlechas;
import threads.ThreadVerificarBalao;
import classes.Cenario;
import classes.Desenho;
import classes.Flecha;
import config.ConfiguracaoThread;
import fisica.Gravidade;

public class JogoArcoFlecha extends Applet implements MouseMotionListener, MouseListener{

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
	private Gravidade gravidade;

	private Long tempo;
	private int velocidade = ConfiguracaoThread.velocidadeIncialFlecha;
	
	public void init() {
		addMouseMotionListener(this);
		addMouseListener(this);
		novoJogo();
		resize(cenario.getWidth(), cenario.getHeigth());
	}
	
	public void novoJogo(){
		desenhar = new Desenho();
		cenario = new Cenario();
		gravidade = new Gravidade();
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

	
	
	/************************************** Eventos do mouse *************************************/
	
	// Ao clicar
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (cenario.getFlechasAtiradas() < cenario.getQtdFlecha()) {
			Flecha f = new Flecha(new Point(cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y));
			f.setVelocidadeFlecha(velocidade);
			velocidade = ConfiguracaoThread.velocidadeIncialFlecha;
			f.setCaminho(gravidade.getCaminhoFlecha(f));
			gravidade = new Gravidade();
			//cenario.getFlechas()[cenario.getFlechasAtiradas()] = new Flecha(new Point(cenario.getArqueiro().getPosicao().x + cenario.getArqueiro().getLargura(), cenario.getArqueiro().getPosicao().y));
			cenario.getFlechas()[cenario.getFlechasAtiradas()] = f;
			cenario.setFlechasAtiradas(cenario.getFlechasAtiradas()+1);
		}
	}

	// Ao pressionar
	@Override
	public void mousePressed(MouseEvent e) {
		tempo = System.currentTimeMillis();
		
	}
	
	// Ao soltar click
	@Override
	public void mouseReleased(MouseEvent e) {
		
		int velocidadeTemp = (int)(System.currentTimeMillis() - tempo)/10;
		if(velocidadeTemp<ConfiguracaoThread.velocidadeMaxFlecha)
			velocidade = velocidadeTemp + velocidade;
		else
			velocidade = ConfiguracaoThread.velocidadeMaxFlecha;
	}

	// Ao entrar
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Entered.");
	}

	// Ao sair
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Exited.");
	}

	// Ao arrastar
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Dragged.");
	}

	// Ao mover
	@Override
	public void mouseMoved(MouseEvent e) {
		cenario.getArqueiro().setPosicao(new Point(cenario.getPosXSeta(), e.getY()));
	}
	
}
