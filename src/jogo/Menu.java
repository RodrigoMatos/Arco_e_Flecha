package jogo;

import java.applet.*;
import java.awt.*;


public class Menu extends Applet implements Runnable {

	private static final long serialVersionUID = -9075169431413415047L;

	private volatile Thread mainThread = null;
	
	// dados cenario
	int width = 400;
	int heigth = 300;

	public void init() {
		resize(width, heigth);
	}

	@Override
	public void start() {

		if (mainThread == null) {
			mainThread = new Thread(this, "Main");
			mainThread.start();
		}
	}

	@Override
	public void run() {

		Thread myThread = Thread.currentThread();
		while (mainThread == myThread) {
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		mainThread = null;
	}

	
	//Metodo que desenha na tela
	@Override
	public void paint(Graphics g) {
		
	}


	/****************** Métodos responsáveis pelos eventos da tela **********************/
	
	public boolean mouseDown(Event evt, int x, int y) {
	
		return true;
	}

	public boolean mouseMove(Event evt, int x, int y) {
		
		return true;
	}

}
