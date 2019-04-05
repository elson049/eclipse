package udc.psw2.aplicacao;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.arquivo.ArquivoBinario;
import udc.psw2.arquivo.ArquivoFormasGeometrica;
import udc.psw2.arquivo.ArquivoSerializado;
import udc.psw2.arquivo.ArquivoTexto;
import udc.psw2.gui.PainelOuvinteForma;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;
import java.io.File;

public class Documento {
	private ListaEncadeada<FiguraGeometrica> listaFiguras;
	private ListaEncadeada<PainelOuvinteForma> OuvintePainel;
	
	private static Documento doc;
	
	public  Documento() {
		super();
		listaFiguras= new ListaEncadeada<FiguraGeometrica>();
		OuvintePainel = new ListaEncadeada<PainelOuvinteForma>();
	}
	public static Documento getDocumento() {
		if(doc == null)
			doc= new Documento();
		return doc;
	}
	
	public void adicionarOuvinte(PainelOuvinteForma painel) {
		OuvintePainel.inserirInicio(painel);
		atualizarPaineis();
	}
	public void excluirOuvinte(PainelOuvinteForma painel){
		OuvintePainel.remover(painel);
		atualizarPaineis();
	}
	
	public void atualizarPaineis() {
		Iterador<PainelOuvinteForma> i =OuvintePainel.getInicio();
		PainelOuvinteForma painel;
		while((painel = (PainelOuvinteForma)i.proximo()) != null) {
			painel.atualizar();
		}
	}
	public void inserirFormaGeometrica(FiguraGeometrica forma) {
		listaFiguras.inserir(forma,0);
		atualizarPaineis();
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
		atualizarPaineis();
	}
	public void salvarFormas(File file) {
		ArquivoFormasGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);

		// Determina qual algoritmo ser� utilizado, no padr�o Strategy
		if (ext.compareTo("ser") == 0)
			arq = new ArquivoSerializado(file);
		if (ext.compareTo("txt") == 0)
			arq = new ArquivoTexto(file);
		if (ext.compareTo("bin") == 0)
			arq = new ArquivoBinario(file);
		// Uso do metodo AlgorithmInterface() da classe Strategy
		arq.salvarFormas(listaFiguras);

	}

	// Metodo ContextInterface da classe Context no padr�o Strategy
	public void lerFormas(File file) {
		ArquivoFormasGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);

		// Determina qual algoritmo ser� utilizado, no padr�o Strategy
		if (ext.compareTo("ser") == 0)
			arq = new ArquivoSerializado(file);
		if (ext.compareTo("txt") == 0)
			arq = new ArquivoTexto(file);
		if (ext.compareTo("bin") == 0)
			arq = new ArquivoBinario(file);
		// Uso do metodo AlgorithmInterface() da classe Strategy
		listaFiguras = arq.lerFormas();

		// Uso do metodo Notify() do padr�o Observer
		atualizarPaineis();
	}
}
