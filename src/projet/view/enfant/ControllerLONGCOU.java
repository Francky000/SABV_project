package projet.view.enfant;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import projet.view.EnumView;

public class ControllerLONGCOU {
	@Inject
	private IManagerGui managerGui;
	
	private int a;
	
	@FXML
	Label label;

	@FXML
	Button Bouton1;
	
	@FXML
	Button Bouton2;
	
	@FXML
	Button nextt;

	@FXML
	private void doBouton1() {
		Bouton1.setStyle("-fx-background-color : green");
		Bouton2.setStyle("-fx-background-color : red");
		a = 1;
		label.setText("BRAVO!");
	}

	@FXML
	private void doBouton2() {
		Bouton1.setStyle("-fx-background-color: green");
		a = 0;
		label.setText("COURAGE!");
	}
//	
//	@FXML
//	private void doSuivant() throws IOException{
//		Main.showRESPIRATION();
//	}
	@FXML
	public void next(ActionEvent ev) {
		managerGui.showView(EnumView.RESPIRATION);
	}
	
}
