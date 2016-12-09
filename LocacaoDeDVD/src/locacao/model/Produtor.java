package locacao.model;

public class Produtor {

	private int idProdutor;
	private String nome;
	
	public Produtor(int idProdutor, String nome) {
		this.idProdutor = idProdutor;
		this.nome = nome;
	}
	
	public int getIdProdutor() {
		return idProdutor;
	}
	public void setIdProdutor(int idProdutor) {
		this.idProdutor = idProdutor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return "Produtor [idProdutor=" + idProdutor + ", nome=" + nome + "]";
	}
	
	public boolean equals(Produtor o){
		if(o.getIdProdutor() == getIdProdutor()){
			return true;
		}
		return false;
	}
	
}
