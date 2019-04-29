package projet.view.systeme;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projet.data.Compte;
import projet.model.ModelConnexion;
import projet.model.ModelInfo;
import projet.view.EnumView;


public class ControllerConnexion {
	

	// Composants de la vue
	
	@FXML
	private TextField		fieldPseudo;
	@FXML
	private PasswordField	fieldMotDePasse;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	@Inject
	private ModelInfo		modelInfo;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		// Data binding
		Compte compte = modelConnexion.getEnCours();
		fieldPseudo.textProperty().bindBidirectional( compte.pseudoProperty() );
		fieldMotDePasse.textProperty().bindBidirectional( compte.motDePasseProperty() );
	}
	

	// Actions
	
	@FXML
	private void doConnexion() {
		managerGui.execTask( () -> {
			modelConnexion.ouvrirSessionUtilisateur();
			Platform.runLater( () -> {
         			modelInfo.titreProperty().setValue( "Bienvenue" );
        			modelInfo.messageProperty().setValue( "Connexion réussie" );
        			managerGui.showView(EnumView.Info);
            }) ;
		} );
	}
	

}
