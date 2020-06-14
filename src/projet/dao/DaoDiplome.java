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
import projet.data.Categorie;
import projet.data.Diplome;
import projet.data.Visiteur;


public class DaoDiplome {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	public int inserer(Diplome diplome)  {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère la personne
			sql = "{ CALL diplome_insert( ?, ?, ?, ? ) }";
			stmt = cn.prepareCall( sql );
			stmt.setString(	1,diplome.getlibelledip());
			stmt.setInt(	2, diplome.getscoredip());
			stmt.setString(	3, diplome.getlibelleta());
			stmt.registerOutParameter( 4, Types.INTEGER );
			
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD   
		diplome.setIdDip( stmt.getInt( 4 ) );           
	
		} catch (SQLException e) {
			throw new RuntimeException(e);  
		} finally {    
			UtilJdbc.close( stmt, cn );
		}

	
		    
		// Retourne l'identifiant
		return diplome.getIdDip();
	}

	public List<Diplome> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM diplome ORDER BY id_dipl";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Diplome> diplome = new ArrayList<>();
			while (rs.next()) {
				diplome.add( construireDiplome( rs ) );
			}
			return diplome;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Diplome construireDiplome( ResultSet rs ) throws SQLException {
		Diplome diplome = new Diplome();
		diplome.setIdDip( rs.getInt( 1 ) );
		diplome.setlibelleta(rs.getString( 1 ) );
		diplome.setlibelledip(rs.getString("Libelle_dipl"));
		diplome.setscoredip(rs.getInt(14));
		return diplome;
	}

}
