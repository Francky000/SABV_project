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


public class ModelPersonne {
	
	
	// Données observables 
	
	private final ObservableList<Personne> liste = FXCollections.observableArrayList();
	
	private final Personne		enCours = new Personne();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoPersonne			daoPersonne;
    @Inject
    private DaoMemo				daoMemo;
	
	
	// Getters 
	
	public ObservableList<Personne> getListe() {
		return liste;
	}
	
	public Personne getEnCours() {
		return enCours;
	}

	
	// Actualisations
	
	public void actualiserListe() {
		liste.clear();
		liste.addAll( daoPersonne.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( enCours, new Personne() );
	}
	

	public void preparerModifier( Personne item ) {
		mapper.update( enCours, item );
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

		
		// Effectue la mise à jour
		
		if ( enCours.getId() == null ) {
			// Insertion
			enCours.setId( daoPersonne.inserer( enCours ) );
		} else {
			// modficiation
			daoPersonne.modifier( enCours );
		}
	}
	

	public void supprimer( Personne item ) {
		
		// Vérifie qu'aucun mémo n'est rattaché à la personne
		if ( daoMemo.compterourPersonne( item.getId() ) != 0 ) {
			throw new ExceptionValidation( "Des mémos sont rattachés à cette personne." ) ;
		}

		daoPersonne.supprimer( item.getId() );
		mapper.update( enCours, UtilFX.findNext( liste, item ) );
	}
	

	public void ajouterTelephone() {
		enCours.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
		enCours.getTelephones().remove( telephone );
	}
	
}
