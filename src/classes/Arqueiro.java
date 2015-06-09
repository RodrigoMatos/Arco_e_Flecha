package classes;

import java.awt.Point;

public class Arqueiro {
	
	private Point posicao;
	private Flecha[] flechas;
	private int qtdFlecha = 10;
	private int flechasAtiradas = 0;
	private int largura = 20;
	private int altura = 30;
	
	public Arqueiro(){
		flechas = new Flecha[qtdFlecha];
		posicao = new Point(0, 0);
	}

	public Point getPosicao() {
		return posicao;
	}

	public void setPosicao(Point posicao) {
		this.posicao = posicao;
	}

	public Flecha[] getFlechas() {
		return flechas;
	}

	public void setFlechas(Flecha[] flechas) {
		this.flechas = flechas;
	}

	public int getQtdFlecha() {
		return qtdFlecha;
	}

	public void setQtdFlecha(int qtdFlecha) {
		this.qtdFlecha = qtdFlecha;
	}

	public int getFlechasAtiradas() {
		return flechasAtiradas;
	}

	public void setFlechasAtiradas(int flechasAtiradas) {
		this.flechasAtiradas = flechasAtiradas;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	
}
