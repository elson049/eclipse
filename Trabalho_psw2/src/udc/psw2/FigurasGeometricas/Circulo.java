package udc.psw2.FigurasGeometricas;

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
	public Circulo(float ax, float ay, float bx, float by){
		a = new Ponto(ax, ay);
		b = new Ponto(bx, by);
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
	public float raio() {
		
		return 0;
	}
	@Override
	public float area() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float comprimento() {
		return a.distancia(b);
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

	public ManipuladorFormaGeometrica getManipulador() {
		return new ManipuladorCirculo(this);
	}
}

