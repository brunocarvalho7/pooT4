package locacao.aplicacao;

import java.util.ArrayList;
import java.util.List;
import locacao.model.Aluguel;;

public class GAluguel {
	
	static int ultimoID = 0;
	
	static List<Aluguel> alugueis = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GAluguel.ultimoID = ultimoID;
	}

	public static List<Aluguel> getAlugueis() {
		return alugueis;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<alugueis.size();i++){
			if(alugueis.get(i).getIdAluguel() == id){
				return i;
			}
		}
		
		return -1;
	}
	
}
