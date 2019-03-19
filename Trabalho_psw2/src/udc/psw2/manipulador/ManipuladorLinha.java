package udc.psw2.manipulador;

import java.awt.Graphics;

import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.FigurasGeometricas.Ponto;

public class ManipuladorLinha implements ManipuladorFormaGeometrica {
	private Linha linha;
	
	public ManipuladorLinha(Linha l) {
		linha = l;
	}
	
	@Override
	public void click(int x, int y) {
	}

	@Override
	public void press(int x, int y) {
		Ponto p = new Ponto(x, y);
		linha.setA(p);
		p = new Ponto(x, y);
		linha.setB(p);
	}

	@Override
	public void release(int x, int y) {
		Ponto p = new Ponto(x, y);
		linha.setB(p);
	}

	@Override
	public void drag(int x, int y) {
		Ponto p = new Ponto(x, y);
		linha.setB(p);
	}

	@Override
	public void paint(Graphics g) {
		g.drawLine((int) linha.getA().getX(), (int) linha.getA().getY(), 
				(int) linha.getB().getX(), (int) linha.getB().getY());
	}

}
