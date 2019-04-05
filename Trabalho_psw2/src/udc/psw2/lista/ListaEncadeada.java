package udc.psw2.lista;

public class ListaEncadeada<Tipo> {
	
	private class No{
		Tipo obj;
		
		No proximo;
		No anterior;
	}
	
	private class IteradorLista implements Iterador<Tipo>{
		
		No noAtual;
		
		IteradorLista(No noAtual){
			this.noAtual = noAtual;
		}
		
		@Override
		public Tipo getObject() {
			if(noAtual == null)
				return null;
			
			iteracoes++;
			return noAtual.obj;
		}

		@Override
		public Tipo proximo() {
			if(noAtual == null)
				return null;
			Tipo o = noAtual.obj;
			noAtual = noAtual.proximo;
			
			iteracoes++;
			return o;
		}

		@Override
		public Tipo anterior() {
			if(noAtual == null)
				return null;
			Tipo o = noAtual.obj;
			noAtual = noAtual.anterior;
			
			iteracoes++;
			return o;
		}
	}
	
	No inicio;
	No fim;
	
	private int tamanho;
	private int iteracoes;
	
	
	public ListaEncadeada(){
		inicio = null;
		fim = null;
		tamanho = 0;
		iteracoes = 0;
	}
	
	public int getTamanho() {
		return tamanho;
	}

	public int getIteracoes() {
		return iteracoes;
	}
	
	public Iterador<Tipo> getInicio() {
		return new IteradorLista(inicio);
	}
	
	public Iterador<Tipo> getFim() {
		return new IteradorLista(fim);
	}

	public void inserirInicio(Tipo obj){
		No novo = new No();
		novo.obj = obj;
		novo.proximo = inicio;
		novo.anterior = null;
		
		if(inicio != null){
			inicio.anterior = novo;
		}
		
		inicio = novo;
		if(fim == null)
			fim = novo;
		
		iteracoes++;
		tamanho++;
	}
	
	public void inserirFim(Tipo obj){
		No novo = new No();
		novo.obj = obj;
		novo.proximo = null;
		novo.anterior = fim;
		
		if(fim != null){
			fim.proximo = novo;
		}
		
		fim = novo;
		if(inicio == null)
			inicio = novo;
		
		iteracoes++;
		tamanho++;
	}
	
	public void inserir(Tipo obj, int indice){
		if(indice == 0){
			inserirInicio(obj);
			return;
		}
		
		if(indice >= tamanho){
			inserirFim(obj);
			return;
		}
		
		No aux = inicio;
		int cont = 0;
		
		for(; cont < indice; cont++, aux = aux.proximo);
			
		iteracoes += cont;
		No novo = new No();
		novo.obj = obj;
		novo.proximo = aux;
		novo.anterior = aux.anterior;
		
		aux.anterior.proximo = novo;
		aux.anterior = novo;
		
		iteracoes++;
		tamanho++;

	}	
	
	public Tipo excluirInicio(){
		if(tamanho == 0)
			return null;
		
		Tipo obj = inicio.obj;
		
		inicio = inicio.proximo;
		inicio.anterior = null;
		
		iteracoes++;
		return obj;
	}
	
	public Tipo excluirFim(){
		if(tamanho == 0)
			return null;
		
		Tipo obj = fim.obj;
		
		fim = fim.anterior;
		fim.proximo = null;
		
		iteracoes++;
		return obj;
	}
	
	public Tipo excluir(int indice){
		if(indice >= tamanho) {
			return null;
		}
		
		if(indice == 0 && !isEmpty()) {
			iteracoes++;
			return excluirInicio();
		}
		
		if(indice == tamanho-1 && !isEmpty()) {
			iteracoes++;
			return excluirFim();
		}
			
		No aux = inicio;
		
		int cont = 0;
		for(; cont < indice; cont++, aux = aux.proximo);
		
		aux.proximo.anterior = aux.anterior;
		aux.anterior.proximo = aux.proximo;
		
		Tipo obj = aux.obj;
		
		iteracoes += cont + 1;
		return obj;
		
	}
	
	public void limpar(){
		inicio = null;
		fim = null;
		tamanho = 0;
	}
	
	public boolean isEmpty(){
		if(tamanho == 0)
			return true;
		return false;
	}
	
	public Tipo buscar(int indice){
		iteracoes++;
		if(indice == 0)
			return inicio.obj;
		if(indice == tamanho -1)
			return fim.obj;
		No aux = inicio;
		
		
		int cont = 0;
		for(; cont < indice; cont++, aux = aux.proximo);
		iteracoes += cont;
		return aux.obj;
	
	}
	public Tipo remover(Tipo obj) {
		if (obj == null)
			return null;

		if (obj == inicio.obj) { // remover inicio
			return excluirInicio();
		}

		if (obj == fim.obj) { // remover o fim
			return excluirFim();
		}

		No aux = inicio;

		// remover no do meio da lista
		while (obj != aux.obj) {
			aux = aux.proximo;
		}

		Tipo dado = aux.obj;

		aux.anterior.proximo = aux.proximo;
		aux.proximo.anterior = aux.anterior;

		tamanho--;
		return dado;
	}
	public void LimpaRemover() {
		while(inicio != null)
			excluirFim();
	}
	
}
