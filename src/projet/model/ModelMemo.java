package projet.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import projet.commun.IMapper;
import projet.dao.DaoMemo;
import projet.data.Memo;


public class ModelMemo  {
	
	
	// Données observables 
	
	private final ObservableList<Memo> liste = FXCollections.observableArrayList(); 
	
	private final Memo	enCours = new Memo();
	
	private final Property<Image>	image1 = new SimpleObjectProperty<>();
	private final Property<Image>	image2 = new SimpleObjectProperty<>();

	
	// Données courante
	
	private Image	image1Temoin;
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoMemo			daoMemo;
	
    
    // Initialisation
    
    @PostConstruct
    public void init() {
    	
    	enCours.cheminImage2Property().addListener( (x,y,z) -> {
    		if ( enCours.getCheminImage2() == null ) {
    			image2.setValue( null );
    		} else {
				try {
					image2.setValue( new Image( new FileInputStream( enCours.getCheminImage2() ) ) );
				} catch (FileNotFoundException e1) {
					throw new RuntimeException(e1);
				}
    		}
    	});
    }
	
	// Getters 
	
	public ObservableList<Memo> getListe() {
		return liste;
	}
	
	public Memo getEnCours() {
		return enCours;
	}

	public final Property<Image> image1Property() {
		return this.image1;
	}

	public final Property<Image> image2Property() {
		return this.image2;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.clear();
		liste.addAll( daoMemo.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( enCours, new Memo() );
		image1Temoin = null;
		image1.setValue(null);
	}
	
	public void preparerModifier( Memo item ) {
		mapper.update( enCours, item );
		image1Temoin = daoMemo.retrouverImage1( item.getId() );
		image1.setValue(image1Temoin);
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( enCours.getTitre() == null || enCours.getTitre().isEmpty() ) {
			message.append( "\nLe titre ne doit pas être vide." );
		} else  if ( enCours.getTitre().length()> 25 ) {
			message.append( "\nLe titre est trop long : 50 maxi." );
		}

		if( enCours.getEffectif() != null ) {
			if ( enCours.getEffectif() < 0  ) {
				message.append( "\nL'effectif ne peut pas être inféireur à zéro." );
			} else  if ( enCours.getEffectif() > 1000 ) {
				message.append( "\nEffectif trop gradn : 1000 maxi." );
			}
		}

		if( enCours.getBudget() != null ) {
			if ( enCours.getBudget() < 0  ) {
				message.append( "\nLe budget ne peut pas être inféireur à zéro." );
			} else  if ( enCours.getBudget() > 1000000 ) {
				message.append( "\nBudget trop grand : 1 000 000 maxi." );
			}
		}
		if( enCours.getEcheance() != null ) {
			if ( enCours.getEcheance().isBefore( LocalDate.of( 2000, 1, 1) ) 
					|| enCours.getEcheance().isAfter( LocalDate.of( 2099, 12, 31) )  ) {
				message.append( "\nLa date d'échéance doit être compirse entre la 01/01/2000 et le 31/12/2099." );
			}
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( enCours.getId() == null ) {
			// Insertion
			enCours.setId( daoMemo.inserer( enCours ) );
		} else {
			// modficiation
			daoMemo.modifier( enCours );
		}
		
		if ( image1Temoin != image1.getValue() ) {
			daoMemo.modifierImage1( enCours.getId(), image1.getValue() );
		}
	}
	
	
	public void supprimer( Memo item ) {
		
		daoMemo.supprimer( item.getId() );
		mapper.update( enCours, UtilFX.findNext( liste, item ) );
	}
	
}
