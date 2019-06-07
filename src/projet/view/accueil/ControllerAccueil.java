package projet.view.accueil;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import projet.MainProjet;
import projet.view.EnumView;

public class ControllerAccueil {
	
	@Inject
	private IManagerGui			managerGui;

	// Actions
	@FXML
	private void doQuitter() {
		Platform.exit();
	}
	
	@FXML
	private void doMenu() throws IOException  {
		managerGui.showView(EnumView.Menu);
	}
	
	
}