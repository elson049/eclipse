package udc.psw2.lista;

public interface Iterador<Tipo> {

	public Tipo getObject();
	public Tipo proximo();
	public Tipo anterior();
	
}