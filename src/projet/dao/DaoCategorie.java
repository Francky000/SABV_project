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

	
	
	


	public List<Categorie> listerTout2() {

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
	
	public List<String> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM tranche_age ORDER BY libelle_ta";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<String> libelleta = new ArrayList<>();
			while (rs.next()) {
				libelleta.add( construireCategorie( rs ).getLibelleta() );
			}
			return libelleta;

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
