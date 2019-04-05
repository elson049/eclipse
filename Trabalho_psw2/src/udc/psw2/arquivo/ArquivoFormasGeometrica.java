package udc.psw2.arquivo;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.lista.ListaEncadeada;

public interface ArquivoFormasGeometrica {
	// Metodo AlgorithmInterface() da classe Strategy do padr�o Strategy
	public ListaEncadeada<FiguraGeometrica> lerFormas();
	// Metodo AlgorithmInterface() da classe Strategy do padr�o Strategy
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista);
}