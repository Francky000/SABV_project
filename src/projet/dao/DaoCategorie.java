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

	
	// Actions

	public int inserer( Categorie categorie ) {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "{ CALL tuto_categorie_insert( ?, ? ) }";
			stmt = cn.prepareCall( sql );
			stmt.setString(	1, categorie.getLibelle() );
			stmt.registerOutParameter( 2, Types.INTEGER );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			categorie.setId( stmt.getInt( 2 ) );
			return categorie.getId();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void modifier( Categorie categorie ) {

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
	}


	public void supprimer( int idCategorie ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM tuto_categorie WHERE IdCategorie = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idCategorie );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Categorie retrouver( int idCategorie ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM tuto_categorie WHERE IdCategorie = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idCategorie);
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
			sql = "SELECT * FROM tuto_categorie ORDER BY Libelle";
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
		categorie.setId( rs.getInt( "IdCategorie" ) );
		categorie.setLibelle( rs.getString( "Libelle" ) );
		return categorie;
	}

}
