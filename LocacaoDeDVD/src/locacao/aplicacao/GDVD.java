package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;
import locacao.model.DVD;

public class GDVD {
	
	static int ultimoID = 0;
	
	static List<DVD> dvds = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GDVD.ultimoID = ultimoID;
	}

	public static List<DVD> getDvds() {
		return dvds;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<dvds.size();i++){
			if(dvds.get(i).getIdDvd() == id){
				return i;
			}
		}
		
		return -1;
	}
	
}
