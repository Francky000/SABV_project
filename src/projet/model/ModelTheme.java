package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;          
import javafx.collections.FXCollections;      
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoCompte;
import projet.dao.DaoTheme;
import projet.data.Compte;            
import projet.data.Personne;                   
import projet.data.Theme;
                                        
public class ModelTheme {

	// Données observables 
	
		private final ObservableList<Theme> liste = FXCollections.observableArrayList();   
		
		private final Theme		enCours = new Theme();
		

		// Autres champs    
	    @Inject
		private IMapper			mapper;
	    @Inject
		private DaoTheme		daoTheme;
	    
	 // Getters
		
		public ObservableList<Theme> getListe() {
			return liste;
		}              

		public Theme getEnCours() {        
			return enCours;
		}
		
		// Actualisations
		
		public void actualiserListe() {
			liste.clear();
			liste.addAll( daoTheme.listerTout() );
	 	}
		
		
		// Actions    
		
		public void preparerAjouter() {
			mapper.update( enCours, new Theme() );
		}

		
		public void preparerModifier( Theme item ) {
			mapper.update( enCours, item );
		}
		
		
		
		
		
		
	
		
		
}
