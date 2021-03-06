package projet;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.mapstruct.factory.Mappers;

import fwk3il.commun.context.ContextStandard;
import fwk3il.commun.context.IContext;
import fwk3il.dao.jdbc.DataSourceSingleConnection;
import fwk3il.javafx.view.IManagerGui;
import projet.commun.IMapper;
import projet.dao.DaoVisiteur;
import projet.data.Visiteur;
import projet.view.ManagerGui;


public class MainProjet {

	
	// main()
	
	public static void main(String[] args) {   

		
		try {

			// JDBC - DataSource
			DataSource dataSource = new DataSourceSingleConnection( MainProjet.class.getResourceAsStream( "/META-INF/jdbc.properties" ) );

			// Context
			IContext context = new ContextStandard();
			context.addBean( context );
			context.addBean( dataSource );
			context.addBean( Mappers.getMapper( IMapper.class ) );

			// IManagerGui
			IManagerGui managerGui = new ManagerGui( context );
			
			// Programme la fermeture des ressources
			managerGui.addRessourcesToClose( dataSource );
			
			// Démarre l'application
			managerGui.launch(); 
			
			
			// Visiteur visiteur = new Visiteur(6,"bobo","vanel",1,2);  DaoVisiteur
		//	 daovisit = context.getBean(DaoVisiteur.class); daovisit.inserer(visiteur);
			 
			
			
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Impossible de démarrer l'application.", "", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		System.exit(0);
    }
  
}