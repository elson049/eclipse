package udc.psw2.FigurasGeometricas;

import java.awt.Graphics;

import udc.psw2.manipulador.ManipuladorCirculo;
import udc.psw2.manipulador.ManipuladorFormaGeometrica;


public class Circulo extends FiguraGeometrica{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	
	public Circulo() {
		a = new Ponto();
		b = new Ponto();
	}
	public Circulo(Ponto a,Ponto b) {
		this.a=a.clone();
		this.b=b.clone();
	}
	public Ponto getA() {
		return a;
	}

	public void setA(Ponto a) {
		this.a = a;
	}

	public Ponto getB() {
		return b;
	}

	public void setB(Ponto b) {
		this.b = b;
	}
	@Override
	public float area() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float comprimento() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float largura() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Circulo clone() {
		return new Circulo(a,b);
	}
	protected  String normalString() {
		return String.format("%s %s",a,b);
	}
	protected  String verboselString() {
		return String.format("Circulo %s %s",a,b);
	}
	public Ponto centro() {
		return new Ponto((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
	}
	public void paint(Graphics g) {
		g.drawOval((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
		
	}
	public ManipuladorFormaGeometrica getManipulador() {
		return new ManipuladorCirculo(this);
	}
}

