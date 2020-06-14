package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Diplome {

	/*ID_DIPL NUMBER(*,0), 
	LIBELLE_DIPL VARCHAR2(100 BYTE), 
	SCORE_DIPL NUMBER(*,0), 
	ID_TRANCHE_AGE NUMBER(*,0) */
	
	private final Property<Integer>	id_dip		= new SimpleObjectProperty<>();
	private final Property<String>	libelleta		= new SimpleObjectProperty<>();
	private final Property<String>	libelle_dip	= new SimpleObjectProperty<>();
	private final Property<Integer>	score_dip		= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Diplome() {
	}
	
	public Diplome( int id_dip, String libelleta, String libelle_dip, int score_dip ) {
		setIdDip(id_dip);
		setlibelleta(libelleta);
		setlibelledip(libelle_dip);
		setscoredip(score_dip);
	}
	
	// Getters & setters

		public final Property<Integer> id_dipProperty() {
			return this.id_dip;
		}

		public final Integer getIdDip() {
			return this.id_dipProperty().getValue();
		}

		public final void setIdDip(final Integer id_dip) {
			this.id_dipProperty().setValue(id_dip);
		}
		
		public final Property<String> libelletaProperty() {
			return this.libelleta;
		}

		public final String getlibelleta() {
			return this.libelletaProperty().getValue();
		}

		public final void setlibelleta(final String libelleta) {
			this.libelletaProperty().setValue(libelleta);
		}
		
		public final Property<String> libelledipProperty() {
			return this.libelle_dip;
		}

		public final String getlibelledip() {
			return this.libelledipProperty().getValue();
		}

		public final void setlibelledip(final String  libelledip) {
			this.libelledipProperty().setValue(libelledip);
		}
		
		public final Property<Integer> scoredipProperty() {
			return this.score_dip;
		}

		public final Integer getscoredip() {
			return this.scoredipProperty().getValue();
		}

		public final void setscoredip(final Integer id_dip) {
			this.scoredipProperty().setValue(id_dip);
		}
		
		// toString()
		
		@Override
		public String toString() {
			return getlibelledip();
		}
		   
		
		// hashCode() & equals()   

		@Override
		public int hashCode() {
			return Objects.hash(id_dip.getValue() );
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Diplome other = (Diplome) obj;
			return Objects.equals(id_dip.getValue(), other.id_dip.getValue() );
		}
		
		
	
	
}
