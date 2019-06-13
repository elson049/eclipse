package udc.psw2.aplicacao;

import udc.psw2.DAO.CirculoDAO;
import udc.psw2.DAO.DesenhosDAO;
import udc.psw2.DAO.LinhaDAO;
import udc.psw2.DAO.PontoDAO;
import udc.psw2.DAO.RetanguloDAO;
import udc.psw2.DAO.entidade.Desenhos;
import udc.psw2.FigurasGeometricas.Circulo;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.FigurasGeometricas.Retangulo;
import udc.psw2.arquivo.ArquivoBinario;
import udc.psw2.arquivo.ArquivoFormasGeometrica;
import udc.psw2.arquivo.ArquivoSerializado;
import udc.psw2.arquivo.ArquivoTexto;
import udc.psw2.gui.PainelOuvinteForma;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;
import java.io.File;
import java.sql.SQLException;

public class Documento {
	private ListaEncadeada<FiguraGeometrica> listaFiguras;
	//lista de Ouvintes
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
	//adicionar ouvinte
	public void adicionarOuvinte(PainelOuvinteForma painel) {
		OuvintePainel.inserirInicio(painel);
		atualizarPaineis();
	}
	//exluir um ouvinte
	public void excluirOuvinte(PainelOuvinteForma painel){
		OuvintePainel.remover(painel);
		atualizarPaineis();
	}
	// Metodo notifica outros paineis
	public void atualizarPaineis() {
		Iterador<PainelOuvinteForma> i =OuvintePainel.getInicio();
		PainelOuvinteForma painel;
		while((painel = (PainelOuvinteForma)i.proximo()) != null) {
			painel.atualizar();
		}
	}
	//inserir figura geometrica 
	public void inserirFormaGeometrica(FiguraGeometrica forma) {
		listaFiguras.inserir(forma,0);
		atualizarPaineis();
	}
	public void excluirFormaGeometrica(FiguraGeometrica forma) {
		listaFiguras.remover(forma);
		atualizarPaineis();
	}
	//retorna interador lista
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
		listaFiguras.removerTudo();
		atualizarPaineis();
	}
	public FiguraGeometrica buscarFigura(Ponto ponto) {
		Iterador<FiguraGeometrica> i =listaFiguras.getInicio();
		FiguraGeometrica figura;
		while((figura = (FiguraGeometrica)i.proximo()) != null) {
			int j = figura.toString().indexOf(' ');
			String nome = figura.toString().substring(0, j);
			if (nome.equals("Ponto")) {
				Ponto p = (Ponto)figura;
				if(p.getX() == ponto.getX() && p.getY() == ponto.getY())
					return figura;
			}else if(nome.equals("Linha")) {
				Linha l = (Linha)figura;
				if(l.getA().getX() == ponto.getX() && l.getA().getY() == ponto.getY())
					return figura;
			}
		}
		return null;
	}
	public void salvarFormas(File file) {
		ArquivoFormasGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);

		// Determina qual algoritmo ser utilizado
		if (ext.compareTo("dat") == 0)
			arq = new ArquivoSerializado(file);
		if (ext.compareTo("txt") == 0)
			arq = new ArquivoTexto(file);
		if (ext.compareTo("bin") == 0)
			arq = new ArquivoBinario(file);
		// Uso do metodo AlgorithmInterface()
		arq.salvarFormas(listaFiguras);

	}

	public void lerFormas(File file) {
		ArquivoFormasGeometrica arq = null;

		String name = file.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);

		// Determina qual algoritmo ser utilizado
		if (ext.compareTo("dat") == 0)
			arq = new ArquivoSerializado(file);
		if (ext.compareTo("txt") == 0)
			arq = new ArquivoTexto(file);
		if (ext.compareTo("bin") == 0)
			arq = new ArquivoBinario(file);
		// Uso do metodo AlgorithmInterface()
		listaFiguras = arq.lerFormas();

		// Uso do metodo Notify() do padrï¿½o Observer
		atualizarPaineis();
	}
	public void salvarBD(String nomeDesenho) {
		DesenhosDAO desenhosDAO = new DesenhosDAO();
		Desenhos desenho = new Desenhos(0,nomeDesenho);

		try {
			desenhosDAO.insert(desenho);
		} catch (IllegalStateException | SQLException e) {
			e.printStackTrace();
		}

		PontoDAO pontoDAO = new PontoDAO((int) desenho.getId());
		LinhaDAO linhaDAO = new LinhaDAO((int) desenho.getId());
		RetanguloDAO retanguloDAO = new RetanguloDAO((int) desenho.getId());
		CirculoDAO circuloDAO = new CirculoDAO((int) desenho.getId());

		Iterador<FiguraGeometrica> i = listaFiguras.getInicio();
		FiguraGeometrica forma;
		
		try {
			while ((forma = (FiguraGeometrica) i.proximo()) != null) {
				if (forma.getClass().equals(Ponto.class)) {
						pontoDAO.insert((Ponto) forma);
				}
				if (forma.getClass().equals(Linha.class)) {
						linhaDAO.insert((Linha) forma);
				}
				if (forma.getClass().equals(Retangulo.class)) {
						retanguloDAO.insert((Retangulo) forma);
				}
				if (forma.getClass().equals(Circulo.class)) {
						circuloDAO.insert((Circulo) forma);
				}
			}
		} catch (IllegalStateException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void lerBD(String nomeDesenho) {
		DesenhosDAO desenhosDAO = new DesenhosDAO();
		Desenhos desenho = null;

		listaFiguras.removerTudo();

		desenho = desenhosDAO.find(nomeDesenho);
		if (desenho == null)
			return;

		PontoDAO pontoDAO = new PontoDAO((int) desenho.getId());
		LinhaDAO linhaDAO = new LinhaDAO((int) desenho.getId());
		RetanguloDAO retanguloDAO = new RetanguloDAO((int) desenho.getId());
		CirculoDAO circuloDAO = new CirculoDAO((int) desenho.getId());
		
		listaFiguras.inserirFim(pontoDAO.getLista());
		listaFiguras.inserirFim(linhaDAO.getLista());
		listaFiguras.inserirFim(retanguloDAO.getLista());
		listaFiguras.inserirFim(circuloDAO.getLista());
		
		// Uso do metodo Notify() do padrão Observer
		atualizarPaineis();
	}
	public int getQtdFiguras() {
		return listaFiguras.getTamanho();
	}
}
