package locacao.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerPrincipal {
	public void telaCliente() throws Exception{
		Stage outraTela = new Stage();
		
		Parent formCliente = FXMLLoader.load(getClass().getResource("../view/FormCliente.fxml"));
		
		outraTela.setTitle("tela de cliente");
		outraTela.setScene(new Scene(formCliente));
		outraTela.show();
	}
}
