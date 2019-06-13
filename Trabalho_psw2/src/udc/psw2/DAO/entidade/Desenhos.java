package udc.psw2.DAO.entidade;

public class Desenhos {
	private long id;
	private String nome;

	public Desenhos(long id) {
		this.id = id;
		nome = "";
	}

	public Desenhos(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Desenhos [id=" + id + ", nome=" + nome + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}