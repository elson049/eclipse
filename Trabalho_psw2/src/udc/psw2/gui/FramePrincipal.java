package udc.psw2.gui;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import udc.psw2.FigurasGeometricas.FiguraGeometrica;
import udc.psw2.FigurasGeometricas.Linha;
import udc.psw2.FigurasGeometricas.Ponto;
import udc.psw2.FigurasGeometricas.Retangulo;
import udc.psw2.aplicacao.App;
import udc.psw2.lista.Iterador;
import udc.psw2.lista.OuvinteLista;



public class FramePrincipal extends JFrame implements OuvinteLista{

	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	private Painel_desenhos painel;
	private JLabel status;
	private boolean aux=false;


	public FramePrincipal() {
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(3,3));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);

		textArea = new JTextArea(8,22);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		App.getApp().inserirOuvinte(this);
		//textArea.setEditable(false);

		status = new JLabel("Area de mensagens");
		contentPane.add(status,BorderLayout.SOUTH);

		painel = new Painel_desenhos(status);
		contentPane.add(painel,BorderLayout.CENTER);
		App.getApp().inserirOuvinte(painel);

		//painel1 = new PainelDesenho(app);
		//contentPane.add(painel1,BorderLayout.CENTER);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu barraArquivo = new JMenu("Arquivo");
		menubar.add(barraArquivo);

		JMenuItem mntmDesenhando = new JMenuItem("Painel de Desenho");
		mntmDesenhando.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painel.alterar(true,false);
				repaint();
			}
		});
		barraArquivo.add(mntmDesenhando);

		JMenuItem mntmManual = new JMenuItem("Desenhar figuras com mouse");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painel.alterar(false,false);
				repaint();
			}
		});
		barraArquivo.add(mntmManual);

		JMenuItem mntmInserir = new JMenuItem("Inserir valores das coordenadas");
		mntmInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aux=true;
				painel.alterar(false,aux);
			}
		});
		barraArquivo.add(mntmInserir);
		
		JMenuItem mntmLimpar = new JMenuItem("Limpar painel");
		mntmLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				App.getApp().LimparPainel();
				atualizarFigura();
				repaint();
			}
		});
		barraArquivo.add(mntmLimpar);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		barraArquivo.add(mntmSair);

		JMenu barraLer = new JMenu("Ler Arquivo");
		menubar.add(barraLer);

		JMenuItem mntmLerBinario = new JMenuItem("Ler (Binario)");
		mntmLerBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = escolherArquivo();
				if(f == null)
					return;
				App.getApp().LimparPainel();
				painel.LerBinario(f);
				atualizarFigura();
				painel.repaint();
			}

		});
		barraLer.add(mntmLerBinario);

		JMenuItem mntmLerSerial = new JMenuItem("Ler (serial)");
		mntmLerSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = escolherArquivo();
				if(f == null)
					return;
				App.getApp().LimparPainel();
				painel.lerSerial(f);
				atualizarFigura();
				painel.repaint();
			}
		});
		barraLer.add(mntmLerSerial);

		JMenuItem mntmLerTexto = new JMenuItem("Ler (texto)");
		mntmLerTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = escolherArquivo();
				if(f == null)
					return;
				App.getApp().LimparPainel();
				painel.lerTexto(f);
				atualizarFigura();
				painel.repaint();
			}
		});
		barraLer.add(mntmLerTexto);

		JMenu barraGravar = new JMenu("Gravar Arquivo");
		menubar.add(barraGravar);

		JMenuItem mntmSalvarBinario = new JMenuItem("Salvar (Binario)");
		mntmSalvarBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = escolherArquivo();
				if(f == null)
					return;
				painel.SalvarBinario(f);
			}
		});
		barraGravar.add(mntmSalvarBinario);

		JMenuItem mntmSalvarSerial = new JMenuItem("Salvar (serial)");
		mntmSalvarSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = escolherArquivo();
				if(f == null)
					return;
				painel.salvarSerial(f);
			}
		});
		barraGravar.add(mntmSalvarSerial);

		JMenuItem mntmSalvarTexto = new JMenuItem("Salvar (texto)");
		mntmSalvarTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = escolherArquivo();
				if(f == null)
					return;
				painel.salvarTexto(f);
			}
		});
		barraGravar.add(mntmSalvarTexto);

		JMenu barraFiguras = new JMenu("Figuras");
		menubar.add(barraFiguras);

		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(aux == true) {
					FiguraGeometrica p = lerPonto();
					p.setEstado(FiguraGeometrica.VERBOSE);
					App.getApp().inserirFormaGeometrica(p);
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
					App.getApp().inserirFormaGeometrica(linha);
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
					App.getApp().inserirFormaGeometrica(r);
				}
				Retangulo retangulo = new Retangulo();
				painel.setFormaGeometrica(retangulo);
			}
		});
		barraFiguras.add(mntmRetangulo);
	}
	public static Ponto lerPonto() {
		String strX = JOptionPane.showInputDialog("Digite o acoordenada x:");
		String strY = JOptionPane.showInputDialog("Digite o acoordenada Y:");

		float x= Float.parseFloat(strX);
		float y= Float.parseFloat(strY);

		return new Ponto(x,y);
	}
	public void atualizarFigura() {
		StringBuffer buffer = new StringBuffer();
		Iterador<FiguraGeometrica> i = App.getApp().getIndice();
		FiguraGeometrica f;
		while((f = (FiguraGeometrica)i.proximo()) != null) {
			buffer.append(f.toString()+"\n");
		}
		textArea.setText(buffer.toString());
	}
	public File escolherArquivo() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));

		FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Serial file", "ser");
		fc.setFileFilter(textFilter);

		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
