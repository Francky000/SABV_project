package projet;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class MainProjet extends Application {
	
	///private static ModelLabyrinthe modelLabyrinthe =new  ModelLabyrinthe();
	private static Scene scene;
	
//	public static ModelLabyrinthe getModelLabyrinthe() {
//		return modelLabyrinthe;
//	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Vue.fxml"));
			 scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showViewMenu() throws IOException{
		Pane pane=FXMLLoader.load(MainProjet.class.getResource("Vue2.fxml"));
		scene.setRoot(pane);
	}
	
	public static void showViewQuizz() throws IOException{
		Pane pane=FXMLLoader.load(MainProjet.class.getResource("Vue.fxml"));
		scene.setRoot(pane);
	}
	
//	public static void showViewSolution() throws IOException{
//		Pane pane=FXMLLoader.load(Main.class.getResource("ViewSolution.fxml"));
//		scene.setRoot(pane);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}