package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Stat_Score {
	
	// Données observables
		protected final Property<Integer>	id_statscore	= new SimpleObjectProperty<>();
		
		// Constructeurs
		
			public Stat_Score() {
			}

			public Stat_Score( final int id_statscore) {
				setIdstatscore(id_statscore);
					
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
