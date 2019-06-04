package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Stat_ModeSimple extends Stat_Score {

	
	// Données observables
			private final Property<Double>	min	= new SimpleObjectProperty<>();
			private final Property<Double>	max	= new SimpleObjectProperty<>();
			private final Property<Double>	moy	= new SimpleObjectProperty<>();
			
	// Constructeurs
	
		public Stat_ModeSimple() {
		}
		
		public Stat_ModeSimple( int idstatscore, double min, double max, double moy ) {
			super(idstatscore);
			setmin(min);
			setmax(max);
			setmoy(moy);
			this.setAffiche();
			
		}
		
		// Getters & setters

		public final Property<Double> minProperty() {
			return this.min;
		}

		public final Double getmin() {
			return this.minProperty().getValue();
		}

		public final void setmin(final Double min) {
			this.minProperty().setValue(min);
		}
		
		public final Property<Double> maxProperty() {
			return this.max;
		}

		public final Double getmax() {
			return this.maxProperty().getValue();
		}

		public final void setmax(final Double max) {
			this.maxProperty().setValue(max);
		}
		public final Property<Double> moyProperty() {
			return this.moy;
		}

		public final Double getmoy() {
			return this.moyProperty().getValue();
		}

		public final void setmoy(final Double moy) {
			this.moyProperty().setValue(moy);
		}
		
		public final Property<String> afficheProperty() {
			return this.affiche;
		}
		
		public final void setAffiche() {
			this.afficheProperty().setValue( String.format("Id_Statscore = %d, score_min = %f, score_max = %f "
					+ "score_moy = %f",this.id_statscore,this.min,this.max,this.moy));
		}	
}
