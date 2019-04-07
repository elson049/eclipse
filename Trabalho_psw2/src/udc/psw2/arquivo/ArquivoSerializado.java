package udc.psw2.arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;

public class ArquivoSerializado implements ArquivoFormasGeometrica{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	
	public ArquivoSerializado(File file) {
		this.file=file;
	}
	@Override
	public ListaEncadeada<FiguraGeometrica> lerFormas() {
		
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();
		
		while (!lista.isEmpty()) {
			lista.excluirInicio();
		}
		FiguraGeometrica formaAux1 = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				formaAux1 = (FiguraGeometrica) ois.readObject();
				
				lista.inserir(formaAux1,0);
			}
		} catch (EOFException endOfFileException) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			Iterador<FiguraGeometrica> it =lista.getInicio();
			FiguraGeometrica f;
			f = it.getObject();
			while (f!= null) {
				oos.writeObject(f);
				f = it.proximo();
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
