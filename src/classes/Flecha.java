package classes;

import java.awt.Point;

public class Flecha {

	private Point posicao;
	private static int larguraFlecha = 10;
	private static int velocidadeFlecha = 5;
	
	
	public Flecha(){
		this.posicao = new Point();
	}
	
	public Flecha (Point p){
		this.posicao = p;
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
	
	public static int getVelocidadeFlecha() {
		return velocidadeFlecha;
	}
	
	
	
	
}
