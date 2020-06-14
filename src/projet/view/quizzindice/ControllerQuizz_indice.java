package projet.view.quizzindice;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import projet.dao.DaoQuestion;
import projet.dao.DaoQuizz;
import projet.dao.DaoTheme;
import projet.model.ModelQuizz;
import projet.model.ModelReponse;
import projet.model.ModelTheme;
import projet.view.EnumView;
//import projet.view.ManagerGui;

public class ControllerQuizz_indice {
	int a = 0;
	int next = 0;
	@Inject
	private IManagerGui managerGui;
	private String resultat = "";

	@FXML
	Label Labeltheme;
	@FXML
	Label Labelquestion;
	@FXML
	Button ButtRep1;
	@FXML
	Button ButtRep2;
	@FXML
	Button ButtRep3;

	Button[] butt = new Button[2];

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

	public int recadrer(int r) {
		if (r <= 0)
			r = 0;
		if (r >= 2)
			r = 2;
		return r;
	}

	@FXML
	private void doPrecedent() throws IOException {
		// AFFICHER QUESTION PRECEDENTE
//		next--;
//		recadrer(next);
//		a = recadrer(a - 1);
//		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());
//
//		modelquizz.actualiserListe();
//
//		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
//		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle());
//		for (int i = 0; i < 4; i++) {
//
//			butt[i].setText(modelreponse.getListe().get(i).getlibellert());
//
//		}

	}

	@FXML
	private void doSuivant() throws IOException {
		// AFFICHER QUESTION SUIVANTE
//		next++;
//		recadrer(next);
//		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());
//
//		modelquizz.actualiserListe();
//
//		a = recadrer(a + 1);
//		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
//		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle());
//		modelreponse.actualiserListe(modelquizz.getListeQues().get(a).getlibelle());
//
//		System.out.println(modelquizz.getListeQues().get(a).getlibelle());
//		System.out.println("" + a);
//
//		for (int i = 0; i < 4; i++) {
//
//			butt[i].setText(modelreponse.getListe().get(i).getlibellert());
//
//		}
		
		managerGui.showView(EnumView.Quizz);

	}

	@FXML
	private void initialize() {
		recadrer(next);
		System.out.println(modeltheme.getEnCours().getlibelleth());
		modeltheme.getEnCours().setNumth(daotheme.valeurNumth(modeltheme.getEnCours().getlibelleth()));
		butt[0] = ButtRep1;
		butt[1] = ButtRep2;
		butt[2] = ButtRep3;

		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());

		modelquizz.actualiserListe();
		modelquizz.TirerQuizz();

		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(0).getlibelle());

		modelreponse.actualiserListe(modelquizz.getListeQues().get(0).getlibelle());
		
		ButtRep1.setText(modelreponse.getListe().get(0).getlibellert());
		ButtRep2.setText(modelreponse.getListe().get(1).getlibellert());
		ButtRep3.setText(modelreponse.getListe().get(2).getlibellert());

	}

	@FXML
	public void actionbouton1(ActionEvent ev) {
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		System.out.println(modelquizz.getListeQues().get(next).getIdques());
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (resultat.equals(butt[0].getText())) {
			System.out.println(butt[0].getText());
			System.out.println("Bonne rep");
		}
	}

	@FXML
	public void actionbouton2(ActionEvent ev) {
		// resultat = butt[1].getText();
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (resultat.equals(butt[1].getText())) {
			System.out.println(butt[1].getText());
			System.out.println("Bonne rep");
		}
	}

	@FXML
	public void actionbouton3(ActionEvent ev) {
		// resultat = butt[2].getText();
		System.out.println("next = " + next);
		System.out.println("------------------------------------");
		recadrer(next);
		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
		if (resultat.equals(butt[2].getText())) {
			System.out.println(butt[2].getText());
			System.out.println("Bonne rep");
		}
	}

}
