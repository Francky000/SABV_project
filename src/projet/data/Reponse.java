package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Reponse {

	// Données observables
		private final Property<Integer>	idrt		= new SimpleObjectProperty<>();
		private final Property<String>	libellert	= new SimpleObjectProperty<>();
		private final Property<Integer>	id_ques		= new SimpleObjectProperty<>();
		private final Property<Integer>	verite		= new SimpleObjectProperty<>();
		
		// Constructeurs
		
			public Reponse() {
			}

			public Reponse( final int idrt, final String libellert , final int id_ques, final int verite ) {
				setIdrt(idrt);
				setlibellert(libellert);
				setIdques(id_ques);     
				setverite(verite);   
				
			}   
			
			// Getters et Setters

			public final Property<Integer> idrtProperty() {   
				return this.idrt;
			}

			public final Integer getIdrt() {
				return this.idrtProperty().getValue();
			}

			public final void setIdrt(final Integer idrt) {
				this.idrtProperty().setValue(idrt);
			}
			
			public final Property<String> libellertProperty() {
				return this.libellert;
			}
			
			public final String getlibellert() {
				return this.libellertProperty().getValue();
			}
			
			public final void setlibellert(final String libellert) {
				this.libellertProperty().setValue(libellert);
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
			
			public final Property<Integer> veriteProperty() {
				return this.verite;
			}
			
			public final Integer getverite() {
				return this.veriteProperty().getValue();
			}
			
			public final void setverite(final Integer verite) {
				this.veriteProperty().setValue(verite);
			}
			
			
			// toString()
			
			@Override
			public String toString() {
				return getlibellert();   
			}
			
			// hashCode() & equals()

			@Override
			public int hashCode() {
				return Objects.hash(idrt.getValue() );
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Reponse other = (Reponse) obj;
				return Objects.equals(idrt.getValue(), other.idrt.getValue() );
			}  
			

}
