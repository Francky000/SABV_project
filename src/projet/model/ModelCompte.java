package projet.model;


import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoCompte;
import projet.data.Compte;


public class ModelCompte {
	
	
	// Données observables 
	
	private final ObservableList<Compte> liste = FXCollections.observableArrayList(); 
	
	private final Compte	enCours = new Compte();
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoCompte		daoCompte;
	
	
	// Getters
	
	public ObservableList<Compte> getListe() {
		return liste;
	}

	public Compte getEnCours() {
		return enCours;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.clear();
		liste.addAll( daoCompte.listerTout() );
 	}
	
	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( enCours, new Compte() );
	}

	
	public void preparerModifier( Compte item ) {
		mapper.update( enCours, item );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( enCours.getPseudo() == null || enCours.getPseudo().isEmpty() ) {
			message.append( "\nLe pseudo ne doit pas être vide." );
		} else  if ( enCours.getPseudo().length()> 25 ) {
			message.append( "\nLe pseudo est trop long : 25 maxi." );
		}
		
		if( enCours.getMotDePasse() == null || enCours.getMotDePasse().isEmpty() ) {
			message.append( "\nLe mot de passe ne doit pas être vide." );
		} else  if ( enCours.getMotDePasse().length()< 3 ) {
			message.append( "\nLe mot de passe est trop court : 3 mini." );
		} else  if ( enCours.getMotDePasse().length()> 25 ) {
			message.append( "\nLe mot de passe est trop long : 25 maxi." );
		}
		
		if( enCours.getEmail() == null || enCours.getEmail().isEmpty() ) {
			message.append( "\nL'adresse e-mail ne doit pas être vide." );
		} else  if ( enCours.getEmail().length()> 100 ) {
			message.append( "\nL'adresse e-mail est trop longue : 100 maxi." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( enCours.getId() == null ) {
			// Insertion
			enCours.setId( daoCompte.inserer( enCours ) );
		} else {
			// modficiation
			daoCompte.modifier( enCours );
		}
	}
	
	
	public void supprimer( Compte item ) {
		daoCompte.supprimer( item.getId() );
		mapper.update( enCours, UtilFX.findNext( liste, item ) );
	}

}
