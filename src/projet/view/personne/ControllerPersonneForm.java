package projet.view.personne;

import javax.inject.Inject;

import fwk3il.javafx.control.EditingCell;
import fwk3il.javafx.view.IManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import projet.data.Categorie;
import projet.data.Personne;
import projet.data.Telephone;
import projet.model.ModelCategorie;
import projet.model.ModelPersonne;
import projet.view.EnumView;


public class ControllerPersonneForm  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldNom;
	@FXML	
	private TextField			textFieldPrenom;
    @FXML
    private ComboBox<Categorie>	comboBoxCategorie;
	@FXML
	private TableView<Telephone>	tableViewTelphones;
	@FXML
	private TableColumn<Telephone, Integer> columnId;
	@FXML
	private TableColumn<Telephone, String> columnLibelle;
	@FXML
	private TableColumn<Telephone, String> columnNumero;

	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPersonne		modelPersonne;
	@Inject
    private ModelCategorie 		modelCategorie;
    
	
	// Initialisation du controller
	
	public void initialize() {
		
		// Champs simples
		Personne enCours = modelPersonne.getEnCours();
		textFieldId.textProperty().bindBidirectional( enCours.idProperty(), new IntegerStringConverter() );
		textFieldNom.textProperty().bindBidirectional( enCours.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( enCours.prenomProperty() );

        
		// Configuration de la combo box

		// Data binding
		comboBoxCategorie.setItems( modelCategorie.getListe() );
        comboBoxCategorie.valueProperty().bindBidirectional( enCours.categorieProperty() );
 		
		
		// Configuration du TableView

		// Data binding
		tableViewTelphones.setItems(  modelPersonne.getEnCours().getTelephones() );
		
		columnId.setCellValueFactory( t -> t.getValue().idProperty() );
		columnLibelle.setCellValueFactory( t -> t.getValue().libelleProperty() );
		columnNumero.setCellValueFactory( t -> t.getValue().numeroProperty() );

		columnLibelle.setCellFactory(  p -> new EditingCell<>() );
		columnNumero.setCellFactory(  p -> new EditingCell<>() );		
	
	}
	
	public void refresh() {
		Categorie categorie = modelPersonne.getEnCours().getCategorie();
		modelCategorie.actualiserListe();
		modelPersonne.getEnCours().setCategorie( null );	
		modelPersonne.getEnCours().setCategorie( categorie );	
	}
	
	
	// Actions
	
	@FXML
	private void doValider() {
		modelPersonne.validerMiseAJour();
		managerGui.showView( EnumView.PersonneListe );
	}
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PersonneListe );
	}
	
	@FXML
	private void doAjouterTelephone() {
		modelPersonne.ajouterTelephone();
	}
	
	
	@FXML
	private void doiSupprimerTelephone() {
		Telephone telephone = tableViewTelphones.getSelectionModel().getSelectedItem();
		modelPersonne.supprimerTelephone(telephone);
	}
    
}
