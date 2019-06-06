package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Compte  {

	
	// Données observables
	
	private final Property<Integer>	id			= new SimpleObjectProperty<>();
	private final Property<String>	pseudo		= new SimpleObjectProperty<>();
	private final Property<String>	motDePasse	= new SimpleObjectProperty<>();
	private final Property<String>	email 		= new SimpleObjectProperty<>();
	private final ObservableList<String> roles = FXCollections.observableArrayList();
	
	
	// Constructeurs
	
	public Compte() {
	}

	public Compte( int id, String pseudo, String motDePasse, String email ) {
		setId(id);
		setPseudo(pseudo);
		setMotDePasse(motDePasse);
		setEmail(email);
	}
	
	
	// Getters et Setters

	public final Property<Integer> idProperty() {
		return this.id;
	}

	public final Integer getId() {
		return this.idProperty().getValue();
	}

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}

	public final Property<String> pseudoProperty() {
		return this.pseudo;
	}

	public final String getPseudo() {
		return this.pseudoProperty().getValue();
	}

	public final void setPseudo(final String pseudo) {
		this.pseudoProperty().setValue(pseudo);
	}

	public final Property<String> motDePasseProperty() {
		return this.motDePasse;
	}

	public final String getMotDePasse() {
		return this.motDePasseProperty().getValue();
	}

	public final void setMotDePasse(final String motDePasse) {
		this.motDePasseProperty().setValue(motDePasse);
	}

	public final Property<String> emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().getValue();
	}

	public final void setEmail(final String email) {
		this.emailProperty().setValue(email);
	}

	public final ObservableList<String> getRoles() {
		return this.roles;
	}

	
	public boolean isInRole( String role ) {
		
		if ( role != null ) {
			for ( String r : roles ) {
				if ( role.equals( r ) ) {
					return true;
				}
			}
		}
		return false;
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getPseudo();
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
		Compte other = (Compte) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}
	
}
