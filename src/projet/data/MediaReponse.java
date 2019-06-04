package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class MediaReponse {

	// Données observables
		private final Property<Integer>	idmr		= new SimpleObjectProperty<>();
		private final Property<String>	libellemr	= new SimpleObjectProperty<>();
		private final Property<Integer>	id_ques		= new SimpleObjectProperty<>();
		private final Property<Boolean>	verite		= new SimpleObjectProperty<>();
		
		// Constructeurs
		
			public MediaReponse() {
			}

			public MediaReponse( final int idmr, final String libellemr , final int id_ques, final boolean verite ) {
				setIdmr(idmr);
				setlibellemr(libellemr);
				setIdques(id_ques);
				setverite(verite);
				
			}   
			
			// Getters et Setters

			public final Property<Integer> idmrProperty() {
				return this.idmr;
			}

			public final Integer getIdmr() {
				return this.idmrProperty().getValue();
			}

			public final void setIdmr(final Integer idmr) {
				this.idmrProperty().setValue(idmr);
			}
			
			public final Property<String> libellemrProperty() {
				return this.libellemr;
			}
			
			public final String getlibellemr() {
				return this.libellemrProperty().getValue();
			}
			
			public final void setlibellemr(final String libellemr) {   
				this.libellemrProperty().setValue(libellemr);
			}
			
			public final Property<Integer> idquesProperty() {
				return this.id_ques;
			}
			
			public final Integer getIdques() {
				return this.idquesProperty().getValue();
			}
			
			public final void setIdques(final Integer id_ques) {
				this.idquesProperty().setValue(id_ques);
			}
			
			public final Property<Boolean> veriteProperty() {
				return this.verite;
			}
			
			public final Boolean getverite() {
				return this.veriteProperty().getValue();
			}
			
			public final void setverite(final Boolean verite) {
				this.veriteProperty().setValue(verite);
			}
			
			
			// toString()
			
			@Override
			public String toString() {
				return getlibellemr();   
			}
			
			// hashCode() & equals()

			@Override
			public int hashCode() {
				return Objects.hash(idmr.getValue() );
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				MediaReponse other = (MediaReponse) obj;
				return Objects.equals(idmr.getValue(), other.idmr.getValue() );
			}  
			

}
