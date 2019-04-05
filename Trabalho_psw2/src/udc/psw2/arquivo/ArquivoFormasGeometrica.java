package udc.psw2.arquivo;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.lista.ListaEncadeada;

public interface ArquivoFormasGeometrica {
	public ListaEncadeada<FiguraGeometrica> lerFormas();
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista);
}