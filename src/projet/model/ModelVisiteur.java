package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoMemo;
import projet.dao.DaoPersonne;
import projet.data.Personne;
import projet.data.Telephone;

public class ModelVisiteur {

	// Données observables

	private final ObservableList<Visiteur> liste = FXCollections.observableArrayList();

	private final Visiteur enCours = new Visiteur();

	// Autres champs

	@Inject
	private IMapper mapper;
	@Inject
	private DaoVisiteur daoVisiteur;
	@Inject
	private DaoCategorie daoCategorie;
	@Inject
	private DaoTheme daoTheme;
	
	//creer les getters/setters du mode de jeu dans le daoVisiteur
	// Getters

	public ObservableList<Visiteur> getListe() {
		return liste;
	}

	public Visiteur getEnCours() {
		return enCours;
	}

	// Actualisations

	public void actualiserListe() {
		liste.clear();
		liste.addAll(daoVisiteur.listerTout());
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(enCours, new Visiteur());
	}

	public void preparerModifier(Visiteur item) {
		mapper.update(enCours, item);
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (enCours.getNom() == null || enCours.getNom().isEmpty()) {
			message.append("\nLe nom ne doit pas être vide.");
		} else if (enCours.getNom().length() > 25) {
			message.append("\nLe nom est trop long.");
		}

		if (enCours.getPrenom() == null || enCours.getPrenom().isEmpty()) {
			message.append("\nLe Prenom ne doit pas être vide.");
		} else if (enCours.getPrenom().length() > 25) {
			message.append("\nLe Prenom est trop long.");
		}
		
		if (enCours.getModejeu() == null) {
			message.append("\nLe catégorie doit être indiquée.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (enCours.getId() == null) {
			// Insertion
			enCours.setId(daoVisiteur.inserer(enCours));
		} else {
			// modficiation
			daoVisiteur.modifier(enCours);
		}
	}

}
