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
			int i = linha.indexOf(')');
			Ponto p1 = fabricarPonto(linha.substring(0,i));
			Ponto p2 = fabricarPonto(linha.substring(i+1));
			
			Linha l =new Linha(p1,p2);
			System.out.println(l.getClass().getSimpleName()+" "+linha.toString());
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
			int i = retangulo.indexOf(')');
			Ponto p1 = fabricarPonto(retangulo.substring(0,i));
			Ponto p2 = fabricarPonto(retangulo.substring(i+1));
			
			Retangulo r = new Retangulo(p1,p2);
			
			System.out.println(r.getClass().getSimpleName()+" "+r.toString());
			return null;
		}
		
}
