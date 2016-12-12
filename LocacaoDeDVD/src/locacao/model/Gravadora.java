package locacao.model;

public class Gravadora {

	private int idGravadora;
	private String nome;
	
	public Gravadora(int idGravadora, String nome) {
		super();
		this.idGravadora = idGravadora;
		this.nome = nome;
	}

	public int getIdGravadora() {
		return idGravadora;
	}

	public void setIdGravadora(int idGravadora) {
		this.idGravadora = idGravadora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public boolean equals(Gravadora o){
		if (o.getIdGravadora() == getIdGravadora()){
			return true;
		}
		
		return false;
	}
	
}
