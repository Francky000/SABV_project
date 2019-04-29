package projet.data;

import java.util.Objects;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personne {


	// Données observables
	
	private final Property<Integer>		id			= new SimpleObjectProperty<>();
	private final Property<String>		nom	 		= new SimpleObjectProperty<>();
	private final Property<String>		prenom		= new SimpleObjectProperty<>();
	private final Property<Categorie>	categorie	= new SimpleObjectProperty<>();
	private final ObservableList<Telephone>	telephones	= FXCollections.observableArrayList(
			t ->  new Observable[] { t.libelleProperty(), t.numeroProperty() } 
		);
	
	
	// Constructeurs
	
	public Personne() {
	}
	
	public Personne( int id, String nom, String prenom, Categorie categorie ) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setCategorie(categorie);
	}
	
	
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
	
	public final Property<String> nomProperty() {
		return this.nom;
	}
	
	public final java.lang.String getNom() {
		return this.nomProperty().getValue();
	}
	
	public final void setNom(final java.lang.String nom) {
		this.nomProperty().setValue(nom);
	}
	
	public final Property<String> prenomProperty() {
		return this.prenom;
	}
	
	public final java.lang.String getPrenom() {
		return this.prenomProperty().getValue();
	}
	
	public final void setPrenom(final java.lang.String prenom) {
		this.prenomProperty().setValue(prenom);
	}

	public final Property<Categorie> categorieProperty() {
		return this.categorie;
	}

	public final projet.data.Categorie getCategorie() {
		return this.categorieProperty().getValue();
	}

	public final void setCategorie(final projet.data.Categorie categorie) {
		this.categorieProperty().setValue(categorie);
	}

	public ObservableList<Telephone> getTelephones() {
		return telephones;
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}
	
}
