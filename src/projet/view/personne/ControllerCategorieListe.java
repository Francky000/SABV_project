package projet.view.personne;

import javax.inject.Inject;

import fwk3il.javafx.util.UtilFX;
import fwk3il.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import projet.data.Categorie;
import projet.model.ModelCategorie;
import projet.view.EnumView;


public class ControllerCategorieListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Categorie>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelCategorie		modelCategorie;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelCategorie.getListe() );
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
		
	}
	
	public void refresh() {
		modelCategorie.actualiserListe();
		System.out.println("actualiserListe Categorie");
		UtilFX.selectInListView( listView, modelCategorie.getEnCours() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelCategorie.preparerAjouter();
		managerGui.showView( EnumView.CategorieForm );
	}

	@FXML
	private void doModifier() {
		Categorie item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelCategorie.preparerModifier( item );
			managerGui.showView( EnumView.CategorieForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Categorie item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.demanderConfirmation( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelCategorie.supprimer( item );
				refresh();
			}
		}
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				doModifier();
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}

}
