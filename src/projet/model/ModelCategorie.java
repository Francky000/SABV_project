package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoCategorie;
import projet.dao.DaoPersonne;
import projet.data.Categorie;


public class ModelCategorie  {
	
	
	// Données observables 
	
	private final ObservableList<Categorie> liste = FXCollections.observableArrayList(); 
	
	private final Categorie	enCours = new Categorie();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoCategorie	daoCategorie;
    @Inject
	private DaoPersonne		daoPersonne;
	
	
	// Getters 
	
	public ObservableList<Categorie> getListe() {
		return liste;
	}
	
	public Categorie getEnCours() {
		return enCours;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.clear();
		liste.addAll( daoCategorie.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( enCours, new Categorie() );
	}
	
	public void preparerModifier( Categorie item ) {
		mapper.update( enCours, item );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( enCours.getLibelle() == null || enCours.getLibelle().isEmpty() ) {
			message.append( "\nLe libellé ne doit pas être vide." );
		} else  if ( enCours.getLibelle().length()> 25 ) {
			message.append( "\nLe libellé est trop long : 25 maxi." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( enCours.getId() == null ) {
			// Insertion
			enCours.setId( daoCategorie.inserer( enCours ) );
		} else {
			// modficiation
			daoCategorie.modifier( enCours );
		}
	}
	
	
	public void supprimer( Categorie item ) {
		
		// Vérifie que la catégorie est vide
		if ( daoPersonne.compterourCategorie( item.getId() ) != 0 ) {
			throw new ExceptionValidation( "La catégorie n'est pas vide." ) ;
		}
		
		daoCategorie.supprimer( item.getId() );
		mapper.update( enCours, UtilFX.findNext( liste, item ) );
	}
	
}
