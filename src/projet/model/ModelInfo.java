package projet.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class ModelInfo {
	
	
	// Données observables 
	
	private final Property<String>	titre	= new SimpleObjectProperty<>();
	private final Property<String>	message	= new SimpleObjectProperty<>();
	
	

	// Getters & setters 

	public final Property<String> titreProperty() {
		return this.titre;
	}
	
	public final String getTitre() {
		return this.titreProperty().getValue();
	}
	
	public final void setTitre(final String titre) {
		this.titreProperty().setValue(titre);
	}
	
	public final Property<String> messageProperty() {
		return this.message;
	}
	
	public final String getMessage() {
		return this.messageProperty().getValue();
	}
	
	public final void setMessage(final String message) {
		this.messageProperty().setValue(message);
	}
	
}
