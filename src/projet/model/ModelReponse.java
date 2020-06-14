package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import projet.commun.IMapper;
import projet.dao.DaoCategorie;
import projet.dao.DaoQuestion;
import projet.dao.DaoQuizz;
import projet.dao.DaoTheme;
import projet.dao.DaoVisiteur;
import projet.data.Personne;
import projet.data.Reponse;
import projet.data.Telephone;

public class ModelReponse {

	// Données observables

	private final ObservableList<Reponse> liste = FXCollections.observableArrayList();

	private final Reponse enCours = new Reponse();

	// Autres champs

	@Inject
	private IMapper mapper;
	@Inject
	private DaoQuestion daoQuestion;
	@Inject
	private DaoQuizz daoQuizz;
	@Inject
	private DaoTheme daoTheme;
	@Inject
	private DaoCategorie daoCategorie;
int b=0;int d;
	// Modèles
	private ModelQuizz modelQuizz;

	// Getters

	public ObservableList<Reponse> getListe() {
		return liste;
	}

	public Reponse getEnCours() {
		return enCours;
	}

	// Actualisations

	public void actualiserListe(String libelleques) {
		
		liste.clear();
		liste.addAll( daoQuestion.listReponse(daoQuestion.valeurIdQues(libelleques)) );
	}
	
	public void actualiserListe2() {
		//b++;
		liste.clear();
		liste.addAll( daoQuestion.listReponse(d) );
		//System.out.println(liste.get(0));
		d--;
	}

	// Actions

//	public void AfficherQuestion() {
//		int i = 0;
//		for (Reponse reponse : liste) {
//			if (reponse.getIdques() == modelQuizz.getEnCoursQues().getIdques()) {
//				i++;
//				if (i == 1)
//					ButtRep1.setText(reponse.getlibellert());
//				if (i == 2)
//					ButtRep2.setText(reponse.getlibellert());
//				if (i == 3)
//					ButtRep3.setText(reponse.getlibellert());
//				if (i == 4)
//					ButtRep4.setText(reponse.getlibellert());
//			}
//		}
//	}

	// Si on clique sur l'un des 4 bouttons ca appelle cette methode
//	public void BonneReponse() {
//		// coloration verte de la bonne reponse
//
//		if (ButtRep1.getText().equals(enCours.getlibellert()) && enCours.getverite() == 1) {
//			System.out.println("BONNE REPONSE");
//			score++;
//
//		} else if (ButtRep2.getText().equals(enCours.getlibellert()) && enCours.getverite() == 1) {
//			System.out.println("BONNE REPONSE");
//			score++;
//		} else if (ButtRep3.getText().equals(enCours.getlibellert()) && enCours.getverite() == 1) {
//			System.out.println("BONNE REPONSE");
//			score++;
//		} else if (ButtRep4.getText().equals(enCours.getlibellert()) && enCours.getverite() == 1) {
//			System.out.println("BONNE REPONSE");
//			score++;
//		} else {
//			// coloration rouge du choix de reponse
//			System.out.println("MAUVAISE REPONSE");
//		}
//	}

	

}
