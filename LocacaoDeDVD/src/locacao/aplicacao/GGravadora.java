package locacao.aplicacao;

import locacao.model.Ator;

public class GGravadora {
	
	static int ultimoID = 0;
	
	static List<Gravadora> gravadoras = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GGravadora.ultimoID = ultimoID;
	}

	public static List<Gravadora> getAtores() {
		return gravadoras;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<gravadoras.size();i++){
			if(gravadoras.get(i).getIdGravadora() == id){
				return i;
			}
		}
		
		return -1;
	}
}
