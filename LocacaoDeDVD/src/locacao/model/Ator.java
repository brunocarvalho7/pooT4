package locacao.model;

public class Ator {

	private int idAtor;
	private String nome;
	
	public int getIdAtor() {
		return idAtor;
	}
	public void setIdAtor(int idAtor) {
		this.idAtor = idAtor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean equals(Ator o){
		if (o.getIdAtor() == getIdAtor()){
			return true;
		}
		return false;
	}

	public String toString(){
		return "Ator [Id="+id+", Nome="+nome+"]";
	}
}
