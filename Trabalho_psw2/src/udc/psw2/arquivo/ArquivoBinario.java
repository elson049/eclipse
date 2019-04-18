package udc.psw2.arquivo;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import udc.psw2.FigurasGeometricas.Circulo;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.FigurasGeometricas.Retangulo;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.ListaEncadeada;

public class ArquivoBinario implements ArquivoFormasGeometrica{

	private static RandomAccessFile raf;
	private File file;

	public ArquivoBinario(File file) {
		this.file=file;
	}
	@Override
	public ListaEncadeada<FiguraGeometrica> lerFormas() {//ler arquivo binario
		ListaEncadeada<FiguraGeometrica> lista = new ListaEncadeada<FiguraGeometrica>();

		try {
			raf = new RandomAccessFile(file,"r");
		}catch(FileNotFoundException e) {
			System.out.println("Erro ao escrever no arquivo");
		}

		while(true){
			try {

				//raf.readByte();
				int op = raf.readInt();
				if(op == 1) {// ler Ponto
					Ponto a = new Ponto((float)raf.readInt(),(float)raf.readInt());
					a.setEstado(FiguraGeometrica.VERBOSE);
					lista.inserir(a,0);
				}else if(op == 2) { //ler Linha
					Ponto a = new Ponto((float)raf.readInt(),(float)raf.readInt());
					Ponto b = new Ponto((float)raf.readInt(),(float)raf.readInt());
					Linha linha = new Linha(a,b);
					linha.setEstado(FiguraGeometrica.VERBOSE);
					lista.inserir(linha,0);
				}else if(op == 3) {//ler Retangulo
					Ponto a = new Ponto((float)raf.readInt(),(float)raf.readInt());
					raf.readInt();raf.readInt();raf.readInt();raf.readInt();
					Ponto b = new Ponto((float)raf.readInt(),(float)raf.readInt());
					Retangulo retangulo = new Retangulo(a,b);
					retangulo.setEstado(FiguraGeometrica.VERBOSE);
					lista.inserir(retangulo,0);
				}else if(op == 4){//ler Circulo
					Ponto a = new Ponto((float)raf.readInt(),(float)raf.readInt());
					Ponto b = new Ponto((float)raf.readInt(),(float)raf.readInt());
					Circulo circulo = new Circulo(a,b);
					circulo.setEstado(FiguraGeometrica.VERBOSE);
					lista.inserir(circulo,0);
				}

			}catch(EOFException e) {
				break;
			}catch(IOException e) {
				break;
			}
		}
		return lista;
	}

	@Override
	public void salvarFormas(ListaEncadeada<FiguraGeometrica> lista) {//ler arquivo binario
		try {
			raf = new RandomAccessFile(file,"rw");
		}catch (FileNotFoundException e) {
			System.out.println("Arquivo Inexistente");
			System.exit(0);
		}
		try {
			Iterador<FiguraGeometrica> it =lista.getInicio();
			FiguraGeometrica forma;
			while((forma = (FiguraGeometrica)it.proximo()) != null) {
				int i = forma.toString().indexOf(' ');
				String nome = forma.toString().substring(0, i);
				if(nome.equals("Ponto")) {
					Ponto ponto = (Ponto)forma;
					//raf.writeByte(1);
					raf.writeInt(1);
					raf.writeInt((int)ponto.getX());
					raf.writeInt((int)ponto.getY());
				}else if (nome.equals("Linha")) {
					Linha linha = (Linha)forma;
					//raf.writeByte(1);
					raf.writeInt(2);
					raf.writeInt((int)linha.getA().getX());
					raf.writeInt((int)linha.getA().getY());
					raf.writeInt((int)linha.getB().getX());
					raf.writeInt((int)linha.getB().getY());
				}else if (nome.equals("Retangulo")) {
					Retangulo retangulo = (Retangulo)forma;
					//raf.writeByte(1);
					raf.writeInt(3);
					raf.writeInt((int)retangulo.getA().getX());
					raf.writeInt((int)retangulo.getA().getY());
					raf.writeInt((int)retangulo.getB().getX());
					raf.writeInt((int)retangulo.getA().getY());
					raf.writeInt((int)retangulo.getA().getX());
					raf.writeInt((int)retangulo.getB().getY());
					raf.writeInt((int)retangulo.getB().getX());
					raf.writeInt((int)retangulo.getB().getY());

				}else if (nome.equals("Circulo")) {
					Circulo circulo = (Circulo)forma;
					//raf.writeByte(1);
					raf.writeInt(4);
					raf.writeInt((int)circulo.getA().getX());
					raf.writeInt((int)circulo.getA().getY());
					raf.writeInt((int)circulo.getB().getX());
					raf.writeInt((int)circulo.getB().getY());
				}
			}
			raf.close();
		}catch(IOException e){
			System.out.println("Erro escrever no arquivo");
		}

	}
}
