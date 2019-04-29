package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class Memo {
	
	
	// Champs
	
	private final Property<Integer>		id			= new SimpleObjectProperty<>();
	private final Property<String>		titre		= new SimpleObjectProperty<>();
	private final Property<String>		description	= new SimpleObjectProperty<>();
	private final Property<Boolean>		flagUrgent	= new SimpleObjectProperty<>( false );
	private final Property<Integer>		statut		= new SimpleObjectProperty<>();
	private final Property<Integer>		effectif	= new SimpleObjectProperty<>();
	private final Property<Double>		budget		= new SimpleObjectProperty<>();
	private final Property<LocalDate>	echeance	= new SimpleObjectProperty<>();
	private final Property<String>		cheminImage2= new SimpleObjectProperty<>();
	private final Property<Personne>	personnee	= new SimpleObjectProperty<>();

	
	// Getters & setters
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
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
	
	public final Property<String> descriptionProperty() {
		return this.description;
	}
	
	public final String getDescription() {
		return this.descriptionProperty().getValue();
	}
	
	public final void setDescription(final String description) {
		this.descriptionProperty().setValue(description);
	}
	
	public final Property<Boolean> flagUrgentProperty() {
		return this.flagUrgent;
	}
	
	public final Boolean getFlagUrgent() {
		return this.flagUrgentProperty().getValue();
	}
	
	public final void setFlagUrgent(final Boolean flagUrgent) {
		this.flagUrgentProperty().setValue(flagUrgent);
	}

	public final Property<Integer> statutProperty() {
		return this.statut;
	}

	public final Integer getStatut() {
		return this.statutProperty().getValue();
	}

	public final void setStatut(final Integer statut) {
		this.statutProperty().setValue(statut);
	}

	public final Property<Integer> effectifProperty() {
		return this.effectif;
	}

	public final Integer getEffectif() {
		return this.effectifProperty().getValue();
	}

	public final void setEffectif(final Integer effectif) {
		this.effectifProperty().setValue(effectif);
	}

	public final Property<Double> budgetProperty() {
		return this.budget;
	}

	public final Double getBudget() {
		return this.budgetProperty().getValue();
	}

	public final void setBudget(final Double budget) {
		this.budgetProperty().setValue(budget);
	}

	public final Property<LocalDate> echeanceProperty() {
		return this.echeance;
	}

	public final LocalDate getEcheance() {
		return this.echeanceProperty().getValue();
	}

	public final void setEcheance(final LocalDate echeance) {
		this.echeanceProperty().setValue(echeance);
	}

//	public final Property<Image> image1Property() {
//		return this.image1;
//	}
//
//	public final Image getImage1() {
//		return this.image1Property().getValue();
//	}
//
//	public final void setImage1(final Image image) {
//		this.image1Property().setValue(image);
//	}

	public final Property<String> cheminImage2Property() {
		return this.cheminImage2;
	}

	public final String getCheminImage2() {
		return this.cheminImage2Property().getValue();
	}

	public final void setCheminImage2(final String document) {
		this.cheminImage2Property().setValue(document);
	}

	public final Property<Personne> personneeProperty() {
		return this.personnee;
	}

	public final Personne getPersonnee() {
		return this.personneeProperty().getValue();
	}

	public final void setPersonnee(final Personne personnee) {
		this.personneeProperty().setValue(personnee);
	}

	
	// hashCode() & equals()
	
	@Override
	public int hashCode() {
		return Objects.hash( id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Memo other = (Memo) obj;
		return Objects.equals( id.getValue(), other.id.getValue() );
	}
	

}
