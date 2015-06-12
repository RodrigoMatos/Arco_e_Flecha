package classes;

import java.awt.Point;

import config.Configuracao;

public class Flecha {

	private Point posicao;
	private static int larguraFlecha = 10;
	private static int alturaFlecha = 10;
	private int velocidadeFlecha;
	private Point[] caminho;
	private int indice;
	
	
	public Flecha(){
		this.posicao = new Point();
		caminho = new Point[Configuracao.larguraCenario];
		indice = 0;
		velocidadeFlecha = Configuracao.velocidadeMinimaFlecha;
	}
	
	public Flecha (Point p){
		this.posicao = p;
		velocidadeFlecha = Configuracao.velocidadeMinimaFlecha;
	}
	
	public Point getPosicao() {
		return posicao;
	}
	
	public void setPosicao(Point posicao) {
		this.posicao = posicao;
	}
	
	public static int getLarguraFlecha() {
		return larguraFlecha;
	}
	
	public int getVelocidadeFlecha() {
		return velocidadeFlecha;
	}

	public static int getAlturaFlecha() {
		return alturaFlecha;
	}

	public static void setAlturaFlecha(int alturaFlecha) {
		Flecha.alturaFlecha = alturaFlecha;
	}

	public Point[] getCaminho() {
		return caminho;
	}

	public void setCaminho(Point[] caminho) {
		this.caminho = caminho;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setVelocidadeFlecha(int velocidadeFlecha) {
		this.velocidadeFlecha = velocidadeFlecha;
	}
	
}
