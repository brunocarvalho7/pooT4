package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;

import locacao.model.Ator;

public class GAtor {
	
	static int ultimoID = 0;
	
	static List<Ator> atores = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GAtor.ultimoID = ultimoID;
	}

	public static List<Ator> getAtores() {
		return atores;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<atores.size();i++){
			if(atores.get(i).getIdAtor() == id){
				return i;
			}
		}
		
		return -1;
	}
	
}
