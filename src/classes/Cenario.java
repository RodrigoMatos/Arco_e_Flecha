package classes;

import java.awt.Point;
import java.util.Random;

public class Cenario {
	
	private int width = 400;
	private int heigth = 300;
	
	private Balao[] baloes;
	private Balao[] baloesCaindo;
	private Balao[] baloesBoom;
	private int qtdBaloes = 10;
	private int qtdBaloesCaindo = 0;
	private int areaInicialBalao = 160;// Posição X que os balões não irão ultrapassar na tela
	private Long[] tempoFinalGif;
	
	// Posições iniciais de objetos
	private int posXPontos = 0;
	private int posXSeta = 0;
	
	Arqueiro arqueiro;
	
	private Flecha[] flechas;
	private int qtdFlecha = 10;
	private int flechasAtiradas = 0;
	
	private int pontos = 0;
	
	public Cenario(){
		tempoFinalGif = new Long[qtdBaloes];
		flechas = new Flecha[qtdFlecha];
		baloes = new Balao[qtdBaloes];
		baloesCaindo = new Balao[qtdBaloes];
		baloesBoom = new Balao[qtdBaloes];
		arqueiro = new Arqueiro();
		criarBaloes();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getPosXPontos() {
		return posXPontos;
	}

	public void setPosXPontos(int posXPontos) {
		this.posXPontos = posXPontos;
	}

	public int getPosXSeta() {
		return posXSeta;
	}

	public void setPosXSeta(int posXSeta) {
		this.posXSeta = posXSeta;
	}

	public Balao[] getBaloes() {
		return baloes;
	}

	public void setBaloes(Balao[] baloes) {
		this.baloes = baloes;
	}

	public Balao[] getBaloesCaindo() {
		return baloesCaindo;
	}

	public void setBaloesCaindo(Balao[] baloesCaindo) {
		this.baloesCaindo = baloesCaindo;
	}

	public Balao[] getBaloesBoom() {
		return baloesBoom;
	}

	public void setBaloesBoom(Balao[] baloesBoom) {
		this.baloesBoom = baloesBoom;
	}

	public int getQtdBaloes() {
		return qtdBaloes;
	}

	public void setQtdBaloes(int qtdBaloes) {
		this.qtdBaloes = qtdBaloes;
	}

	public int getQtdBaloesCaindo() {
		return qtdBaloesCaindo;
	}

	public void setQtdBaloesCaindo(int qtdBaloesCaindo) {
		this.qtdBaloesCaindo = qtdBaloesCaindo;
	}

	public int getAreaInicialBalao() {
		return areaInicialBalao;
	}

	public void setAreaInicialBalao(int areaInicialBalao) {
		this.areaInicialBalao = areaInicialBalao;
	}
	
	public Arqueiro getArqueiro() {
		return arqueiro;
	}

	public void setArqueiro(Arqueiro arqueiro) {
		this.arqueiro = arqueiro;
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
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public Long[] getTempoFinalGif() {
		return tempoFinalGif;
	}

	public void setTempoFinalGif(Long[] tempoFinalGif) {
		this.tempoFinalGif = tempoFinalGif;
	}
	

	private void criarBaloes() {

		// Criar balões em posições aleatorias.
		Random num = new Random();
		int criados = 0;
		int x, y;
		boolean criar = true;

		while (criados < getQtdBaloes()) {
			x = num.nextInt(getWidth());
			y = num.nextInt(getHeigth());

			// Diminuir a probabilidade de que um balão fique em cima de outro:
			if (x > getAreaInicialBalao() && x < getWidth() - Balao.getLargura()) {
				if (criados == 0) {
					getBaloes()[criados] = new Balao(new Point(x, y));
					criados++;
				} else {
					criar = true;
					for (int i = 0; i < criados && criar; i++) {
						if (x >= getBaloes()[i].getPosicao().x - Balao.getLargura() && x <= getBaloes()[i].getPosicao().x + Balao.getLargura()) {
							if (y >= getBaloes()[i].getPosicao().y - Balao.getAltura() && y <= getBaloes()[i].getPosicao().y + Balao.getAltura()) {
								criar = false;
							}
						}
					}
					if (criar) {
						getBaloes()[criados] = new Balao(new Point(x, y));
						criados++;
						criar = false;
					}
				}
			}
		}
	}
	
}
