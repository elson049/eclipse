package udc.psw2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import udc.psw2.FigurasGeometricas.Circulo;
import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.FigurasGeometricas.Retangulo;
import udc.psw2.aplicacao.Documento;
import udc.psw2.lista.Iterador;



public class FramePrincipal extends JFrame implements PainelOuvinteForma{

	private static final long serialVersionUID = 1L;

	private Painel_desenhos painel;
	private Painel_texto texto;
	private JLabel status;
	private Documento doc;
	private JPanel contentPane;
	private boolean aux=false;

	//criação do frame
	public FramePrincipal(Documento doc) {
		this.doc=doc;

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);


		contentPane.setBackground(Color.WHITE); // configura cor de fundo
		status = new JLabel( "Area de mensagens!" );           
		contentPane.add( status, BorderLayout.SOUTH ); // adiciona r�tulo ao JFrame

		painel = new Painel_desenhos(status,doc);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);
		JScrollPane scrollDesenho = new JScrollPane();
		scrollDesenho.setViewportView(painel);
		contentPane.add(scrollDesenho, BorderLayout.CENTER);

		// Adiciona objeto Observador na lista de observadores do objeto Subject do padr�o Observer
		doc.adicionarOuvinte(painel);

		// Inicializa painel de texto
		JScrollPane scrollTexto = new JScrollPane();
		contentPane.add(scrollTexto, BorderLayout.WEST);
		texto = new Painel_texto(doc);
		scrollTexto.setViewportView(texto);
		texto.setEditable(false);

		// Adiciona objeto Observador na lista de observadores do objeto Subject do padr�o Observer
		doc.adicionarOuvinte(texto);	

		setBounds(50, 50, 850, 650); // Posi��o e tamanho da janela

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu barraArquivo = new JMenu("Arquivo");
		menubar.add(barraArquivo);

		JMenuItem mntmDesenhando = new JMenuItem("Desenho");
		mntmDesenhando.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painel.alterar(true,false);
				painel.setApagando(false);
			}
		});
		barraArquivo.add(mntmDesenhando);

		JMenuItem mntmManual = new JMenuItem("Desenhar com mouse");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painel.alterar(false,false);
				painel.setApagando(false);
			}
		});
		barraArquivo.add(mntmManual);

		JMenuItem mntmInserir = new JMenuItem("Inserir valores");
		mntmInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aux=true;
				painel.alterar(false,aux);
			}
		});
		barraArquivo.add(mntmInserir);

		JMenuItem mntmBorracha = new JMenuItem("Borracha");
		mntmBorracha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ponto ponto = new Ponto();
				painel.setApagando(true);
				painel.setFormaGeometrica(ponto);
			}
		});
		barraArquivo.add(mntmBorracha);

		JMenuItem mntmLimpar = new JMenuItem("Limpar");
		mntmLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doc.LimparPainel();
			}
		});
		barraArquivo.add(mntmLimpar);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = escolherArquivo(true);
				if(f == null)
					return;
				doc.salvarFormas(f);;
			}
		});
		barraArquivo.add(mntmSalvar);

		JMenuItem mntmLer = new JMenuItem("Ler");
		mntmLer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = escolherArquivo(false);
				if(f == null)
					return;
				doc.lerFormas(f);
			}

		});
		barraArquivo.add(mntmLer);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		barraArquivo.add(mntmSair);


		JMenu barraFiguras = new JMenu("Figuras");
		menubar.add(barraFiguras);

		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(aux == true) {
					FiguraGeometrica p = lerPonto();
					p.setEstado(FiguraGeometrica.VERBOSE);
					doc.inserirFormaGeometrica(p);
				}
				Ponto ponto = new Ponto();
				painel.setFormaGeometrica(ponto);
			}
		});
		barraFiguras.add(mntmPonto);

		JMenuItem mntmReta = new JMenuItem("Linha");
		mntmReta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aux ==true) {
					FiguraGeometrica linha = new Linha(lerPonto(),lerPonto());
					linha.setEstado(FiguraGeometrica.VERBOSE);
					doc.inserirFormaGeometrica(linha);
				}

				Linha linha2 = new Linha();
				painel.setFormaGeometrica(linha2);
			}
		});
		barraFiguras.add(mntmReta);

		JMenuItem mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aux ==true) {
					FiguraGeometrica r = new Retangulo(lerPonto(),lerPonto());
					r.setEstado(FiguraGeometrica.VERBOSE);
					doc.inserirFormaGeometrica(r);
				}
				Retangulo retangulo = new Retangulo();
				painel.setFormaGeometrica(retangulo);
			}
		});
		barraFiguras.add(mntmRetangulo);

		JMenuItem mntmcirculo = new JMenuItem("Circulo");
		mntmcirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(aux ==true) {
					FiguraGeometrica c = new Circulo(lerPonto(),lerPonto());
					c.setEstado(FiguraGeometrica.VERBOSE);
					doc.inserirFormaGeometrica(c);
				}
				Circulo circulo = new Circulo();
				painel.setFormaGeometrica(circulo);
			}
		});
		barraFiguras.add(mntmcirculo);

	}
	public static Ponto lerPonto() {
		String strX = JOptionPane.showInputDialog("Digite o acoordenada x:");
		String strY = JOptionPane.showInputDialog("Digite o acoordenada Y:");

		float x= Float.parseFloat(strX);
		float y= Float.parseFloat(strY);

		return new Ponto(x,y);
	}
	public File escolherArquivo(boolean gravar) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));

		FileNameExtensionFilter textFilterS = new FileNameExtensionFilter("Serial file", "dat");
		fc.addChoosableFileFilter(textFilterS);
		FileNameExtensionFilter textFilterT = new FileNameExtensionFilter("Text file", "txt");
		fc.addChoosableFileFilter(textFilterT);
		FileNameExtensionFilter textFilterB = new FileNameExtensionFilter("Binary file", "bin");
		fc.addChoosableFileFilter(textFilterB);

		fc.setFileFilter(textFilterT);

		int result = gravar ? fc.showSaveDialog(null) : fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
	@Override
	public void atualizar() {
		StringBuffer buffer = new StringBuffer();
		Iterador<FiguraGeometrica> i = doc.getIndice();
		FiguraGeometrica f;
		while((f = (FiguraGeometrica)i.proximo()) != null) {
			buffer.append(f.toString()+"\n");
		}
		texto.setText(buffer.toString());

	}
}
