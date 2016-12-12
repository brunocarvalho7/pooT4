package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;

import locacao.model.Atendente;

public class GAtendente {
static int ultimoID = 0;
	
	static List<Atendente> atendentes = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GAtor.ultimoID = ultimoID;
	}

	public static List<Atendente> getAtendentes() {
		return atendentes;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<atendentes.size();i++){
			if(atendentes.get(i).getIdPessoa() == id){
				return i;
			}
		}
		
		return -1;
	}
}
