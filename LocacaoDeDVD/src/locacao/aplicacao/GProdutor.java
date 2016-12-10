package locacao.aplicacao;

import locacao.model.Genero;

public class GProdutor {
static int ultimoID = 0;
	
	static List<Produtor> produtores = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GProdutor.ultimoID = ultimoID;
	}

	public static List<Produtor> getProdutores() {
		return produtores;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<produtores.size();i++){
			if(produtores.get(i).getIdProdutor() == id){
				return i;
			}
		}
		
		return -1;
	}
	
}
