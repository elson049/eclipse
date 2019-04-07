package udc.psw2.FigurasGeometricas;
import java.awt.Graphics;

import udc.psw2.manipulador.ManipuladorFormaGeometrica;
import udc.psw2.manipulador.ManipuladorLinha;



public class Linha  extends FiguraGeometrica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	
	public Linha(){
		a = new Ponto();
		b = new Ponto();
	}

	public Linha(Ponto a, Ponto b){
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Linha(float ax, float ay, float bx, float by){
		a = new Ponto(ax, ay);
		b = new Ponto(bx, by);
	}
	
	
	@Override
	public Linha clone() {
		return new Linha(a, b);
	}

	public float comprimento(){
		return a.distancia(b);
	}
	
	public Ponto centro(){
		Ponto m = new Ponto((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		return m;
	}
	
	public double base(){
		if(a.getX() < b.getX())
			return b.getX() - a.getX();
		return a.getX() - b.getX();
	}
	
	public double altura(){
		if(a.getY() < b.getY())
			return b.getY() - a.getY();
		return a.getY() - b.getY();
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
	@Override
	public float area() {
		return 0;
	}

	@Override
	public float perimetro() {
		return comprimento();
	}

	@Override
	public ManipuladorFormaGeometrica getManipulador() {
		return new ManipuladorLinha(this);
	}

	@Override
	public float largura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paint(Graphics g) {
		g.drawLine((int) a.getX(), (int) a.getY(), 
				(int)b.getX(), (int) b.getY());
	}

	protected  String normalString() {
		return String.format("[%s %s]",a,b);
	}
	protected  String verboselString() {
		return String.format("Linha %s %s",a,b);
	}


}
