package udc.psw2.aplicacao;

import java.awt.Point;

import javax.swing.JFrame;

import udc.psw2.gui.FramePrincipal;

public class App {
	
	private static App app;
	private Documento doc;

	private App() {
		doc = new Documento();
		FramePrincipal frame = new FramePrincipal(doc);
		frame.setTitle("Painel de Desenho");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(new Point(300, 300));
		frame.setSize(800, 600); // configura o tamanho do frame
		frame.setVisible(true);
	}
	public Documento getDocumentAtivo() {
		return doc;
	}
	public static App getApp() {
		if(app == null)
			app= new App();
		return app;
	}
	public static void main(String[] args) {
		App.getApp();
	}
}