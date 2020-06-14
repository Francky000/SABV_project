package projet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import fwk3il.dao.jdbc.UtilJdbc;
import projet.data.Personne;
import projet.data.Quizz;
import projet.data.Visiteur;

public class DaoVisiteur {

	// Champs       

			@Inject
			private DataSource		dataSource;
			
	// Actions       
			
			public int inserer(Visiteur visiteur)  {

				Connection			cn		= null;
				CallableStatement	stmt	= null;
				String				sql;

				try {
					cn = dataSource.getConnection();

					// Insère la personne
					sql = "{ CALL tuto_visiteur_insert( ?, ?, ?, ? ) }";
					stmt = cn.prepareCall( sql );
					stmt.setString(	1, visiteur.getnom() );
					stmt.setString(	2, visiteur.getprenom() );
					///System.out.println(visiteur.getIdDip());
					//stmt.setInt(	3,visiteur.getIdDip());
					//stmt.registerOutParameter( 3, Types.INTEGER );
					stmt.setInt(	3, visiteur.getModejeu());
					stmt.registerOutParameter( 4, Types.INTEGER );
					
					stmt.executeUpdate();

					// Récupère l'identifiant généré par le SGBD   
					visiteur.setIdVisit( stmt.getInt( 4 ) );           
			
				} catch (SQLException e) {
					throw new RuntimeException(e);  
				} finally {    
					UtilJdbc.close( stmt, cn );
				}

			
				    
				// Retourne l'identifiant
				return visiteur.getIdVisit() ;
			}
			
			  
			public void modifier(Visiteur visiteur)  {   // Ne nous sera pas utile 

				Connection			cn		= null;
				PreparedStatement	stmt	= null;
				String 				sql;

				try {
					cn = dataSource.getConnection();

					// Modifie le visiteur
					sql = "UPDATE visiteur SET nom = ?, prenom = ?, modejeu = ? WHERE id_visit =  ?";
					stmt = cn.prepareStatement( sql );
					//stmt.setInt(	1, visiteur.getIdVisit());
					stmt.setString(	1, visiteur.getnom() );
					stmt.setString(	2, visiteur.getprenom() );
					stmt.setInt(	3, visiteur.getModejeu());
					stmt.setInt(	4, visiteur.getIdVisit());
					stmt.executeUpdate();
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( stmt, cn );
				}

				
			}
			
			public void supprimer(int id_visit)  {  // Ne nous sera pas utile 

				Connection			cn		= null;
				PreparedStatement	stmt	= null;
				String 				sql;

				// Que dois t'on supprimer d'autres lorsqu'on supprime un visiteur , ses diplomes ? 
				//daoTelephone.supprimerPourPersonne( idPersonne );

				try {
					cn = dataSource.getConnection();

					// Supprime le personne
					sql = "DELETE FROM visiteur WHERE id_visit = ? ";
					stmt = cn.prepareStatement(sql);
					stmt.setInt( 1, id_visit);
					stmt.executeUpdate();

				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( stmt, cn );
				}
			}
			
			/*public Visiteur retrouver(int id_visit)  { // je dois d'abord finir avec tranche d'age 

				Connection			cn		= null;
				PreparedStatement	stmt	= null;
				ResultSet 			rs 		= null;
				String				sql;

				try {
					cn = dataSource.getConnection();

					sql = "SELECT * FROM visiteur WHERE id_visit = ?";
		            stmt = cn.prepareStatement(sql);
		            stmt.setInt(1, id_visit);
		            rs = stmt.executeQuery();

		            if ( rs.next() ) {
		                return construirePersonne(rs, null );
		            } else {
		            	return null;
		            }
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( rs, stmt, cn );
				}
			}*/


}
