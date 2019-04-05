package udc.psw2.arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;

public class ArquivoBinario implements ArquivoFormasGeometrica{
	
	private static RandomAccessFile raf;
	private File file;
	
	public ArquivoBinario(File file) {
		this.file=file;
	}
	@Override
	public ListaEncadeada<FiguraGeometrica> lerFormas() {
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();
		
		try {
			raf = new RandomAccessFile(file,"r");
		}catch(FileNotFoundException e) {
			System.out.println("Erro ao escrever no arquivo");
		}

		while(true){
			try {
				
				raf.readByte();
				float x =(float)raf.readInt();
				float y =(float)raf.readInt();
				Ponto ponto = new Ponto(x,y);
				ponto.setEstado(FiguraGeometrica.VERBOSE);
				lista.inserir(ponto,0);
				
			}catch(EOFException e) {
				break;
			}catch(IOException e) {
				break;
			}
		}
		return lista;
	}

	@Override
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista) {
		try {
			raf = new RandomAccessFile(file,"rw");
		}catch (FileNotFoundException e) {
			System.out.println("Arquivo Inexistente");
			System.exit(0);
		}
		try {
			Iterador<FiguraGeometrica> it =lista.getInicio();
			FiguraGeometrica f;
			while((f = (FiguraGeometrica)it.proximo()) != null) {
				raf.writeByte(1);
				raf.writeInt((int)f.getX());
				raf.writeInt((int)f.getY());
			}
			raf.close();
		}catch(IOException e){
			System.out.println("Erro escrever no arquivo");
		}
		
	}
}
