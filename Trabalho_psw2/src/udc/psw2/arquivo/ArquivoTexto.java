package udc.psw2.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import udc.psw2.FigurasGeometricas.FabricarFormas;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;

public  class ArquivoTexto implements ArquivoFormasGeometrica{
	private File file;
	
	public ArquivoTexto(File file) {
		this.file=file;
	}
	@Override
	public ListaEncadeada<FiguraGeometrica> lerFormas() {
		
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();
		
		Scanner input=null;
		while (!lista.isEmpty()) {
			lista.excluirInicio();
		}
		try {
			input= new Scanner(file);
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				FiguraGeometrica formaAux = FabricarFormas.fabricarFormaGeometrica(linha);
				lista.inserir(formaAux,0);
			}
			input.close();

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista) {
		FileWriter output;

		try {

			output = new FileWriter(file);

			Iterador<FiguraGeometrica> i =lista.getInicio();
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
}
