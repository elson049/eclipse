package udc.psw2.FigurasGeometricas;

import java.awt.Graphics;
import java.io.Serializable;

import udc.psw2.manipulador.ManipuladorFormaGeometrica;


public abstract  class FiguraGeometrica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int NORMAL=0;
	public static final int VERBOSE=1;
	
	private int estado =NORMAL;
	public abstract float area();
	public abstract float comprimento();
	public abstract float largura();
	public abstract float perimetro();
	public abstract float getX();
	public abstract float getY();
	public abstract void paint(Graphics g);
	protected abstract String normalString();
	protected abstract String verboselString();
	
	public abstract ManipuladorFormaGeometrica getManipulador();
	public abstract FiguraGeometrica clone();
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		if (estado != NORMAL && estado != VERBOSE)
			this.estado=NORMAL;
		this.estado = estado;
	}
	public String toString() {
		if(estado == NORMAL)
			return normalString();
		return verboselString();
	}
	
}