package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoCategorie;
import projet.dao.DaoDiplome;
import projet.data.Categorie;
import projet.data.Diplome;


public class ModelDiplome  {
	
	
	// Données observables 
	
	private final ObservableList<Diplome> liste = FXCollections.observableArrayList(); 
	
	private final Diplome	enCours = new Diplome();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoCategorie	daoCategorie;
    @Inject
	private DaoDiplome	daoDiplome;
    
    
	
	
	// Getters 
	
	public ObservableList<Diplome> getListe() {
		return liste;
	}
	
	public Diplome getEnCours() {
		return enCours;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.clear();
		liste.addAll( daoDiplome.listerTout() );
 	}


	// Actions
	
//	public void preparerAjouter() {
//		mapper.update( enCours, new Categorie() );
//	}
//	
	public void preparerModifier( Diplome item ) {
		mapper.update( enCours, item );
	}
	
	
//	public void validerMiseAJour() {
//
//		// Vérifie la validité des données
//		
//		StringBuilder message = new StringBuilder();
//
//		if( enCours.getIdDip() == null || enCours.getIdTr() == null ) {
//			message.append( "\nL'Id du diplome et ou Id du titre ne doit pas être vide." );
//		} else if (enCours.getscoredip() == null ) {
//			message.append("\\nLe score du diplome  ne doit pas être vide.");
//		} else if (enCours.getlibelledip() == null || enCours.getlibelledip().isEmpty()) {
//			message.append("\\nLe libelle du diplome  ne doit pas être vide.");
//		}
//		
//		if ( message.length() > 0 ) {
//			throw new ExceptionValidation( message.toString().substring(1) );
//		}
//		
//		
//		// Effectue la mise à jour
//		
//		if ( enCours.getLibelleta() == null ) {
//			// Insertion
//			enCours.setLibelleta( daoCategorie.inserer( enCours ) );
//		} else {
//			// modficiation
//			daoCategorie.modifier( enCours );
//		}
//	}
//	
	

	}
	

