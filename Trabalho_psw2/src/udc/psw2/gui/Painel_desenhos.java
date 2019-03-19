package udc.psw2.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import udc.psw2.FigurasGeometricas.FabricarFormas;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.aplicacao.App;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.OuvinteLista;
import udc.psw2.manipulador.ManipuladorFormaGeometrica;


public class Painel_desenhos extends JPanel implements MouseMotionListener, MouseListener,OuvinteLista  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel status;
	private boolean desenhando = false;
	private boolean aux=false;
	private boolean aux2=false;
	private ManipuladorFormaGeometrica manipulador;
	private FiguraGeometrica forma;


	public Painel_desenhos(JLabel status) {
		this.status=status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (forma != null)
			manipulador.paint(g);

		Iterador<FiguraGeometrica> i =App.getApp().getIndice();
		FiguraGeometrica f;
		while((f = (FiguraGeometrica)i.proximo()) != null) {
			f.paint(g);
		}
	}
	public void alterar(boolean aux,boolean aux2) {
		this.aux=aux;
		this.aux2=aux2;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		status.setText(String.format("Arrastou na  [%d, %d]",e.getX() , e.getY() ));
		if(aux == true && aux2 == false) {
			FiguraGeometrica ponto = new Ponto((float)e.getX(),(float)e.getY());
			ponto.setEstado(FiguraGeometrica.VERBOSE);
			App.getApp().inserirFormaGeometrica(ponto);
			repaint(); // repinta JFrame
		}
	}
	public void setFormaGeometrica(FiguraGeometrica forma) {
		this.forma = forma;
		manipulador = forma.getManipulador();
	}

	public boolean isDesenhando() {
		return desenhando;
	}

	public void setDesenhando(boolean desenhando) {
		this.desenhando = desenhando;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		status.setText(String.format("Atual [%d, %d]",
				e.getX() , e.getY() ));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		status.setText(String.format("Clicado na [%d, %d]",
				e.getX() , e.getY() ));
		if (manipulador != null) {
			manipulador.click(e.getX(), e.getY());

			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		status.setText(String.format("Mouse no Painel [%d, %d]",
				e.getX() , e.getY() ));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		status.setText("Mouse saiu no painel");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		status.setText(String.format("Precionou [%d, %d]",
				e.getX() , e.getY() ));
		if (manipulador != null) {
			manipulador.press(e.getX(), e.getY());

			desenhando = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		status.setText(String.format("Released at [%d, %d]",
				e.getX() , e.getY() ));
		if (desenhando && aux == false && aux2 == false) {
			manipulador.release(e.getX(), e.getY());
			forma.setEstado(FiguraGeometrica.VERBOSE);
			App.getApp().inserirFormaGeometrica(forma);

			desenhando = false;

			forma = forma.clone();
			manipulador = forma.getManipulador();

			repaint();
		}
	}
	public void salvarTexto(File f) {
		FileWriter output;

		try {

			output = new FileWriter(f);

			Iterador<FiguraGeometrica> i =App.getApp().getIndice();
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
	public void lerTexto(File f) {
		Scanner input=null;
		while (!App.getApp().Lista_vazia()) {
			App.getApp().EscruirInicio();
		}
		try {
			input= new Scanner(f);
			while (input.hasNextLine()) {
				String linha = input.nextLine();
				FiguraGeometrica formaAux = FabricarFormas.fabricarFormaGeometrica(linha);
				App.getApp().inserirFormaGeometrica(formaAux);
			}
			input.close();

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void salvarSerial(File file) {
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
	public void lerSerial(File file) {
		// Criar um m�todo na lista para realizar esta opera��o

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // fim do arquivo foi alcan�ado
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SalvarBinario(File file) {
		String msg= new String(); 
		try {
			FileOutputStream fout = new FileOutputStream(file);
			
			Iterador<FiguraGeometrica> it =App.getApp().getIndice();
			FiguraGeometrica formaAux;
			formaAux = it.getObject();
			while (formaAux!= null) {
				formaAux.setEstado(FiguraGeometrica.VERBOSE);
				//byte[] bytes =formaAux.toString()+"\n";
				fout.write(msg.getBytes());
				formaAux = it.proximo();
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void atualizarFigura() {
		repaint();
	}
}
