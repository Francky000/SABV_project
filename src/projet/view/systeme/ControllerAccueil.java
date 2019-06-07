package projet.view.systeme;

import java.io.IOException;

import javax.inject.Inject;

import fwk3il.javafx.view.IManagerGui;
import javafx.application.Platform;
import projet.view.EnumView;
//import projet.view.ManagerGui;

public class ControllerAccueil {
	@Inject
	private IManagerGui  managerGui ;

//private void showView(EnumView solution) {
//	// TODO Auto-generated method stub
//	
//}
	
public void doMenu()  throws IOException{
	managerGui.showView(EnumView.Menu);
}

public void doQuizz() throws IOException{
	managerGui.showView(EnumView.Quizz);
}

public void doQuitter() {
	managerGui.exit();
}

}