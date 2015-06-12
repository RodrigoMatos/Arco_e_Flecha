package config;

public class Configuracao {

	// Tempo das threads
	// Tempo de espera entre as execu��es das threads
	public static Long tempoThreadMoverBalao = 150L;
	public static Long tempoThreadMoverFlecha = 150L;
	public static Long tempoThreadVerificarBalao = 500L;
	public static Long tempoThreadGeral = 1000L;
	public static Long tempoThreadDesenho = 150L; 
	
	// Dados da flecha
	public static int velocidadeMaxFlecha = 300;// velocidade maxima da flecha.
	public static int velocidadeMinimaFlecha = 100;// velocidade minima da flecha.
	public static int qtdFlehas = 10;// quantidade de flechas que o arqueiro possui 
	
	// Dados do bal�o
	public static int qtdBalao = 10;// quantidade de bal�es que ser� criado.
	public static int pontosPorBalao = 5;// valor por acerto de cada bal�o 
	
	// Dados do cenario
	public static int larguraCenario = 400;// largura do cenario
	public static int alturaCenario = 300;// altura do cenario
	public static int limiteXBalao = 200;// area X que o bal�o n�o poder� ultrapassar
	
	public static int posXPontuacao = 0;// posi��o X da pontua��o
	public static int posYPontuacao = alturaCenario;// posi��o Y da pontua��o
	
	public static int limitXArqueiro = 0;// area X que o arqueiro n�o ir� ultrapassar (seguindo o mouse)
	
}
