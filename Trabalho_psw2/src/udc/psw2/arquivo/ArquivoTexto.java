package udc.psw2.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import udc.psw2.FigurasGeometricas.FabricarFormas;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.aplicacao.App;
import udc.psw2.lista.Iterador;

public  class ArquivoTexto {
	private File file;
	
	public ArquivoTexto(File file) {
		this.file=file;
	}
	public void salvarTexto() {
		FileWriter output;

		try {

			output = new FileWriter(file);

			Iterador<FiguraGeometrica> i =App.getApp().getIndice();
			FiguraGeometrica g;
			while((g = (FiguraGeometrica)i.proximo()) != null) {
				g.setEstado(FiguraGeometrica.VERBOSE);
				output.append(g.toString()+"\n");
			}

			output.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	public void lerTexto() {
		Scanner input=null;
		while (!App.getApp().Lista_vazia()) {
			App.getApp().EscruirInicio();
		}
		try {
			input= new Scanner(file);
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				FiguraGeometrica formaAux = FabricarFormas.fabricarFormaGeometrica(linha);
				App.getApp().inserirFormaGeometrica(formaAux);
			}
			input.close();

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
