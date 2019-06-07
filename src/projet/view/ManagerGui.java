package projet.view;

import java.io.IOException;

import fwk3il.commun.context.IContext;
import fwk3il.javafx.view.IEnumView;
import fwk3il.javafx.view.ManagerGuiClassic;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ManagerGui extends ManagerGuiClassic {

	
	// Champs

    private BorderPane		paneMenu;


	// Constructeur
	
	public ManagerGui( IContext context ) {
		super ( context );
	}

	
	// Actions

	@Override
	protected void configureStage(Stage stage) throws IOException {
		
		// Configure la scene
		paneMenu = load( getClass().getResource( "systeme/PaneMenu.fxml" ) );
		Scene scene = new Scene( paneMenu );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		// Configure le stage
		stage.setTitle( "Jeu de quizz" );
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		stage.getIcons().add(new Image(getClass().getResource("logosabv.png").toExternalForm()));
		
		// Choisit la vue à afficher
		showView( EnumView.Menu);
	}


	@Override
	protected void displayView( IEnumView view ) {
		paneMenu.setCenter( view.getPane() );
	}
	
}