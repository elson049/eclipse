package udc.psw2.FigurasGeometricas;

import java.awt.Graphics;

import udc.psw2.manipulador.ManipuladorFormaGeometrica;
import udc.psw2.manipulador.ManipuladorPonto;

public class Ponto extends FiguraGeometrica{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float x;
	public float y;
	
	public Ponto(){
		x = 0;
		y = 0;
	}
	
	public Ponto(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Ponto(Ponto p){
		x = p.x;
		y = p.y;
	}
	
	public float getX(){
		return x;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public Ponto clone(){
		return new Ponto(x,y);
	}
	
	public float distancia(Ponto p) {
		return  (float)Math.sqrt( (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y) );
	}
	
	public float area(){
		return 0;
	}
	
	public float comprimento(){
		
		return 0;
	}
	
	public float largura(){
		return 0;
	}
	
	public float perimetro(){
		return 0;
	}
	public void paint(Graphics g) {
		g.fillOval((int)x,(int)y,4, 4);
	}

	public ManipuladorFormaGeometrica getManipulador() {
		return new ManipuladorPonto(this);
	}

	protected String normalString() {
		return String.format("(%d %d",(int)x,(int)y);
	}
	protected String verboselString() {
		return String.format("Ponto %d %d",(int)x,(int)y);
	}

	
}
