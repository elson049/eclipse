package udc.psw2.arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.aplicacao.App;
import udc.psw2.lista.Iterador;

public class ArquivoBinario {
	
	private static RandomAccessFile raf;
	private File file;
	
	public ArquivoBinario(File file) {
		this.file=file;
	}
	
	public void SalvarBinario() {
		try {
			raf = new RandomAccessFile(file,"rw");
		}catch (FileNotFoundException e) {
			System.out.println("Arquivo Inexistente");
			System.exit(0);
		}
		try {
			Iterador<FiguraGeometrica> it =App.getApp().getIndice();
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
	public void LerBinario() {

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
				App.getApp().inserirFormaGeometrica(ponto);
				
			}catch(EOFException e) {
				break;
			}catch(IOException e) {
				break;
			}
		}
	}
}
