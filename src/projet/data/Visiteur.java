
package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Visiteur {
	
	 
    // Données observables
   	private final Property<Integer>	id_visit		= new SimpleObjectProperty<>();
   	private final Property<String>	nom	= new SimpleObjectProperty<>();
   	private final Property<String>	prenom		= new SimpleObjectProperty<>();
   	private final Property<Integer>	id_dipl		= new SimpleObjectProperty<>();
   	private final Property<Integer>	modejeu		= new SimpleObjectProperty<>();
   	
   	
   	
 // Constructeurs
	
 		public Visiteur() {
 		}

 		public Visiteur( final int id_visit, final String nom , final String prenom, final int idDip,final int modejeu) {
 			setIdVisit(id_visit);
 			setNomV(nom);
 			setPrenomV(prenom);
 			setIdDip(idDip);
 			setModejeu(modejeu);    
 			
 		}
 		
 	// Getters et Setters

 			public final Property<Integer> idVisitProperty() {
 				return this.id_visit;
 			}

 			public final Integer getIdVisit() {
 				return this.idVisitProperty().getValue();
 			}

 			public final void setIdVisit(final Integer idVisit) {
 				this.idVisitProperty().setValue(idVisit);
 			}
 		
 			public final Property<Integer> idDipProperty() {
 				return this.id_dipl;
 			}

 			public final Integer getIdDip() {
 				return this.idDipProperty().getValue();
 			}

 			public final void setIdDip(final Integer idDip) {
 				this.idDipProperty().setValue(idDip);
 			}
 			
 			public final Property<String> nomProperty() {
 				return this.nom;
 			}

 			public final String getnom() {
 				return this.nomProperty().getValue();
 			}

 			public final void setNomV(final String nom) {
 				this.nomProperty().setValue(nom);
 			}
 			
 			public final Property<String> prenomProperty() {
 				return this.prenom;
 			}

 			public final String getprenom() {
 				return this.prenomProperty().getValue();
 			}

 			public final void setPrenomV(final String nom) {
 				this.prenomProperty().setValue(nom);
 			}   
 			
 			public final Property<Integer> ModejeuProperty() {
 				return this.modejeu;
 			}

 			public final Integer getModejeu() {
 				return this.ModejeuProperty().getValue();
 			}

 			public final void setModejeu(final Integer modejeu) {
 				this.ModejeuProperty().setValue(modejeu);
 			}
 		
            
 			
	
}
