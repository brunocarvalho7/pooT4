package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;

import locacao.model.Genero;

public class GGenero {
	static int ultimoID = 0;
	
	static List<Genero> generos = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GGenero.ultimoID = ultimoID;
	}

	public static List<Genero> getGeneros() {
		return generos;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<generos.size();i++){
			if(generos.get(i).getIdGenero() == id){
				return i;
			}
		}
		
		return -1;
	}
	
}
