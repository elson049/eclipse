package udc.psw2.manipulador;
import java.awt.Graphics;

import udc.psw2.FigurasGeometricas.Ponto;



public class ManipuladorPonto implements ManipuladorFormaGeometrica {
	private Ponto ponto;
	
	public ManipuladorPonto(Ponto p) {
		ponto = p;
	}
	
	@Override
	public boolean click(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
		return false;
	}

	@Override
	public boolean press(int x, int y) {
		return false;
	}

	@Override
	public boolean release(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
		return false;
	}

	@Override
	public boolean drag(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.fillOval((int)ponto.x,(int)ponto.y,4, 4);
	}

}