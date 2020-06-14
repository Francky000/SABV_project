package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Theme {

	  // Données observables

		private final Property<Integer>	Numth	= new SimpleObjectProperty<>();
		private final Property<String>	libelleth = new SimpleObjectProperty<>();
		
	// Constructeurs
		
			public Theme() {
			}

			public Theme( final int Numth , final String libelleth ) {

				setNumth(Numth);
				setlibelle(libelleth);
		
				
			}
			
	// Getters et Setters

			
			public final Property<Integer> NumthProperty() {
				return this.Numth;
			}
			
			
			public final Integer getNumth() {
				return this.NumthProperty().getValue();
			}
			
			public final void setNumth(final Integer Numth) {
				this.NumthProperty().setValue(Numth);
			}

			public final Property<String> libellethProperty() {
				return this.libelleth;
			}     
			
			public final void setlibelle(final String libelleth) {
				this.libellethProperty().setValue(libelleth);
			}
			
			public final String getlibelleth() {
				return this.libellethProperty().getValue();
			}
			

			// toString()
			
			@Override
			public String toString() {
				return getlibelleth();   
			}
			
			// hashCode() & equals()

			@Override
			public int hashCode() {
				return Objects.hash(Numth.getValue() );
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Theme other = (Theme) obj;
				return Objects.equals(Numth.getValue(), other.Numth.getValue() );
			}  
			
			
		
		
	 
}
