package projet.view.personne;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import projet.data.Categorie;
import projet.model.ModelCategorie;
import projet.view.EnumView;


public class ControllerCategorieForm {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldLibelle;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelCategorie		modelCategorie;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Categorie enCours = modelCategorie.getEnCours();
		textFieldId.textProperty().bindBidirectional( enCours.idProperty(), new IntegerStringConverter()  );
		textFieldLibelle.textProperty().bindBidirectional( enCours.libelleProperty()  );
		
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.CategorieListe );
	}
	
	@FXML
	private void doValider() {
		modelCategorie.validerMiseAJour();
		managerGui.showView( EnumView.CategorieListe );
	}

}
