package projet.view.memo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Inject;

import fwk3il.javafx.util.ConverterStringDouble;
import fwk3il.javafx.util.ConverterStringInteger;
import fwk3il.javafx.util.ConverterStringLocalDate;
import fwk3il.javafx.util.ListenerFocusValidation;
import fwk3il.javafx.view.IManagerGui;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;
import projet.data.Memo;
import projet.data.Personne;
import projet.model.ModelMemo;
import projet.model.ModelPersonne;
import projet.view.EnumView;


public class ControllerMemoForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldTitre;
	@FXML
	private TextArea		textAreaDescription;
	@FXML
	private CheckBox		checkBoxUrgent;
	
	@FXML
	private ToggleGroup		toggleGroupStatut;
	@FXML
	private TextField		textFieldEffectif;
	@FXML
	private TextField		textFieldBudget;
	@FXML
	private DatePicker		datePicherEcheance;
	
	@FXML
	private ImageView		imageViewImage1;
	@FXML
	private ImageView		imageViewImage2;
	
	@FXML
	private ComboBox<Personne>	comboBoxPersonne;
	
	

	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelMemo		modelMemo;
	@Inject
	private ModelPersonne	modelPersonne;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Memo enCours = modelMemo.getEnCours();
		textFieldId.textProperty().bindBidirectional( enCours.idProperty(), new IntegerStringConverter()  );
		textFieldTitre.textProperty().bindBidirectional( enCours.titreProperty() );
		textAreaDescription.textProperty().bindBidirectional( enCours.descriptionProperty() );
		checkBoxUrgent.selectedProperty().bindBidirectional( enCours.flagUrgentProperty() );
	
		toggleGroupStatut.selectedToggleProperty().addListener( this::actualiserStatut ) ; 
		enCours.statutProperty().addListener(  this::actualiserStatut );
		actualiserStatut( enCours.statutProperty() );
	
		textFieldEffectif.textProperty().bindBidirectional( enCours.effectifProperty(), new ConverterStringInteger() );
		textFieldEffectif.focusedProperty().addListener( new ListenerFocusValidation( enCours.effectifProperty()  ));

		textFieldBudget.textProperty().bindBidirectional( enCours.budgetProperty(), new ConverterStringDouble( "#,##0.00" ) );
		textFieldBudget.focusedProperty().addListener( new ListenerFocusValidation( enCours.budgetProperty(), "Valeur incorrecte pour le budget."  ));

		datePicherEcheance.getEditor().textProperty().bindBidirectional( enCours.echeanceProperty(), new ConverterStringLocalDate() );
		datePicherEcheance.getEditor().focusedProperty().addListener(new ListenerFocusValidation( enCours.echeanceProperty())  );
		
		imageViewImage1.imageProperty().bindBidirectional( modelMemo.image1Property() );
		imageViewImage2.imageProperty().bindBidirectional( modelMemo.image2Property() );
		
		comboBoxPersonne.setItems( modelPersonne.getListe() );
		comboBoxPersonne.valueProperty().bindBidirectional( enCours.personneeProperty() );
	}
	
	public void refresh() {
		Personne personne = modelMemo.getEnCours().getPersonnee();
		modelPersonne.actualiserListe();
		modelMemo.getEnCours().setPersonnee( null );	
		modelMemo.getEnCours().setPersonnee( personne );	
	}
	
	
	// Actions
	
	@FXML
	private void doEnleverPersonne() {
		comboBoxPersonne.setValue(null);
	}
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.MemoListe );
	}
	
	@FXML
	private void doValider() {
		modelMemo.validerMiseAJour();
		managerGui.showView( EnumView.MemoListe );
	}

	@FXML
	private void doChoisirImage1() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier image");
		File fichier = fileChooser.showOpenDialog( managerGui.getStage() );
		if ( fichier != null ) {
			try {
				imageViewImage1.setImage( new Image( new FileInputStream(fichier) ) );
			} catch (FileNotFoundException e) {
				throw new RuntimeException( e );
			}
		}
	}
	
	@FXML
	private void doSupprimerImage1() {
		imageViewImage1.setImage(null);
	}

	@FXML
	private void doChoisirImage2() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier image");
		File fichier = fileChooser.showOpenDialog( managerGui.getStage() );
		modelMemo.getEnCours().setCheminImage2( fichier.getPath() );
	}
	
	@FXML
	private void doSupprimerImage2() {
		modelMemo.getEnCours().setCheminImage2( null );
	}
	
	
	// Méthodes auxiliaires
	
	private void actualiserStatut( Observable observable ) {
		if ( observable == toggleGroupStatut.selectedToggleProperty() ) {
			Toggle bouton = toggleGroupStatut.getSelectedToggle();
			int statut = toggleGroupStatut.getToggles().indexOf( bouton  );
			modelMemo.getEnCours().setStatut( statut );
		} else {
			int statut = modelMemo.getEnCours().getStatut();
			Toggle bouton = toggleGroupStatut.getToggles().get( statut );
			toggleGroupStatut.selectToggle(  bouton );
		}
	}
	
}
