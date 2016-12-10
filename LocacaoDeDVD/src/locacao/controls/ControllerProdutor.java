package locacao.controls;

import locacao.aplicacao.GProdutor;
import locacao.model.Produtor;

public class ControllerProdutor {
	@FXML
	TextArea id, descricao;

	@FXML
	Button previous, next, novo, editar, salvar, remover;

	@FXML
	public void initialize(){
		System.out.println(GProdutor.getProdutores());

		if(GProdutor.getProdutores().isEmpty()){ //OK
			previous.setDisable(true);
			next.setDisable(true);
			editar.setDisable(true);
			remover.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);

			if(GProdutor.getProdutores().size() == 1){
				next.setDisable(true);
			}else{
				next.setDisable(false);
			}
		}
	}

	public void novoRegistro(){ //OK
		descricao.setEditable(true);
		id.setText(String.valueOf(GProdutor.getUltimoID() + 1));

		descricao.setText("");
		descricao.requestFocus();

		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		editar.setDisable(true);
		salvar.setDisable(false);
		remover.setDisable(true);
	}

	public void editarRegistro(){
		descricao.setEditable(true);
		descricao.requestFocus();

		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		editar.setDisable(true);
		salvar.setDisable(false);
		remover.setDisable(true);
	}

	public boolean salvarProdutor(){
		//TODO RESTRI��ES
		int vID = Integer.parseInt(id.getText());
		String vNome = descricao.getText();

		if(vNome.trim().isEmpty()){
			Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do produtor", ButtonType.OK);
			vazio.show();
			descricao.setText("");
			descricao.requestFocus();
		}else{

			Produtor produtor = new Produtor(vID, vNome);

			if(GProdutor.getProdutores().contains(produtor)){
				GProdutor.getProdutores().set(GProdutor.getIndex(vID) , produtor);
				//GAtor.getAtores().set(GAtor.getIndex(vID) , ator);
			}else{
				GProdutores.getProdutores().add(produtor);
				GProdutor.setUltimoID(GProdutor.getUltimoID() + 1);
			}
			nextProdutor();

			novo.setDisable(false);
			editar.setDisable(false);
			salvar.setDisable(true);
			remover.setDisable(false);
			descricao.setEditable(false);


			Alert a = new Alert(AlertType.INFORMATION, "Dados salvos com sucesso!!", ButtonType.CLOSE);
			a.show();

			return true;
		}
		return false;
	}

	public void previousProdutor(){ //OK
		int indiceAnterior = (GProdutor.getIndex(Integer.parseInt(id.getText())) - 1);
		System.out.println(GProdutor.getProdutores());
			System.out.println("Indice Anterior: "+indiceAnterior );
			if(indiceAnterior >= 0){
			  carregarInformacoes(indiceAnterior);
			}

			if(indiceAnterior == 0){
			  previous.setDisable(true);
			}else{
			  previous.setDisable(false);
			}
			next.setDisable(false);
	}


	public void nextProdutor(){
		int proximoIndice = (GProdutor.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GProdutor.getProdutores().size()){
			carregarInformacoes(proximoIndice);
		}

		if(proximoIndice + 1 == GProdutor.getProdutores().size()){
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);
	}

	public void removerProdutor(){
		if(!id.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o produtor "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == ButtonType.YES){
				GProdutor.getProdutores().remove(GProdutor.getIndex(Integer.parseInt(id.getText())));

				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();

				System.out.println(GProdutor.getProdutores());
				if(GProdutor.getProdutores().size() == 0){
					id.setText("");
					descricao.setText("");
					previous.setDisable(true);
					next.setDisable(true);
					remover.setDisable(true);
					editar.setDisable(true);
				}else{
					carregarInformacoes(GProdutor.getProdutores().size()- 1);
					previous.setDisable(false);
				}
			}
		}
	}

	public void carregarInformacoes(int indice){
		Produtor a = (Produtor)GProdutor.getProdutores().get(indice);

		id.setText(String.valueOf(a.getIdProdutor()));
		descricao.setText(a.getNome());
	}
}
