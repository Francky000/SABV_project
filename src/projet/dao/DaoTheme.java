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
import projet.data.Compte;
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
				theme.setNumth( stmt.getInt( 2 ) );
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );         
			}
    
		
			// Retourne l'identifiant          
			return theme.getNumth();        
		}    

		
		
   
		public List<String> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM theme ORDER BY numth";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<String> libelletheme = new ArrayList<>();
				while ( rs.next() ) {
					libelletheme.add( construireTheme(rs).getlibelleth() );
				}
				return libelletheme;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

		// Méthodes auxiliaires
		
		private Theme construireTheme( ResultSet rs ) throws SQLException {
			Theme theme = new Theme();
			theme.setNumth( rs.getInt( "numth" ) );
			theme.setlibelle( rs.getString( "libelle_th" ) );
			
			//theme.getRoles().addAll( daoRole.listerPourCompte( compte ) );
			return theme;
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
				stmt.setInt(	1, theme.getNumth() );
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
		
		public int valeurNumth(String libelleth)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				sql = "select numth from theme where libelle_th =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(	1, libelleth );
				rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("numth");
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close(rs, stmt, cn );
			}  

			
		}
		
		
}
