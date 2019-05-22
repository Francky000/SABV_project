package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import fwk3il.dao.jdbc.UtilJdbc;
import projet.data.Compte;


public class DaoRole {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public void insererPourCompte( Compte compte )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO tuto_role (IdCompte, Role) VALUES (?,?)";
			stmt = cn.prepareStatement( sql );
			for( String role : compte.getRoles() ) {
				stmt.setInt(	1, compte.getId() );
				stmt.setString(	2, role );
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
			
		}
	}

	
	public void modifierPourCompte( Compte compte )  {

		Connection			cn			= null;
		PreparedStatement	stmtDelete	= null;
		PreparedStatement	stmtInsert	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM tuto_role WHERE IdCompte = ?";
			stmtDelete = cn.prepareStatement( sql );
			stmtDelete.setInt(	1, compte.getId() );
			stmtDelete.executeUpdate();

			sql = "INSERT INTO tuto_role (IdCompte, Role) VALUES (?,?)";
			stmtInsert = cn.prepareStatement( sql );
			for( String role : compte.getRoles() ) {
				stmtInsert.setInt(	1, compte.getId() );
				stmtInsert.setString(	2, role );
				stmtInsert.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmtDelete, stmtInsert, cn );
		}
	}


	public void supprimerPourCompte( int idCompte ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les roles
			sql = "DELETE FROM tuto_role  WHERE IdCompte = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idCompte );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public List<String> listerPourCompte( Compte compte ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_role WHERE IdCompte = ? ORDER BY Role";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, compte.getId() );
			rs = stmt.executeQuery();

			List<String> roles = new ArrayList<>();
			while (rs.next()) {
				roles.add( rs.getString("Role") );
			}
			return roles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

}
