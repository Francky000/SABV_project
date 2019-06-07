package projet.view.menu;

import java.io.IOException;

import javax.inject.Inject;

//import fwk3il.javafx.view.IEnumView;
import fwk3il.javafx.view.IManagerGui;
//import fwk3il.stub.ManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import projet.data.Personne;
import projet.model.ModelCategorie;
import projet.model.ModelPersonne;
import projet.model.ModelVisiteur;
import projet.view.EnumView;
//import projet.view.ManagerGui;

public class ControllerMenu {

	// Autres champs

	@Inject
	private IManagerGui managerGui;

	@Inject
	private ModelCategorie modelcategorie;

	@Inject
	private ModelVisiteur modelvisiteur;

	@Inject
	private ModelTheme modeltheme;

	// composants de la vue
	@FXML
	ComboBox<String> comboBoxTheme;

	@FXML
	ComboBox<String> comboBoxAge;

	@FXML
	TextField TfNom;

	@FXML
	TextField TfPrenom;

	@FXML
	RadioButton RbSimple;

	@FXML
	RadioButton RbParcours;

	@FXML
	private void doQuitter() {

		managerGui.exit();
	}


	//@FXML // Initialisation des controllers ne depend plus de FXML
	public void initialize() {

		// Champs simples
		Visiteur encours = modelvisiteur.getEnCours();
		TfNom.textProperty().bindBidirectional(encours.nomProperty());
		TfPrenom.textProperty().bindBidirectional(encours.nomProperty());

		// configuration des combo box

		// Data binding

		comboBoxAge.setItems(modelcategorie.getListe());
		comboBoxAge.valueProperty().bindBidirectional(encours.categorieProperty());
		// comboBoxAge.getItems().addAll("0 -4 ans","5 -9 ans","9 -13 ans","14 ans et
		// plus");

		comboBoxTheme.setItems(modeltheme.getListe());
		comboBoxTheme.valueProperty().bindBidirectional(encours.themeProperty());
		// comboBoxTheme.getItems().addAll("Faune","Flore","Eau","Deforestation","Jungle");
	}
	
	public void refresh() {
		Categorie categorie = modelvisiteur.getEnCours().getCategorie();
		modelcategorie.actualiserListe();
		modelvisiteur.getEnCours().setCategorie( categorie );
	}
	
	@FXML
	private void doQuizz() throws IOException {

//		if (RbSimple.isSelected()) {
//			System.out.println("MODE SIMPLE");
//
//		}
//		if (RbParcours.isSelected()) {
//			System.out.println("MODE PARCOURS");
//		}
        modelvisiteur.validerMiseAJour();
		managerGui.showView(EnumView.Quizz);
	}

}