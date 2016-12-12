package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;

import locacao.model.Gravadora;

public class GGravadora {
	
	static int ultimoID = 0;
	
	static List<Gravadora> gravadoras = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GGravadora.ultimoID = ultimoID;
	}

	public static List<Gravadora> getGravadoras() {
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
	
	public static Gravadora getGravadora(int idGravadora){
		for(Gravadora gravadora : gravadoras){
			if(gravadora.getIdGravadora() == idGravadora){
				return gravadora;
			}
		}
		
		return null;
	}
	
}
