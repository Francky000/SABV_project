package projet.model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import projet.dao.DaoCompte;
import projet.data.Compte;


public class ModelConnexion {
	
	
	// Données observables 
	
	// Vue connexion
	private final Compte			enCours = new Compte();

	// Compte connecté
	private final Property<Compte>	compteActif = new SimpleObjectProperty<>();

	
	// Autres champs
	@Inject
	private DaoCompte	daoCompte;
	

	// Getters 
	
	public Compte getEnCours() {
		return enCours;
	}
	
	public Property<Compte> compteActifProperty() {
		return compteActif;
	}
	
	public Compte getCompteActif() {
		return compteActif.getValue();
	}
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {
		enCours.setPseudo( "geek" );
		enCours.setMotDePasse( "geek" );
	}
	
	
	// Actions


	public void ouvrirSessionUtilisateur() {

		Compte compte = daoCompte.validerAuthentification(
				enCours.pseudoProperty().getValue(), enCours.motDePasseProperty().getValue() );
		
		if( compte == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
			 Platform.runLater( () -> compteActif.setValue( compte ) );
		}
	}
	

	public void fermerSessionUtilisateur() {
		compteActif.setValue( null );
	}

}
