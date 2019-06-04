package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Parcours {

	// Données observables
		private final Property<Integer>	num_p		= new SimpleObjectProperty<>();
		private final Property<String>	libelle_p	= new SimpleObjectProperty<>();
		
		// Constructeurs
		
			public Parcours() {  
			}

			public Parcours( final int num_p, final String libelle_p ) {
				setNump(num_p);
				setLibellep(libelle_p);
				
			}
			
			// Getters et Setters

			public final Property<Integer> numpProperty() {
				return this.num_p;
			}

			public final Integer getNump() {
				return this.numpProperty().getValue();
			}

			public final void setNump(final Integer nump) {
				this.numpProperty().setValue(nump);
			}
			
			public final Property<String> libellepProperty() {
				return this.libelle_p;      
			}

			public final String getLibellep() {
				return this.libellepProperty().getValue();
			}

			public final void setLibellep(final String libelle) {
				this.libellepProperty().setValue(libelle);
			}
			
			
			

	
}
