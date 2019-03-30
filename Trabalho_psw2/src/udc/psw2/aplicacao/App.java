package udc.psw2.aplicacao;

import java.awt.Point;

import javax.swing.JFrame;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.gui.FramePrincipal;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;
import udc.psw2.lista.OuvinteLista;


public class App {
	
	private ListaEncadeada<FiguraGeometrica> listaFiguras;
	private ListaEncadeada<OuvinteLista> listaOuvinte;
	private static App app;

	private App() {
		listaFiguras= new ListaEncadeada<FiguraGeometrica>();
		listaOuvinte=new ListaEncadeada<OuvinteLista>();

	}
	public static App getApp() {
		if(app == null)
			app= new App();
		return app;
	}
	public void inserirOuvinte(OuvinteLista ouvinte) {
		listaOuvinte.inserir(ouvinte,0);
	}
	public void inserirFormaGeometrica(FiguraGeometrica forma) {
		listaFiguras.inserir(forma,0);
		Iterador<OuvinteLista> i =listaOuvinte.getInicio();
		OuvinteLista ouvinte;
		while((ouvinte = (OuvinteLista)i.proximo()) != null) {
			ouvinte.atualizarFigura();
		}
	}
	public Iterador<FiguraGeometrica> getIndice() {
		return listaFiguras.getInicio();
	}
	public boolean Lista_vazia () {
		return listaFiguras.isEmpty();
	}
	public void EscruirInicio() {
		listaFiguras.excluirInicio();
		return;
	}
	public void LimparPainel() {
		listaFiguras.limpar();
		listaOuvinte.limpar();
	}
	public static void main(String[] args) {
		
		FramePrincipal frame = new FramePrincipal();
		frame.setTitle("Painel de Desenho");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(new Point(300, 300));
		frame.setSize(800, 600); // configura o tamanho do frame
		frame.setVisible(true);
	}

}