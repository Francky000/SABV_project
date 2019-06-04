package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Categorie  { // Est tranche d'âge
	

	// Données observables
	
	private final Property<String>	libelle_ta	= new SimpleObjectProperty<>();
	private final Property<String>	tranche		= new SimpleObjectProperty<>();
	
	
	/* libelle_ta varchar (50) NOT NULL ,
     tranche    varchar (10) NOT NULL*/
	
	// Constructeurs
	
	public Categorie() {
	}

	public Categorie( final String libelle_ta, final String tranche ) {
		setLibelleta(libelle_ta);
		setTranche(tranche);
	}
	
	
	// Getters et Setters

	public final Property<String> libelletaProperty() {
		return this.libelle_ta; 
	}

	public final String getLibelleta() {
		return this.libelletaProperty().getValue();
	}

	public final void setLibelleta(final String libelleta) {
		this.libelletaProperty().setValue(libelleta);
	}

	public final Property<String> trancheProperty() {
		return this.tranche;
	}

	public final String getTranche() {
		return this.trancheProperty().getValue();
	}

	public final void setTranche(final String tranche) {
		this.trancheProperty().setValue(tranche);
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getLibelleta();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(getLibelleta() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		return Objects.equals(getLibelleta(), other.getLibelleta() );
	}
	
}

