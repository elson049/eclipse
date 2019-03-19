package udc.psw2.manipulador;
import java.awt.Graphics;

import udc.psw2.FigurasGeometricas.Ponto;



public class ManipuladorPonto implements ManipuladorFormaGeometrica {
	private Ponto ponto;
	
	public ManipuladorPonto(Ponto p) {
		ponto = p;
	}
	
	@Override
	public void click(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
	}

	@Override
	public void press(int x, int y) {
	}

	@Override
	public void release(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
	}

	@Override
	public void drag(int x, int y) {
	}

	@Override
	public void paint(Graphics g) {
		g.fillOval((int)ponto.x,(int)ponto.y,4, 4);
	}

}