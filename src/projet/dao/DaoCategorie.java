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


public class DaoCategorie {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	
	/*
	 libelle_ta varchar (50) NOT NULL ,
     tranche    varchar (10) NOT NULL   */
	
	// Actions

	public String inserer( Categorie categorie ) {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "{ CALL categorie_insert( ?, ? ) }";
			stmt = cn.prepareCall( sql );
			stmt.setString(	1, categorie.getLibelleta() );
			stmt.setString(	2, categorie.getTranche() );
			stmt.executeUpdate();

		
			return categorie.getLibelleta();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	/*public void modifier( Categorie categorie ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE tuto_categorie SET Libelle = ? WHERE IdCategorie =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString(	1, categorie.getLibelle() );
			stmt.setInt(	2, categorie.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}*/


	public void supprimer( String libelleta ) {   // c'est bon 

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM tranche_age WHERE libelle_ta = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setString( 1, libelleta );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Categorie retrouver( String libelleta ) { // c'est bon 

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
    
		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM tranche_age WHERE libelle_ta = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString(1, libelleta);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireCategorie( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Categorie> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM tranche_age ORDER BY libelle_ta";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Categorie> categories = new ArrayList<>();
			while (rs.next()) {
				categories.add( construireCategorie( rs ) );
			}
			return categories;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Categorie construireCategorie( ResultSet rs ) throws SQLException {
		Categorie categorie = new Categorie();
		categorie.setLibelleta( rs.getString( "libelle_ta" ) );
		categorie.setTranche( rs.getString( "tranche" ) );
		return categorie;
	}

}
