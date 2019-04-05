package udc.psw2.arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.aplicacao.App;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;

public class ArquivoSerializado implements ArquivoFormasGeometrica{
	private File file;
	
	public ArquivoSerializado(File file) {
		this.file=file;
	}
	public void salvarSerial() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			Iterador<FiguraGeometrica> it =App.getApp().getIndice();
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
	public void lerSerial() {

		while (!App.getApp().Lista_vazia()) {
			App.getApp().EscruirInicio();
		}
		FiguraGeometrica formaAux1 = null;
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			while (true) {
				formaAux1 = (FiguraGeometrica) ois.readObject();

				App.getApp().inserirFormaGeometrica(formaAux1);
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
	}
	@Override
	public ListaEncadeada<FiguraGeometrica> lerFormas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista) {
		// TODO Auto-generated method stub
		
	}
}
