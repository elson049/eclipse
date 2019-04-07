package udc.psw2.manipulador;


import java.awt.Graphics;

import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.FigurasGeometricas.Retangulo;


public class ManipuladorRetangulo implements ManipuladorFormaGeometrica {
	private Retangulo retangulo;
	
	public ManipuladorRetangulo(Retangulo r) {
		retangulo = r;
	}
	
	@Override
	public boolean click(int x, int y) {
		return false;
	}

	@Override
	public boolean press(int x, int y) {
		Ponto p = new Ponto(x, y);
		retangulo.setA(p);
		p = new Ponto(x, y);
		retangulo.setB(p);
		return false;
	}

	@Override
	public boolean release(int x, int y) {
		Ponto p = new Ponto(x, y);
		retangulo.setB(p);
		return false;
	}

	@Override
	public boolean drag(int x, int y) {
		Ponto p = new Ponto(x, y);
		retangulo.setB(p);
		return false;
	}

	@Override
	public void paint(Graphics g) {
		int xa = (int) retangulo.getA().getX();
		int xb = (int) retangulo.getB().getX();
		int ya = (int) retangulo.getA().getY();
		int yb = (int) retangulo.getB().getY();
		g.drawRect(xa < xb ? xa : xb, ya < yb ? ya : yb, 
				(int) retangulo.base(), (int) retangulo.altura() );
	}

}

