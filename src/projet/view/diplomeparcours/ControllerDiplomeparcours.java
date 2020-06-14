package projet.view.diplomeparcours;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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


public class ControllerDiplomeparcours {

	// Autres champs
	@FXML
	private Label Scorep;
	@Inject
	private IManagerGui managerGui;
	@Inject
	private ControllerQuizz controllerquiz;
	@FXML
	private Button Retour;

	
	
	@FXML
	public void initialize() {
		Scorep.setText(String.valueOf(ControllerQuizz.scorep));

	}

	public void refresh() {
		initialize();
	}
	@FXML
	private  void doMenu() throws IOException {
		controllerquiz.scorep=0;
		managerGui.showView(EnumView.Menu);
	}

}