package projet.view.quizz;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import projet.dao.DaoQuestion;
import projet.dao.DaoQuizz;
import projet.dao.DaoTheme;
import projet.model.ModelQuizz;
import projet.model.ModelReponse;
import projet.model.ModelTheme;
import projet.model.ModelVisiteur;
import projet.view.EnumView;
import projet.view.ManagerGui;
//import projet.view.ManagerGui;

public class ControllerQuizz {

	public int a = 0, b = 0, clic = 0;
	public static int score = 0, scorep=0;
	int next = 0;
	@Inject
	private IManagerGui managerGui;
	private String resultat = "";

	@FXML
	Label Labeltheme;
	@FXML
	Label Labelquestion;
	@FXML
	Label Labelindice;
	@FXML
	Label Labelinfos;
	@FXML
	Button ButtRep1;
	@FXML
	Button ButtRep2;
	@FXML
	Button ButtRep3;
	@FXML
	Button ButtRep4;
	@FXML
	Button Buttind;
	


	Button[] butt = new Button[4];

	@Inject
	private ModelTheme modeltheme;

	@Inject
	private ModelQuizz modelquizz;
	@Inject
	private DaoQuizz daoquizz;
	@Inject
	private DaoQuestion daoquestion;
	@Inject
	private DaoTheme daotheme;
	@Inject
	private ModelReponse modelreponse;
	@Inject
	private ModelVisiteur modelvisiteur;

	public int recadrer(int r) {
		if (r <= 0)
			r = 0;
		if (r >= 3)
			r = 3;
		return r;
	}

	public int recadrerth(int r) {
		if (r <= 0)
			r = 0;
		if (r >= 4)
			r = 4;
		return r;
	}

	@FXML
	private void doSuivant() throws IOException {
		// AFFICHER QUESTION SUIVANTE
		next++;
		clic = 0;
		recadrer(next);
		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());

		modelquizz.actualiserListe();

		a = recadrer(a + 1);
		if (a <= 2) {
			modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
			Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle());
			Labelinfos.setText(daoquestion.Rechinfos(daoquestion
					.valeurIdQues(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle())));
			Labelindice.setText(daoquestion.Rechindice(daoquestion
					.valeurIdQues(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle())));
			modelreponse.actualiserListe(modelquizz.getListeQues().get(a).getlibelle());

			System.out.println(modelquizz.getListeQues().get(a).getlibelle());
			System.out.println("" + a);

			for (int i = 0; i < 4; i++) {

				butt[i].setText(modelreponse.getListe().get(i).getlibellert());
				butt[i].setStyle("\"- fx-font-size: 2em;\"");
				butt[i].setStyle("-fx-background-color: white");
			}
		}
		if (next == 3) {
			next = 0;
			a = 0;
			if (modelvisiteur.getEnCours().getModejeu() == 0) {
				if (score == 0) {
					managerGui.showView(EnumView.Menu);
				} 
				else {
					managerGui.showView(EnumView.Diplome);
				}
			} else {
				if (modeltheme.getEnCours().getNumth() + 1 < 5) {
					modeltheme.getEnCours().setlibelle(modeltheme.getListe().get(modeltheme.getEnCours().getNumth()));
					modeltheme.getEnCours().setNumth(recadrerth(modeltheme.getEnCours().getNumth()) + 1);
					System.out.println(modeltheme.getEnCours().getNumth() + 1);
					managerGui.showView(EnumView.Quizz);
				} else {
					if (scorep == 0) {
						managerGui.showView(EnumView.Menu);
					}
						managerGui.showView(EnumView.DiplomeParcours);
				}
			}
		}

	}

	@FXML
	private void initialize() {
		clic = 0;
		score = 0;
		a = 0;
		b = 0;
		next = 0;
		recadrer(next);
		System.out.println(modeltheme.getEnCours().getlibelleth());
		modeltheme.getEnCours().setNumth(daotheme.valeurNumth(modeltheme.getEnCours().getlibelleth()));
		butt[0] = ButtRep1;
		butt[1] = ButtRep2;
		butt[2] = ButtRep3;
		butt[3] = ButtRep4;

		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());

		modelquizz.actualiserListe();
		modelquizz.TirerQuizz();

		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(0).getlibelle());
		Labelindice.setText(daoquestion.Rechindice(daoquestion
				.valeurIdQues(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(0).getlibelle())));
		Labelinfos.setText(daoquestion.Rechinfos(daoquestion
				.valeurIdQues(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(0).getlibelle())));

		modelreponse.actualiserListe(modelquizz.getListeQues().get(0).getlibelle());
		System.out.println(modelquizz.getListeQues().get(0).getlibelle());
		for (int i = 0; i < 4; i++) {
			butt[i].setText(modelreponse.getListe().get(i).getlibellert());
			butt[i].setStyle("-fx-background-color: white");
		}
	}

	@FXML
	public void refresh() {
		initialize();
	}

	@FXML
	public void actionbouton1(ActionEvent ev) {
		clic = clic + 1;
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		System.out.println(modelquizz.getListeQues().get(next).getIdques());
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (clic == 1) {
			if (resultat.equals(butt[0].getText())) {
				butt[0].setStyle("-fx-background-color: green");
				System.out.println(butt[0].getText());
				System.out.println("Bonne rep");
				score = score + 1;
				scorep = scorep + 1;
			} else {
				butt[0].setStyle("-fx-background-color: red");
				for (int i = 0; i < 4; i++) {
					if (resultat.equals(butt[i].getText())) {
						butt[i].setStyle("-fx-background-color: green");
					}
				}
			}
		}
	}

	@FXML
	public void actionbouton2(ActionEvent ev) {
		// resultat = butt[1].getText();
		clic = clic + 1;
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (clic == 1) {
			if (resultat.equals(butt[1].getText()) && (clic == 1)) {
				System.out.println(butt[1].getText());
				butt[1].setStyle("-fx-background-color: green");
				System.out.println("Bonne rep");
				score = score + 1;
				scorep = scorep + 1;
			} else {
				butt[1].setStyle("-fx-background-color: red");
				for (int i = 0; i < 4; i++) {
					if (resultat.equals(butt[i].getText())) {
						butt[i].setStyle("-fx-background-color: green");
					}
				}
			}
		}
	}

	@FXML
	public void actionbouton3(ActionEvent ev) {
		// resultat = butt[2].getText();
		clic = clic + 1;
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (clic == 1) {
			if (resultat.equals(butt[2].getText())) {
				System.out.println(butt[2].getText());
				butt[2].setStyle("-fx-background-color: green");
				System.out.println("Bonne rep");
				score = score + 1;
				scorep = scorep + 1;
			}

			else {
				butt[2].setStyle("-fx-background-color: red");
				for (int i = 0; i < 4; i++) {
					if (resultat.equals(butt[i].getText())) {
						butt[i].setStyle("-fx-background-color: green");
					}
				}
			}
		}
	}

	@FXML
	public void actionbouton4(ActionEvent ev) {
		// resultat = butt[3].getText();
		clic = clic + 1;
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (clic == 1) {
			if (resultat.equals(butt[3].getText())) {
				System.out.println(butt[3].getText());
				butt[3].setStyle("-fx-background-color: green");
				System.out.println("Bonne rep");
				score = score + 1;
				scorep = scorep + 1;
			} else {
				butt[3].setStyle("-fx-background-color: red");
				for (int i = 0; i < 4; i++) {
					if (resultat.equals(butt[i].getText())) {
						butt[i].setStyle("-fx-background-color: green");
					}
				}
			}
		}
	}
	
	@FXML
	public void next(ActionEvent ev) {
		managerGui.showView(EnumView.LELAIT);
	}
	
	@FXML
	public void next1(ActionEvent ev) {
		managerGui.showView(EnumView.LESINGE);
	}
	
	@FXML
	public void next2(ActionEvent ev) {
		managerGui.showView(EnumView.LONGCOU);
	}
	
	@FXML
	public void next3(ActionEvent ev) {
		managerGui.showView(EnumView.RESPIRATION);
	}

	@FXML
	public void next4(ActionEvent ev) {
		managerGui.showView(EnumView.DANSLEAU);
	}
	
	@FXML
	public void next5(ActionEvent ev) {
		managerGui.showView(EnumView.Quizz);
	}
}
