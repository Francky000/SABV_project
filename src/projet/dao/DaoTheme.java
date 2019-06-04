package projet.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import fwk3il.dao.jdbc.UtilJdbc;
import projet.data.Personne;
import projet.data.Theme;

public class DaoTheme {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
	// Actions
		
	
		public int inserer(Theme theme)  {

			Connection			cn		= null;
			CallableStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				// Insère le theme 
				sql = "{ CALL tuto_theme_insert( ?, ? ) }";
				stmt = cn.prepareCall( sql );
				stmt.setString(	1, theme.getlibelleth() );
				stmt.registerOutParameter( 2, Types.INTEGER );
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				theme.setIdth( stmt.getInt( 2 ) );
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );         
			}
    
		
			// Retourne l'identifiant          
			return theme.getIdth();        
		}    

		
		public void modifier(Theme theme)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie le personne
				sql = "UPDATE THEME SET LIBELLE_TH = ? WHERE NUMTH =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setInt(	1, theme.getIdth() );
				stmt.setString(	2, theme.getlibelleth() );
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}

			
		}
		
		public void supprimer( int numth) {
			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "DELETE FROM theme WHERE numth = ? ";
				stmt = cn.prepareStatement( sql );
				stmt.setInt( 1, numth);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
   
		
		
		
}
