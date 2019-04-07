package udc.psw2.manipulador;

import java.awt.Graphics;

import udc.psw2.FigurasGeometricas.Circulo;
import udc.psw2.FigurasGeometricas.Ponto;

public class ManipuladorCirculo implements ManipuladorFormaGeometrica{
	Circulo circulo;
	
	public ManipuladorCirculo(Circulo circulo) {
		this.circulo=circulo;
	}
	@Override
	public boolean click(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean press(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setA(p);
		p = new Ponto(x, y);
		circulo.setB(p);
		return false;
	}

	public boolean release(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setB(p);
		return false;
	}

	public boolean drag(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setB(p);
		return false;
	}

	@Override
	public void paint(Graphics g) {
		int raio = (int)circulo.comprimento()/2;
		g.drawOval((int)circulo.centro().getX()-raio,(int)circulo.centro().getY()-raio,2*raio,2*raio);
	}

}
