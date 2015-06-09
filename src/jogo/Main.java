package jogo;

public class Main {

	public static void main(String[] args) {
		
//		new Menu();
		JogoArcoFlecha jogo = new JogoArcoFlecha();
		jogo.init();
		jogo.start();
		jogo.run();
		jogo.setVisible(true);
		jogo.show();

	}

}
