package projet.view.memo;

import javax.inject.Inject;

import fwk3il.javafx.util.UtilFX;
import fwk3il.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import projet.data.Memo;
import projet.model.ModelMemo;
import projet.view.EnumView;


public class ControllerMemoListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Memo>		listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMemo			modelMemo;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelMemo.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getTitre() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelMemo.actualiserListe();
		UtilFX.selectInListView( listView, modelMemo.getEnCours() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelMemo.preparerAjouter();;
		managerGui.showView( EnumView.MemoForm );
	}

	@FXML
	private void doModifier() {
		Memo item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelMemo.preparerModifier(item);
			managerGui.showView( EnumView.MemoForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Memo item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.afficherErreur( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.demanderConfirmation( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelMemo.supprimer( item );
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
