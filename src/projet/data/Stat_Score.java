package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Stat_Score {
	
	// Données observables
	
		protected final Property<Integer>	id_statscore	= new SimpleObjectProperty<>();
		protected final Property<String>	affiche	= new SimpleObjectProperty<>();
		
		
	/*    protected final Property<Float>	    valeur_min 	    = new SimpleObjectProperty<>();
	    protected final Property<Float>	    valeur_max 	    = new SimpleObjectProperty<>();
	    protected final Property<Float>	    valeur_moy 	    = new SimpleObjectProperty<>();*/
	    
		
		// Constructeurs
		
			public Stat_Score() {
			}

			public Stat_Score( final int id_statscore/*,final String affich*/ ) {
				setIdstatscore(id_statscore);
				//setAffich(affich);
			/*	setValmin(valeur_min);
				setValmax(valeur_max);
				setValmoy(valeur_moy); */
				
					
			}
			
			// Getters & setters

			public final Property<Integer> idstatscoreProperty() {
				return this.id_statscore;
			}

			public final Integer getIdstatscore() {
				return this.idstatscoreProperty().getValue();
			}

			public final void setIdstatscore(final Integer id_statscore) {
				this.idstatscoreProperty().setValue(id_statscore);
			}
			
			public final Property<String> affichProperty() {
				return this.affiche;
			}

			public final String getAffich() {
				return this.affichProperty().getValue();
			}

			public final void setAffich(final String affich) {
				this.affichProperty().setValue(affich);
			}

			/*
			
	        public final Property<Float> valminProperty() {
				return this.valeur_min;
			}

			public final Integer getvalmin() {
				return this.valminProperty().getValue();
			}

			public final void setValmin(final float valmin) {
				this.valminProperty().setValue(valmin);
			}
			
			public final Property<Float> valmaxProperty() {
				return this.valeur_max;
			}

			public final Integer getvalmax() {
				return this.valmaxProperty().getValue();
			}

			public final void setValmax(final float valmax) {
				this.valmaxProperty().setValue(valmax);
			}
			
			public final Property<Float> valmoyProperty() {
				return this.valeur_moy;
			}

			public final Integer getvalmoy() {
				return this.valmoyProperty().getValue();
			}

			public final void setValmoy(final float valmoy) {
				this.valmoyProperty().setValue(valmoy);
			}
			
			
			
		/*	// toString()
			
			@Override
			public String toString() {
				return getTitre();   
			} */
			
			// hashCode() & equals()

			@Override
			public int hashCode() {
				return Objects.hash(id_statscore.getValue() );
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Stat_Score other = (Stat_Score) obj;
				return Objects.equals(id_statscore.getValue(), other.id_statscore.getValue() );
			}  
			
			
			
			
}
