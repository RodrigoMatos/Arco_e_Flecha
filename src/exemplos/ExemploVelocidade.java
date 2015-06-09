package exemplos;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ExemploVelocidade extends Applet {

	TextField distanciaText = new TextField(10);
	TextField aceleracaoText = new TextField(10);
	Button iniciarButton = new Button("INICIAR!");
	TextArea respostas = new TextArea("Sucesso !!", 8, 60, TextArea.SCROLLBARS_NONE);

	public void init() {

		addHorizontalLine(Color.red);
		addNewLine();

		add(distanciaText);
		add(new Label("Distancia da Viagem em Ano-luz"));
		addNewLine();
		add(aceleracaoText);
		add(new Label("Aceleração do foguete em g's"));
		addNewLine();
		add(iniciarButton);
		addNewLine();
		respostas.setEditable(false);
		add(respostas);
		addNewLine();
		addHorizontalLine(Color.red);
		iniciarButton.addActionListener(new IniciarListener());
	}

	class IniciarListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			double distance;
			double acceleration;

			distance = atod(distanciaText.getText());
			if (Double.isNaN(distance) || distance < 0) {
				respostas.setText("A distância não pode ser negativa.");
				distanciaText.requestFocus();
				distanciaText.selectAll();
				return;
			}

			acceleration = atod(aceleracaoText.getText());
			if (acceleration == Double.NaN || acceleration <= 0) {
				respostas.setText("A aceleração não pode ser negativa.");
				aceleracaoText.requestFocus();
				aceleracaoText.selectAll();
				return;
			}

			makeTrip(distance, acceleration);
		}
	}

	double asinh(double x) {
		return Math.log(x + Math.sqrt(x * x + 1));
	}

	double atod(String s) {
		double answer;
		Double d;
		try {
			d = new Double(s);
			answer = d.doubleValue();
		} catch (NumberFormatException e) {
			answer = Double.NaN;
		}
		return answer;
	}

	void makeTrip(double distance, double acceleration) {
		final double LIGHT_YEAR = 9.47e15;
		final double c = 3.00e8;
		final double g = 9.81;
		final double SECONDS_PER_YEAR = 60.0 * 60.0 * 24.0 * 365.25;
		double d;
		double a;
		double time_earth;
		double time_ship;
		double years_earth;
		double years_ship;
		d = LIGHT_YEAR * distance / 2;
		a = g * acceleration;
		time_earth = 2 * Math.sqrt((d * d) / (c * c) + 2 * d / a);
		time_ship = 2 * (c / a) * asinh(a * time_earth / c);
		years_earth = time_earth / SECONDS_PER_YEAR;
		years_ship = time_ship / SECONDS_PER_YEAR;

		respostas.setText("");
		respostas.append("Distância da Viagem: ");
		respostas.append((new Double(distance)).toString() + " Ano-Luz.\n");
		respostas.append("Aceleração: ");
		respostas.append((new Double(acceleration)).toString() + " g.\n");
		respostas.append("Tempo na Terra: ");
		respostas.append((new Double(years_earth)).toString() + " anos.\n");

	}

	private void addHorizontalLine(Color c) {
		Canvas line = new Canvas();
		line.setSize(10000, 1);
		line.setBackground(c);
		add(line);
	}

	private void addNewLine() {
		addHorizontalLine(getBackground());
	}

}
