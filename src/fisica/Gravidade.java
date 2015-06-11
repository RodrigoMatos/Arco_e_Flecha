package fisica;

import java.awt.Point;

import classes.Cenario;
import classes.Flecha;

public class Gravidade {
	
	private int gravidade;
	private int angulo;
	private int indice;
	private float tempo;
	private float sx;
	private float tsmax;
	private float vx;
	private float vy;
	private float sy;
	private float smax;
	private float t;
	private Point[] caminho;
	
	public Gravidade(){
		limpar();
		gravidade = 9;
		angulo = 20;
	}
	
	private void limpar(){
		caminho = new Point[Cenario.width];
		indice = 0;
	}
	
	public Point[] getCaminhoFlecha(Flecha f){
		//caminho = new Point[Cenario.width];
		limpar();
		calcularCaminho(f);
		return this.caminho;
	}
	
	
	private void calcularCaminho(Flecha f){

		vx = (float) (Math.cos(angulo*Math.PI/180)*f.getVelocidadeFlecha()); //Velocidade Horizontal inivelocidade
		vy = (float) (Math.sin(angulo*Math.PI/180)*f.getVelocidadeFlecha()); //Velocidade Vertical

		//Calculando o tempo total para que o projétil atinja o solo
		tempo = (vy/gravidade) * 2;
		//Calculando o tempo necessário para que o projétil atinja a altura máxima
		tsmax = (tempo) /2;
		//Altura Máxima
		smax = ((vy * tsmax) - (gravidade * tsmax * tsmax)/2);
		/*Este loop for atualiza as funções horárias da posição de X e Y
		em função do tempo a cada 0,25 segundos, marcando um asterisco em
		cada posição(*)*/
		int y = 0;
		//for(t = 0; t <= tempo;t = (float) (t +0.25))
		for(t = 0; y < Cenario.heigth;t = (float) (t +0.25)) 
		{
			sx = (vx*t);
			sy = ((vy * t) - (gravidade * t * t)/2);

			
			if(smax < 30) {
				caminho[indice] = new Point(((int) (sx + 5)),f.getPosicao().y-((int) ((sy*2)+ 5)));
				y = f.getPosicao().y-((int) ((sy*2)+ 5));
			} else {
				caminho[indice] = new Point(((int) (sx/6 +5)),f.getPosicao().y - ((int) (sy/6 +5)));
				y = f.getPosicao().y - ((int) (sy/6 +5));
			} 
			indice++;
		}

		//Mostra dados adicionais
	    /*System.out.println("A Altura maxima e igual a " + smax + " metros"); 
	    System.out.println("O alcance maximo atingido e igual a " + sx + " metros");
	    System.out.println("O tempo do percurso e igual a " + tempo + " segundos");
	    System.out.println("Tamanho =  " + qtdPontos());*/
	}
	
	@SuppressWarnings("unused")
	private int qtdPontos(){
		int qtd = 0;
		for(int z = 0; z<caminho.length;z++){
			if(caminho[z] != null){
				qtd++;
			}
		}
		return qtd;
	}
	
}
