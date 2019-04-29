package projet.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import fwk3il.commun.context.IContext;
import fwk3il.javafx.view.FactoryController;
import fwk3il.javafx.view.IEnumView;
import fwk3il.javafx.view.ManagerGuiAbstract;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.model.ModelInfo;


public class ManagerGui extends ManagerGuiAbstract {

	
	// Champs
	
	@Inject
	private IContext	context;
    @Inject
    private ModelInfo	modelInfo;

    private BorderPane	paneMenu;

	
	// Initialisation
	
	@PostConstruct
	public void init() {

		// Définit la fabrique des contrôleurs
		factoryController =  new FactoryController( this, context::getBean );
		
	}
	
	
	// Actions

	@Override
	protected void configureStage(Stage stage) throws IOException {
		
		// Configure la scene
		paneMenu = load( getClass().getResource( "systeme/PaneMenu.fxml" ) );
		Scene scene = new Scene( paneMenu );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		// Configure le stage
		stage.setTitle( "Projet" );
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setMinHeight(300);
		stage.setMinWidth(400);
		stage.getIcons().add(new Image(getClass().getResource("icone.png").toExternalForm()));
		
		// Choisit la vue à afficher
		modelInfo.setTitre( "Projet" );
		modelInfo.setMessage( "Bienvenue dans le projet !");
		showView( EnumView.Info );
	}


	@Override
	protected void displayView( IEnumView view ) {
		paneMenu.setCenter( view.getPane() );
	}
	
}