package projet.view;

import fwk3il.javafx.view.IEnumView;
import javafx.scene.layout.Pane;

public enum EnumView implements IEnumView {

	// Valeurs
	
	Menu			( "menu/menu.fxml" ),
	Quizz		    ( "quizz/quizz.fxml" ),
	PaneMenu        ("systeme/PaneMenu.fxml"),
	Quizz_indice    ("quizzindice/quizz_indice.fxml"),
	Felicitations   ("felicitation/felicitation.fxml"),
	Diplome 		 ( "diplome/diplome.fxml" ),
	DiplomeParcours  ( "diplomeparcours/diplomeparcours.fxml" ),
	DANSLEAU         ( "DANSLEAU/DANSLEAU.fxml" ),
	FROID             ( "FROID/FROID.fxml" ),
	LELAIT           ( "LELAIT/LELAIT.fxml" ),
	LESINGE          (  "LESINGE/LESINGE.fxml" ),
	LONGCOU          ( "LONGCOU/LONGCOU.fxml" ),
	RESPIRATION      ( "RESPIRATION/RESPIRATION.fxml" )
	;
//
//	Info("systeme/ViewInfo.fxml"), 
//	Connexion("accueil/ViewAccueil.fxml"),
//	Menu("menu/Vue2.fxml"),
//	CompteListe("compte/ViewCompteListe.fxml"), 
//	CompteForm("compte/ViewCompteForm.fxml"),
//	CategorieListe("personne/ViewCategorieListe.fxml"), 
//	CategorieForm("personne/ViewCategorieForm.fxml"),
//	PersonneListe("personne/ViewPersonneListe.fxml"), 
//	PersonneForm("personne/ViewPersonneForm.fxml"),
//	MemoListe("memo/ViewMemoListe.fxml"), 
//	MemoForm("memo/ViewMemoForm.fxml"),
//	TestDaoCategorie("test/ViewTestDaoCategorie.fxml"), 
//	TestDaoMemo("test/ViewTestDaoMemo.fxml"),;

	// Champs

	private String path;
	private Pane pane;
	private Object controller;
	private Runnable runnableEnter;
	private Runnable runnableEscape;
	private boolean configured;

	// Constructeur

	EnumView(String path) {
		this.path = path;
	}

	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Pane getPane() {
		return pane;
	}

	@Override
	public void setPane(Pane pane) {
		this.pane = pane;
	}

	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}

	@Override
	public Runnable getRunnableEnter() {
		return runnableEnter;
	}

	@Override
	public void setRunnableEnter(Runnable runnableEnter) {
		this.runnableEnter = runnableEnter;
	}

	@Override
	public Runnable getRunnableEscape() {
		return runnableEscape;
	}

	@Override
	public void setRunnableCancel(Runnable runnableCancel) {
		this.runnableEscape = runnableCancel;
	}

	@Override
	public boolean isConfigured() {
		return configured;
	}

	@Override
	public void setConfigured(boolean configured) {
		this.configured = configured;
	}
}