package udc.psw2.manipulador;

import java.awt.Graphics;

public interface ManipuladorFormaGeometrica {
	public boolean click(int x, int y);
	public boolean press(int x, int y);
	public boolean release(int x, int y);
	public boolean drag(int x, int y);
	
	public void paint(Graphics g);
}
