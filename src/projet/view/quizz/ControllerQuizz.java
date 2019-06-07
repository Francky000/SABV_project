package projet.view.quizz;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import projet.view.EnumView;
//import projet.view.ManagerGui;

public class ControllerQuizz {
	@Inject
    private IManagerGui managerGui;
	@FXML
	private ImageView imageViewProbleme;


	@FXML
	private void doPrecedent() throws IOException {
		// AFFICHER QUESTION PRECEDENTE
		managerGui.showView(EnumView.Menu);
	}

	@FXML
	private void doSuivant() throws IOException {
		// AFFICHER QUESTION SUIVANTE
	}

	@FXML
	private void initialize() {
		//modelLabyrinthe = Main.getModelLabyrinthe();
		//imageViewProbleme.imageProperty().bind(modelLabyrinthe.imageproblemeProperty());
	}
}
