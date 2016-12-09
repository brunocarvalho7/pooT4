package locacao.model;

public class Genero {

	private int idGenero;
	private String nome;
	
	public Genero(int idGenero, String nome) {
		super();
		this.idGenero = idGenero;
		this.nome = nome;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nome=" + nome + "]";
	}
	
	public boolean equals(Genero o){
		if (o.getIdGenero() == getIdGenero()){
			return true;
		}
		
		return false;
	}
	
}
