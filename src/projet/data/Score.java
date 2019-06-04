package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Score {
	
	// Données observables
	
	private final Property<Integer>		id_score			= new SimpleObjectProperty<>();
	private final Property<Float>	 	valeur_score	= new SimpleObjectProperty<>();
	private final Property<Integer>	    id_qz		= new SimpleObjectProperty<>();
	private final Property<Integer>	    id_visit 	= new SimpleObjectProperty<>(); 
	private final Property<Integer>	    id_stat_score	= new SimpleObjectProperty<>(); 
	
	
	// Constructeurs
	
		public Score() {   
		}   
		
		public Score( int id_score,float valeur_score,int id_qz, int id_visit) {
			setIdScore(id_score);
			setScore(valeur_score);
			setIdqz(id_qz);
			setIdvisit(id_visit);
		}
		
		// Getters & setters

		public final Property<Integer> idScoreProperty() {
			return this.id_score;
		}

		public final Integer getIdScore() {
			return this.idScoreProperty().getValue();
		}

		public final void setIdScore(final Integer id_score) {
			this.idScoreProperty().setValue(id_score);
		}
		
		public final Property<Float> ScoreProperty() {
			return this.valeur_score;
		}

		public final Float getScore() {
			return this.ScoreProperty().getValue();
		}

		public final void setScore(final Float valeur) {
			this.ScoreProperty().setValue(valeur);
		}
		
		public final Property<Integer> idqzProperty() {
			return this.id_qz;
		}

		public final Integer getIdqz() {
			return this.idqzProperty().getValue();
		}

		public final void setIdqz(final Integer idqz) {
			this.idqzProperty().setValue(idqz);
		}
		
		public final Property<Integer> idvisitProperty() {
			return this.id_visit;
		}

		public final Integer getIdvisit() {
			return this.idvisitProperty().getValue();
		}

		public final void setIdvisit(final Integer idvisit) {
			this.idvisitProperty().setValue(idvisit);
		}
		
	
		// hashCode() & equals()

		@Override
		public int hashCode() {
			return Objects.hash(id_score.getValue() );
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Score other = (Score) obj;
			return Objects.equals(id_score.getValue(), other.id_score.getValue() );
		}  
		
		
}
