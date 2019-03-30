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
	public void click(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void press(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setA(p);
		p = new Ponto(x, y);
		circulo.setB(p);
	}

	public void release(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setB(p);
	}

	public void drag(int x, int y) {
		Ponto p = new Ponto(x, y);
		circulo.setB(p);
	}

	@Override
	public void paint(Graphics g) {
		g.drawOval((int)circulo.getA().getX(),(int)circulo.getA().getY(),(int)circulo.getB().getX(),(int)circulo.getB().getY());
		
	}

}
