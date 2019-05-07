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
	private boolean apagando = false;
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
		desenhando=false;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (forma != null)
			manipulador.paint(g);

		Iterador<FiguraGeometrica> i =doc.getIndice();
		FiguraGeometrica f;
		while((f = (FiguraGeometrica)i.proximo()) != null) {
			f.getManipulador().paint(g);
		}
	}
	public void alterar(boolean aux,boolean aux2) {
		this.aux=aux;
		this.aux2=aux2;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		status.setText(String.format("Arrastou na  [%d, %d] Total de Figuras = %d",e.getX() , e.getY(),doc.getQtdFiguras() ));
		if(aux == true && aux2 == false && apagando == false) {
			FiguraGeometrica ponto = new Ponto((float)e.getX(),(float)e.getY());
			ponto.setEstado(FiguraGeometrica.VERBOSE);
			doc.inserirFormaGeometrica(ponto);
		}else if (desenhando) {
			manipulador.drag(e.getX(), e.getY());
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
	public void setApagando(boolean apagando) {
		this.apagando = apagando;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		status.setText(String.format("Atual [%d, %d] Total de Figuras = %d",e.getX(),e.getY(),doc.getQtdFiguras() ));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		status.setText(String.format("Clicado na [%d, %d] Total de Figuras = %d",e.getX(),e.getY(),doc.getQtdFiguras() ));
		if (manipulador != null) {
			if(apagando) {
				Ponto p = new Ponto(e.getX(),e.getY());
				FiguraGeometrica f = doc.buscarFigura(p);
				doc.excluirFormaGeometrica(f);
			}else {
				manipulador.click(e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		status.setText(String.format("Mouse no Painel [%d, %d] Total de Figuras = %d",e.getX(),e.getY(),doc.getQtdFiguras() ));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		status.setText("Mouse saiu no painel");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		status.setText(String.format("Precionou [%d, %d] Total de Figuras = %d",e.getX(),e.getY(),doc.getQtdFiguras() ));
		if (manipulador != null && apagando == false) {
			manipulador.press(e.getX(), e.getY());

			desenhando = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		status.setText(String.format("Released at [%d, %d] Total de Figuras = %d",e.getX(),e.getY(),doc.getQtdFiguras() ));
		if (desenhando && aux == false && aux2 == false && apagando == false) {
			manipulador.release(e.getX(), e.getY());
			forma.setEstado(FiguraGeometrica.VERBOSE);
			doc.inserirFormaGeometrica(forma);

			desenhando = false;

			forma = forma.clone();
			manipulador = forma.getManipulador();

		}
	}
	public void atualizar() {
		repaint();

	}
}

