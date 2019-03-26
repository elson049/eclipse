package udc.psw2.FigurasGeometricas;

public class FabricarFormas {

	public static FiguraGeometrica fabricarFormaGeometrica(String forma) {
		int i = forma.indexOf(' ');
		String nome = forma.substring(0, i);

		FiguraGeometrica formaGeometrica=null;

		if(nome.equals(Ponto.class.getSimpleName()))
			formaGeometrica = fabricarPonto(forma.substring(i+1));
		else if(nome.equals(Linha.class.getSimpleName()))
			formaGeometrica = fabricarLinha(forma.substring(i+1));
		else if (nome.equals(Retangulo.class.getSimpleName()))
			formaGeometrica = fabricarRetangulo(forma.substring(i+1));


		return formaGeometrica;
	}
	public static Linha fabricarLinha(String linha) {
		String str1=null;
		String str2=null;
		int cont=0;
		str1 = linha.substring(linha.indexOf(' '));
		str2 = str1.substring(str1.indexOf(' ')+1);
		cont = cont+str2.indexOf(' ')+str1.indexOf(' ')+linha.indexOf(' ');
		Ponto p1 = fabricarPonto(linha.substring(0, cont+1)); 
		Ponto p2 =fabricarPonto(linha.substring(cont+2));
		Linha l = new Linha(p1,p2);
		l.setEstado(FiguraGeometrica.VERBOSE);
		return l;
	}
	public static Ponto fabricarPonto(String ponto) {
		int cont = ponto.indexOf(' ');
		int x = Integer.parseInt(ponto.substring(0,cont));
		int y = Integer.parseInt(ponto.substring(cont+1));
		Ponto p = new Ponto((float)x,(float)y);
		p.setEstado(FiguraGeometrica.VERBOSE);
		return p;
	}
	public static Retangulo fabricarRetangulo(String retangulo) {
		/*
		 * String str1=null; String str2=null; String str3=null; int cont=0;
		 * System.out.println(linha); str1 = linha.substring(linha.indexOf(' ')+1); str2
		 * = str1.substring(str1.indexOf(' ')+1); str3 = str2.substring(str2.indexOf('
		 * ')+1); cont = cont+str3.indexOf(' ')+str1.indexOf(' ')+str2.indexOf('
		 * ')+linha.indexOf(' ')+3; Ponto p1 = fabricarPonto(linha.substring(0, 11));
		 * Ponto p2 = fabricarPonto(linha.substring(11+1)); Linha l =null;//new
		 * Linha(p1,p2); //l.setEstado(FiguraGeometrica.VERBOSE);
		 */			
		int i = retangulo.indexOf(')');
		Ponto p1 = fabricarPonto(retangulo.substring(0,i));
		Ponto p2 = fabricarPonto(retangulo.substring(i+1));

		Retangulo r = new Retangulo(p1,p2);

		System.out.println(r.getClass().getSimpleName()+" "+r.toString());
		return null;
	}

}