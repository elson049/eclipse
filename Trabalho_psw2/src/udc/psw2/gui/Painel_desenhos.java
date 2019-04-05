package udc.psw2.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.aplicacao.Documento;
import udc.psw2.lista.Iterador;
import udc.psw2.manipulador.ManipuladorFormaGeometrica;


public class Painel_desenhos extends JPanel implements MouseMotionListener, MouseListener,PainelOuvinteForma  {
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
	private Documento doc;

	public Painel_desenhos(JLabel status,Documento doc) {
		this.status=status;
		this.doc=doc;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (forma != null)
			manipulador.paint(g);

		Iterador<FiguraGeometrica> i =doc.getIndice();
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
			doc.inserirFormaGeometrica(ponto);
			repaint();
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
			doc.inserirFormaGeometrica(forma);

			desenhando = false;

			forma = forma.clone();
			manipulador = forma.getManipulador();

			repaint();
		}
	}
	public void atualizar() {
		repaint();
		
	}
}

