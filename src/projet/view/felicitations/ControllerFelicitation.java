package projet.view.felicitations;

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
import projet.view.EnumView;
//import projet.view.ManagerGui;

public class ControllerFelicitation {

//	int a = 0, b=0;
//	int next = 0;
	@Inject
	private IManagerGui managerGui;
//	private String resultat = "";
//
//	@FXML
//	Label Labeltheme;
//	@FXML
//	Label Labelquestion;
//	@FXML
//	Button ButtRep1;
//	@FXML
//	Button ButtRep2;
//	@FXML
//	Button ButtRep3;
//	@FXML
//	Button ButtRep4;
//	@FXML
//	Button Buttind;
//
//	Button[] butt = new Button[4];
//
//	@Inject
//	private ModelTheme modeltheme;
//
//	@Inject
//	private ModelQuizz modelquizz;
//	@Inject
//	private DaoQuizz daoquizz;
//	@Inject
//	private DaoQuestion daoquestion;
//	@Inject
//	private DaoTheme daotheme;
//	@Inject
//	private ModelReponse modelreponse;
//
//	public int recadrer(int r) {
//		if (r <= 0)
//			r = 0;
//		if (r >= 2)
//			r = 2;
//		return r;
//	}
//
//	@FXML
//	private void doPrecedent() throws IOException {
//		// AFFICHER QUESTION PRECEDENTE
////		next--;
////		recadrer(next);
////	
////		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());
////
////		modelquizz.actualiserListe();
////
////		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
////		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(a).getlibelle());
////		
////		for (int i = 0; i < 4; i++) {
////			b=recadrer(b-1);
////			butt[i].setText(modelreponse.getListe().get(b).getlibellert());
////		}
////		a = recadrer(a - 1);
//	}
//
//	@FXML
//	private void doSuivant() throws IOException {
//		// AFFICHER QUESTION SUIVANTE
//		next++;
//		recadrer(next);
//		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());
//
//		modelquizz.actualiserListe();
//
//		a = recadrer(a + 1);
//		
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
//			butt[i].setStyle ("\"- fx-font-size: 2em;\"");
//			butt[i].setStyle("-fx-background-color: white");
//		}
//		
//		if(a == 3) {
//			
//		}
//
//	}
//
//	@FXML
//	private void initialize() {
//		recadrer(next);
//		System.out.println(modeltheme.getEnCours().getlibelleth());
//		modeltheme.getEnCours().setNumth(daotheme.valeurNumth(modeltheme.getEnCours().getlibelleth()));
//		butt[0] = ButtRep1;
//		butt[1] = ButtRep2;
//		butt[2] = ButtRep3;
//		butt[3] = ButtRep4;
//		// butt[3].setStyle ("\"- fx-font-size: 2em;\"");
//		Labeltheme.setText(modeltheme.getEnCours().getlibelleth());
//
//		modelquizz.actualiserListe();
//		modelquizz.TirerQuizz();
//
//		modelquizz.actualiserListeQues(modeltheme.getEnCours().getNumth());
//		Labelquestion.setText(daoquizz.ListQuestions(modeltheme.getEnCours().getNumth()).get(0).getlibelle());
//
//		modelreponse.actualiserListe(modelquizz.getListeQues().get(0).getlibelle());
//		System.out.println(modelquizz.getListeQues().get(0).getlibelle());
//		for (int i = 0; i < 4; i++) {
//			butt[i].setText(modelreponse.getListe().get(i).getlibellert());
//			butt[i].setStyle("-fx-background-color: white");
//		}
//	}
//
//	@FXML
//	public void actionbouton1(ActionEvent ev) {
//		System.out.println("next = " + next);
//		System.out.println("------------------------------------");
//		recadrer(next);
//		System.out.println(modelquizz.getListeQues().get(next).getIdques());
//		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
//		if (resultat.equals(butt[0].getText())) {
//			butt[0].setStyle("-fx-background-color: green");
//			System.out.println(butt[0].getText());
//			System.out.println("Bonne rep");
//		} else
//			butt[0].setStyle("-fx-background-color: red");
//	}
//
//	@FXML
//	public void actionbouton2(ActionEvent ev) {
//		// resultat = butt[1].getText();
//		System.out.println("next = " + next);
//		System.out.println("------------------------------------");
//		recadrer(next);
//		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
//		if (resultat.equals(butt[1].getText())) {
//			System.out.println(butt[1].getText());
//			butt[1].setStyle("-fx-background-color: green");
//			System.out.println("0000000000000");
//			System.out.println("Bonne rep");
//			// butt[1].setStyle ("\"- fx-font-size: 2em;\"");;;
//		} else
//			butt[1].setStyle("-fx-background-color: red");
//	}
//
//	@FXML
//	public void actionbouton3(ActionEvent ev) {
//		// resultat = butt[2].getText();
//		System.out.println("next = " + next);
//		System.out.println("------------------------------------");
//		recadrer(next);
//		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
//		if (resultat.equals(butt[2].getText())) {
//			System.out.println(butt[2].getText());
//			butt[2].setStyle("-fx-background-color: green");
//			System.out.println("Bonne rep");
//			// butt[2].setStyle ("\"- fx-font-size: 2em;\"");
//		}
//
//		else
//			butt[2].setStyle("-fx-background-color: red");
//	}
//
//	@FXML
//	public void actionbouton4(ActionEvent ev) {
//		// resultat = butt[3].getText();
//		System.out.println("next = " + next);
//		System.out.println("------------------------------------");
//		recadrer(next);
//		resultat = daoquestion.ReponseJuste(modelquizz.getListeQues().get(next).getIdques());
//		if (resultat.equals(butt[3].getText())) {
//			System.out.println(butt[3].getText());
//			butt[3].setStyle("-fx-background-color: green");
//			System.out.println("Bonne rep");
//		} else
//			butt[3].setStyle("-fx-background-color: red");
//	}
//	

	@FXML
	public void actionindice(ActionEvent ev) {
		managerGui.showView(EnumView.Quizz_indice);
	}

//	public void refresh() {
//		for (int i = 0; i < 4; i++) {
//			butt[i].setBackground(null);
//		}
//	}

}
