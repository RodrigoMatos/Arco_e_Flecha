package classes;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Balao {

	private Point posicao;
	private static int altura = 30;
	private static int largura = 20;
	private static int velocidadeSubindo = 3;
	private static int velocidadeDescendo = 5;
	private static Long tempoGif = 600L;
	private Image imgBalao = null;
	private Image ImgBalaoFurado = null;
	private Image ImgBalaoBoom = null;
	
	public Balao(){
		this.posicao = new Point();
		carregarImgs();
	}

	public Balao(Point point){
		this.posicao = point;
		carregarImgs();
	}
	
	private void carregarImgs(){
		//getImage(getCodeBase(), "imagens/balao.png");
		this.imgBalao = new ImageIcon("imagens/balao.png").getImage();
		this.ImgBalaoFurado = new ImageIcon("imagens/balaoFurado.png").getImage();
		this.ImgBalaoBoom = new ImageIcon("imagens/boom.gif").getImage();
	}
	
	public Point getPosicao() {
		return posicao;
	}

	public void setPosicao(Point posicao) {
		this.posicao = posicao;
	}

	public static int getAltura() {
		return altura;
	}

	public static int getLargura() {
		return largura;
	}

	public static int getVelocidadeSubindo() {
		return velocidadeSubindo;
	}

	public static int getVelocidadeDescendo() {
		return velocidadeDescendo;
	}

	public static Long getTempoGif() {
		return tempoGif;
	}

	public static void setTempoGif(Long tempoGif) {
		Balao.tempoGif = tempoGif;
	}

	public Image getImgBalao() {
		return imgBalao;
	}

	public void setImgBalao(Image imgBalao) {
		this.imgBalao = imgBalao;
	}

	public Image getImgBalaoFurado() {
		return ImgBalaoFurado;
	}

	public void setImgBalaoFurado(Image imgBalaoFurado) {
		ImgBalaoFurado = imgBalaoFurado;
	}

	public Image getImgBalaoBoom() {
		return ImgBalaoBoom;
	}

	public void setImgBalaoBoom(Image imgBalaoBoom) {
		ImgBalaoBoom = imgBalaoBoom;
	}
	
	
}
