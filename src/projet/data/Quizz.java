package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Quizz {

	// Données observables
	private final Property<Integer>	id_qz		= new SimpleObjectProperty<>();
	private final Property<String>	titre	= new SimpleObjectProperty<>();
	private final Property<Integer>	Numth		= new SimpleObjectProperty<>();
	private final Property<Integer>	idStatScore		= new SimpleObjectProperty<>();
	private final static int	nb_ques		= 10;
	
	// Constructeurs
	
		public Quizz() {
		}

		public Quizz( final int id_qz, final String titre , final int Numth, final int idStatScore ) {
			setIdqz(id_qz);
			setTitre(titre);
			setNumth(Numth);
			setidStatScore(idStatScore );
			
		}
	

		// Getters et Setters

		public final Property<Integer> idqzProperty() {
			return this.id_qz;
		}

		public final Integer getIdqz() {
			return this.idqzProperty().getValue();
		}

		public final void setIdqz(final Integer id_qz) {
			this.idqzProperty().setValue(id_qz);
		}

		public final Property<String> titreProperty() {
			return this.titre;
		}

		public final String getTitre() {
			return this.titreProperty().getValue();
		}

		public final void setTitre(final String titre) {
			this.titreProperty().setValue(titre);
		}
		
		public final Property<Integer> NumthProperty() {
			return this.Numth;
		}

		public final Integer getNumth() {
			return this.NumthProperty().getValue();
		}
		
		public final void setNumth(final Integer Numth) {
			this.idqzProperty().setValue(Numth);
		}
		
		public final Property<Integer> idStatScoreProperty() {
			return this.idStatScore;
		}
		
		public final Integer getidStatScore() {
			return this.idStatScoreProperty().getValue();
		}
		
		public final void setidStatScore(final Integer idStatScore) {
			this.idStatScoreProperty().setValue(idStatScore);
		}
		
		// toString()
		
		@Override
		public String toString() {
			return getTitre();   
		}
		
		// hashCode() & equals()

		@Override
		public int hashCode() {
			return Objects.hash(id_qz.getValue() );
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Quizz other = (Quizz) obj;
			return Objects.equals(id_qz.getValue(), other.id_qz.getValue() );
		}  
		
		
		
		   
}
