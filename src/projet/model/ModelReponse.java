package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import projet.commun.IMapper;
import projet.dao.DaoMemo;
import projet.dao.DaoPersonne;
import projet.data.Personne;
import projet.data.Telephone;

public class ModelReponse {

	// Données observables

	private final ObservableList<Reponse> liste = FXCollections.observableArrayList();

	private final Reponse enCours = new Reponse();

	// Autres champs

	@Inject
	private IMapper mapper;
	@Inject
	private DaoVisiteur daoReponse;
	@Inject
	private DaoQuestion daoQuestion;
	@Inject
	private DaoTheme daoTheme;
	@Inject
	private DaoCategorie daoCategorie;

	// Modèles
	private ModelQuizz modelQuizz;

	@Inject
	private int score = 0;

	// Boutons

	@Inject
	private Button ButtRep1;
	@Inject
	private Button ButtRep2;
	@Inject
	private Button ButtRep3;
	@Inject
	private Button ButtRep4;
	@Inject
	private Button ButtIndice;

	// Getters

	public ObservableList<Reponse> getListe() {
		return liste;
	}

	public Reponse getEnCours() {
		return enCours;
	}

	// Actualisations

	public void actualiserListe() {
		liste.clear();
		liste.addAll(daoReponse.listerTout());
	}

	// Actions

	public void AfficherQuestion() {
		int i = 0;
		for (Reponse reponse : liste) {
			if (reponse.getIdques() == modelQuizz.getEnCoursQues().getId()) {
				i++;
				if (i == 1)
					ButtRep1.setText(reponse.getLibellert());
				if (i == 2)
					ButtRep2.setText(reponse.getLibellert());
				if (i == 3)
					ButtRep3.setText(reponse.getLibellert());
				if (i == 4)
					ButtRep4.setText(reponse.getLibellert());
			}
		}
	}

	// Si on clique sur l'un des 4 bouttons ca appelle cette methode
	public void BonneReponse() {
		// coloration verte de la bonne reponse

		if (ButtRep1.getText().equals(enCours.getLibellert()) && enCours.getVerite() == 1) {
			System.out.println("BONNE REPONSE");
			score++;

		} else if (ButtRep2.getText().equals(Reponse.getLibellert()) && Reponse.getVerite() == 1) {
			System.out.println("BONNE REPONSE");
			score++;
		} else if (ButtRep3.getText().equals(Reponse.getLibellert()) && Reponse.getVerite() == 1) {
			System.out.println("BONNE REPONSE");
			score++;
		} else if (ButtRep4.getText().equals(Reponse.getLibellert()) && Reponse.getVerite() == 1) {
			System.out.println("BONNE REPONSE");
			score++;
		} else {
			// coloration rouge du choix de reponse
			System.out.println("MAUVAISE REPONSE");
		}
	}

	}

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( enCours.getNom() == null || enCours.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( enCours.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( enCours.getPrenom() == null || enCours.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( enCours.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if( enCours.getCategorie() == null ) {
			message.append( "\nLe catégorie doit être indiquée." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		
	
}
