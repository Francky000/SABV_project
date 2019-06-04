
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
import projet.data.Parcours;
import projet.data.Theme;
import fwk3il.dao.jdbc.UtilJdbc;


public class DaoParcours {    

	// Champs

			@Inject
			private DataSource		dataSource;
			
			
			/*  num_p     
		        libelle_p  */
			public int inserer(Parcours parcours)  {

				Connection			cn		= null;
				CallableStatement	stmt	= null;
				String				sql;

				try {
					cn = dataSource.getConnection();

					// Insère le theme 
					sql = "{ CALL parcours_insert( ?, ? ) }";
					stmt = cn.prepareCall( sql );
					stmt.setInt(1, parcours.getNump());
					stmt.setString(	2, parcours.getLibellep());
					
					stmt.executeUpdate();

				
			
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( stmt, cn );         
				}
	    
			
				// Retourne l'identifiant          
				return parcours.getNump() ;       
			}    
			
			public List<String> listParcours( ) {

				Connection			cn		= null;
				PreparedStatement	stmt	= null;
				ResultSet 			rs 		= null;
				String				sql;

				try {
					cn = dataSource.getConnection();

					sql = "SELECT libelle_p FROM parcours ";
					stmt = cn.prepareStatement(sql);
					rs = stmt.executeQuery();

					List<String> parc = new ArrayList<>();
					while (rs.next()) {
						parc.add( rs.getString("libelle_p") );
										}
					return parc;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( rs, stmt, cn );
				}
			}
			
			public List<String> listQuizz(Parcours p) {              

				Connection			cn		= null;       
				PreparedStatement	stmt	= null;
				ResultSet 			rs 		= null;
				String				sql;

				try {
					cn = dataSource.getConnection();

					sql = "SELECT q.titre FROM quizz q INNER JOIN posseder p on p.id_qz = q.id_qz" + 
							"INNER JOIN parcours pa ON pa.num_p = p.num_p " + 
							"WHERE pa.num_p = ?  ORDER BY q.titre ;";
					stmt = cn.prepareStatement(sql);
					stmt.setInt( 1, p.getNump() );
					rs = stmt.executeQuery();

					List<String> quizz = new ArrayList<>();
					while (rs.next()) {
						quizz.add( rs.getString("q.titre") );
										}
					return quizz;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					UtilJdbc.close( rs, stmt, cn );
				}
			}
	
}
