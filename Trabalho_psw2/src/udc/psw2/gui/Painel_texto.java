package udc.psw2.gui;

import java.awt.Color;
import javax.swing.JTextArea;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.aplicacao.Documento;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.OuvinteLista;

public class Painel_texto extends JTextArea implements PainelOuvinteForma{

	private static final long serialVersionUID = 1L;
	private Documento doc;

	public Painel_texto(Documento doc){
		super(8,20);
		this.doc=doc;
		setBackground(new Color(255,255,255));
		setEditable(false);
	}
	public void atualizar() {
		StringBuffer buffer = new StringBuffer();
		Iterador<FiguraGeometrica> i = doc.getIndice();
		FiguraGeometrica f;
		while((f = (FiguraGeometrica)i.proximo()) != null) {
			buffer.append(f.toString()+"\n");
		}
		setText(buffer.toString());
	}

}
