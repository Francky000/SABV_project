package projet.view.personne;

import javax.inject.Inject;

import fwk3il.javafx.util.UtilFX;
import fwk3il.javafx.view.IManagerGui;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import projet.data.Personne;
import projet.model.ModelPersonne;
import projet.view.EnumView;


public class ControllerPersonneListe  {
	
	
	// Composants de la vue
	
	@FXML
	private ListView<Personne>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	
	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPersonne		modelPersonne;
	
	
	// Initialisation du controller

	@FXML
	private void initialize() {
		
		// Data binding
		listView.setItems( modelPersonne.getListe() );
		
		// Configuraiton des boutons
		listView.getSelectionModel().getSelectedItems().addListener( 
		        (ListChangeListener<Personne>) (c) -> {
		        	configurerBoutons();
		});
    	configurerBoutons();
	}
	
	public void refresh() {
		modelPersonne.actualiserListe();
		UtilFX.selectInListView(listView, modelPersonne.getEnCours() );
		listView.requestFocus();
	}
	
	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelPersonne.preparerAjouter();
		managerGui.showView( EnumView.PersonneForm );
	}
	
	@FXML
	private void doModifier() {
		Personne item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelPersonne.preparerModifier( listView.getSelectionModel().getSelectedItem() );
			managerGui.showView( EnumView.PersonneForm );
		}
	}
	
	@FXML
	private void doSupprimer() {
		Personne item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			if ( managerGui.demanderConfirmation("Etes-vous sûr de voulir supprimer cette personne ?" ) ) {
				modelPersonne.supprimer( listView.getSelectionModel().getSelectedItem() );
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
