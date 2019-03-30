package udc.psw2.FigurasGeometricas;

import java.awt.Graphics;

import udc.psw2.manipulador.ManipuladorFormaGeometrica;
import udc.psw2.manipulador.ManipuladorRetangulo;

public class Retangulo extends FiguraGeometrica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	
	public Retangulo(){
		a = new Ponto();
		b = new Ponto();
	}
	
	public Retangulo(Ponto a, Ponto b){
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Retangulo(float ax, float ay, float bx, float by){
		a = new Ponto(ax, ay);
		b = new Ponto(bx, by);
	}
	
	public Ponto getA() {
		return a.clone();
	}

	public Ponto getB() {
		return b.clone();
	}

	public void setA(Ponto a) {
		this.a = a.clone();
	}

	public void setB(Ponto b) {
		this.b = b.clone();
	}
	
	public Ponto centro(){
		return new Ponto((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
	}
	
	public double base(){
		return Math.abs(a.getX() - b.getX());
	}
	
	public double altura(){
		return Math.abs(a.getY() - b.getY());
	}
	 
	public float area(){
		return  0;
	}

	public float perimetro() {
		return 0;
	}

	public ManipuladorFormaGeometrica getManipulador() {
		return new ManipuladorRetangulo(this);
	}

	@Override
	public Retangulo clone() {
		return new Retangulo(a, b);
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
	public void paint(Graphics g) {
		int xa = (int) a.getX();
		int xb = (int) b.getX();
		int ya = (int) a.getY();
		int yb = (int) b.getY();
		g.drawRect(xa < xb ? xa : xb, ya < yb ? ya : yb, 
				(int) base(), (int) altura() );
	}
	protected String normalString() {
		return String.format("%d %d %d %d %d %d %d %d",(int)a.getX(),(int)a.getY(),(int)b.getX(),(int)a.getY(),(int)a.getX(),(int)b.getY(),(int)b.getX(),(int)b.getY());
	}
	protected String verboselString() {
		return String.format("Retangulo %d %d %d %d %d %d %d %d",(int)a.getX(),(int)a.getY(),(int)b.getX(),(int)a.getY(),(int)a.getX(),(int)b.getY(),(int)b.getX(),(int)b.getY());
	}

}