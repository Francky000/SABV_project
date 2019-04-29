package projet.view.systeme;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import projet.commun.Roles;
import projet.model.ModelConnexion;
import projet.view.EnumView;


public class ControllerMenu  {
 	

	// Composants de la vue
	
	@FXML
	private Menu 		menuDonnees;
	@FXML
	private MenuItem	menuItemPersonnes;
	@FXML
	private MenuItem	menuItemCategories;
	@FXML
	private MenuItem	menuItemComptes;
	@FXML
	private Menu 		menuTests;
	@FXML
	private MenuItem	menuItemSeConnecter;
	@FXML
	private MenuItem	menuItemSeDeconnecter;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteActifProperty().addListener(
				( ov, oldValue, newValue) -> {
					Platform.runLater( this::configurerMenu );
				}
			); 

		// Adapte le menu
		configurerMenu();
	}
	

	// Actions
	
	@FXML
	private void doSeConnecter() {
		managerGui.showView( EnumView.Connexion );
	}
	
	@FXML
	private void doSeDeconnecter() {
		modelConnexion.fermerSessionUtilisateur();
		managerGui.showView( EnumView.Connexion );
	}
	
	@FXML
	private void doQuitter() {
		managerGui.exit();
	}
	
	@FXML
	private void doAfficherListeComptes() {
		managerGui.showView( EnumView.CompteListe );;
	}
	
	@FXML
	private void doAfficherListePersonnes() {
		managerGui.showView( EnumView.PersonneListe );;
	}
	
	@FXML
	private void doAfficherListeCategories() {
		managerGui.showView( EnumView.CategorieListe );;
	}
	
	@FXML
	private void doAfficherListMemos() {
		managerGui.showView( EnumView.MemoListe );;
	}
	
	@FXML
	private void doTestDaoCategorie() {
		managerGui.showView( EnumView.TestDaoCategorie );;
	}
	
	@FXML
	private void doTestDaoMemo() {
		managerGui.showView( EnumView.TestDaoMemo );;
	}
	

	// Méthodes auxiliaires
	
	private void configurerMenu() {
		
		menuDonnees.setVisible(true);
		menuItemPersonnes.setVisible(false);
		menuItemCategories.setVisible(false);
		menuItemComptes.setVisible(false);
		menuTests.setVisible(false);

		menuItemSeConnecter.setVisible(true);
		menuItemSeDeconnecter.setVisible(false);
		
		if( modelConnexion.getCompteActif() != null ) {

			menuItemSeConnecter.setVisible(false);
			menuItemSeDeconnecter.setVisible(true);

			if( modelConnexion.getCompteActif().isInRole( Roles.UTILISATEUR) ) {
				menuItemPersonnes.setVisible(true);
			}

			if( modelConnexion.getCompteActif().isInRole( Roles.ADMINISTRATEUR ) ) {
				menuItemCategories.setVisible(true);
				menuItemComptes.setVisible(true);
				menuTests.setVisible(true);
			}
		}
	}

}
