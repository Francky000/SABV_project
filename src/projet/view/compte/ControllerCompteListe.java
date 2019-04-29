package projet.view.compte;

import javax.inject.Inject;

import fwk3il.javafx.util.UtilFX;
import fwk3il.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import projet.data.Compte;
import projet.model.ModelCompte;
import projet.view.EnumView;


public class ControllerCompteListe  {
	
	
	// Composants de la vue

	@FXML
	private ListView<Compte>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonMemos;

	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelCompte			modelCompte;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		// Configuration de l'objet ListView
		
		// Data binding
		listView.setItems( modelCompte.getListe() );

		// Affichage
		listView.setCellFactory( (list) -> {
		    return new ListCell<Compte>() {
		        @Override
		        protected void updateItem(Compte item, boolean empty) {
		            super.updateItem(item, empty);
		            if (item == null) {
		                setText(null);
		            } else {
		                setText(item.pseudoProperty().getValue() );
		            }
		        }
		    };
		});		

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener( 
				(ListChangeListener<Compte>) (c) -> {
					 configurerBoutons();					
		});
		configurerBoutons();
	}
	
	public void refresh() {
		modelCompte.actualiserListe();
		UtilFX.selectInListView(listView, modelCompte.getEnCours() );
		listView.requestFocus();
	}

	
	// Actions

	@FXML
	private void doAjouter() {
		modelCompte.preparerAjouter();
		managerGui.showView( EnumView.CompteForm );
	}

	@FXML
	private void doModifier() {
		Compte item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelCompte.preparerModifier( item );
			managerGui.showView( EnumView.CompteForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Compte item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.demanderConfirmation( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelCompte.supprimer( item );
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
