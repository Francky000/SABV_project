package projet.view.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

//import fwk3il.javafx.view.IEnumView;
import fwk3il.javafx.view.IManagerGui;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import fwk3il.stub.ManagerGui;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import projet.dao.DaoCategorie;
import projet.dao.DaoDiplome;
import projet.dao.DaoTheme;
import projet.dao.DaoVisiteur;
import projet.data.Categorie;
import projet.data.Diplome;
import projet.data.Personne;
import projet.data.Theme;
import projet.data.Visiteur;
import projet.model.ModelCategorie;
import projet.model.ModelDiplome;
import projet.model.ModelTheme;
import projet.model.ModelVisiteur;
import projet.view.EnumView;
import projet.view.quizz.ControllerQuizz;

public class ControllerMenu {

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelCategorie modelcategorie;
	@Inject
	private ModelVisiteur modelvisiteur;
	@Inject
	private ModelDiplome modeldiplome;
	@Inject
	private DaoCategorie daocategorie;
	@Inject
	private DaoDiplome daodiplome;
	@Inject
	private DaoTheme daotheme;
	@Inject
	DaoVisiteur dao;
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

	@FXML
	public void initialize() {
		RbParcours.setSelected(true);
		// configuration des combo box

		// Data binding
		modelcategorie.actualiserListe();
		comboBoxAge.getItems().addAll(modelcategorie.getListe());

		modeltheme.actualiserListe();
		comboBoxTheme.setItems(modeltheme.getListe());

		// Champs simples
		modelvisiteur.getEnCours().nomProperty().bindBidirectional(TfNom.textProperty());
		modelvisiteur.getEnCours().prenomProperty().bindBidirectional(TfPrenom.textProperty());
		modelvisiteur.getEnCours().setModejeu(0);
		modeldiplome.getEnCours().libelletaProperty().bind(comboBoxAge.valueProperty());
		
		String a;
		modeldiplome.getEnCours().setscoredip(0);
		modeldiplome.getEnCours().setlibelledip("dispo");
		if (RbSimple.isSelected())
			modeltheme.getEnCours().libellethProperty().bind(comboBoxTheme.valueProperty());
		if (RbParcours.isSelected()) {
			modeltheme.getEnCours().setNumth(1);
			modeltheme.getEnCours().setlibelle((modeltheme.getListe().get(0)));
		}
		ModeJeu();
		
	
	}

	@FXML
	public void refresh() {
		initialize();
		
	}

	@FXML
	public void ModeJeu() {
		if (RbSimple.isSelected()) {
			modelvisiteur.getEnCours().setModejeu(0);
			comboBoxTheme.setDisable(false);
		}
		if (RbParcours.isSelected()) {
			modelvisiteur.getEnCours().setModejeu(1);
			comboBoxTheme.setDisable(true);
		}

	}

	@FXML
	private void doQuizz() throws IOException {
		daodiplome.inserer(modeldiplome.getEnCours());
		dao.inserer(modelvisiteur.getEnCours());
		if (modelvisiteur.getEnCours().getModejeu() == 0) {
	
			modeltheme.getEnCours().setlibelle(comboBoxTheme.getValue());;
			managerGui.showView(EnumView.Quizz);
		}
		if (modelvisiteur.getEnCours().getModejeu() == 1) {

			modeltheme.getEnCours().setNumth(1);
			modeltheme.getEnCours().setlibelle((modeltheme.getListe().get(0)));
			managerGui.showView(EnumView.Quizz);
		}
		
		if(comboBoxAge.getValue().equals("plus_petits")) {
			managerGui.showView(EnumView.FROID);
		}

	}

}