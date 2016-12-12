package locacao.aplicacao;


import java.util.ArrayList;
import java.util.List;

import locacao.model.Cliente;
import locacao.model.Genero;

public class GCliente {
	
	static int ultimoID = 0;
	
	static List<Cliente> clientes = new ArrayList<>();
	
	public static int getUltimoID() {
		return ultimoID;
	}

	public static void setUltimoID(int ultimoID) {
		GCliente.ultimoID = ultimoID;
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}
	
	public static int getIndex(int id){
		for(int i=0;i<clientes.size();i++){
			if(clientes.get(i).getIdPessoa() == id){
				return i;
			}
		}
		
		return -1;
	}
	
	public static Cliente getCliente(int idCliente){
		for(Cliente cliente : clientes){
			if(cliente.getIdPessoa() == idCliente){
				return cliente;
			}
		}
		
		return null;
	}
	
}
