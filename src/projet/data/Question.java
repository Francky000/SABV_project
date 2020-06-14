package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Question {
	
	// Données observables
	private final Property<Integer>	Id_ques		= new SimpleObjectProperty<>();
	private final Property<String>	libelle	= new SimpleObjectProperty<>();
	private final Property<String>	bulle_info	= new SimpleObjectProperty<>();
	private final Property<String>	indice	= new SimpleObjectProperty<>();
	private final Property<Integer>	numth	= new SimpleObjectProperty<>();
	
	
	

	// Constructeurs
	    
		public Question() {   
		}

		public Question( final int Id_ques, final String libelle , final String bulle_info, final String indice,final int numth ) {
			setIdques(Id_ques);
			setlibelle(libelle);
			setBulleInfo(bulle_info);
			setIndice(indice );
			setNumth(numth);
			
		}

		// Getters et Setters

		public final Property<Integer> IdquesProperty() {
			return this.Id_ques;
		}
		
		public final Integer getIdques() {
			return this.IdquesProperty().getValue();
		}

		public final void setIdques(final Integer id_ques) {
			this.IdquesProperty().setValue(id_ques);
		}
		
		public final Property<String> libelleProperty() {
			return this.libelle;
		}
		
		public final String getlibelle() {
			return this.libelleProperty().getValue();
		}
		
		public final void setlibelle(final String libelle) {
			this.libelleProperty().setValue(libelle);
		}
		
		public final Property<String> BulleInfoProperty() {
			return this.bulle_info;
		}
		
		public final String getBulleInfo() {
			return this.BulleInfoProperty().getValue();
		}
		
		public final void setBulleInfo(final String bulle_info) {
			this.BulleInfoProperty().setValue(bulle_info);
		}
		
		public final Property<String> IndiceProperty() {
			return this.indice;
		}
		
		public final String getIndice() {
			return this.IndiceProperty().getValue();
		}
		
		public final void setIndice(final String indice) {
			this.IndiceProperty().setValue(indice);
		}
		
		public final Property<Integer> NumthProperty() {
			return this.numth;
		}
		
		public final Integer getNumth() {
			return this.NumthProperty().getValue();
		}

		public final void setNumth(final Integer numth) {
			this.NumthProperty().setValue(numth);
		}
		
		// toString()
		
				@Override
				public String toString() {
					return getlibelle();   
				}
		
		// hashCode() & equals()

				@Override
				public int hashCode() {
					return Objects.hash(Id_ques.getValue() );
				}
		
				@Override
				public boolean equals(Object obj) {
					if (this == obj)
						return true;
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Question other = (Question) obj;
					return Objects.equals(Id_ques.getValue(), other.Id_ques.getValue() );
				}  
		
}
