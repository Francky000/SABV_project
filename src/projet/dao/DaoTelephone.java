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
import projet.data.Telephone;


public class DaoTelephone {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public void insererPourPersonne(Personne personne) {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "{ CALL tuto_telephone_insert( ?, ?, ?, ? ) }";
			stmt = cn.prepareCall( sql );
			for( Telephone telephone : personne.getTelephones() ) {
				stmt.setInt(	1, personne.getId() );
				stmt.setString(	2, telephone.getLibelle() );
				stmt.setString(	3, telephone.getNumero() );
				stmt.registerOutParameter( 4, Types.INTEGER );
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				telephone.setId( stmt.getInt( 4 ) );
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void modifierPourPersonne(Personne personne) {

		Connection			cn			= null;
		PreparedStatement	stmtDelete	= null;
		CallableStatement	stmtInsert	= null;
		PreparedStatement	stmtUpdate	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM tuto_telephone WHERE IdTelephone = ?";
			stmtDelete = cn.prepareStatement( sql );
			for ( Telephone telephone : listerPourPersonne(personne) ) {
				if ( ! personne.getTelephones().contains( telephone ) ) {
					stmtDelete.setInt( 1, telephone.getId() );
					stmtDelete.executeUpdate();
				}
			}

			sql = "{ CALL tuto_telephone_insert( ?, ?, ?, ? ) }";
			stmtInsert = cn.prepareCall( sql );
			sql = "UPDATE tuto_telephone SET IdPersonne = ?, Libelle = ?, Numero = ? WHERE IdTelephone = ?";
			stmtUpdate = cn.prepareStatement( sql );
			for( Telephone telephone : personne.getTelephones() ) {
				if ( telephone.getId() == null || telephone.getId() == 0 ) {
					stmtInsert.setInt( 1, personne.getId());
					stmtInsert.setString( 2, telephone.getLibelle() );
					stmtInsert.setString( 3, telephone.getNumero() );
					stmtInsert.registerOutParameter( 4, Types.INTEGER );
					stmtInsert.executeUpdate();
					// Récupère l'identifiant généré par le SGBD
					telephone.setId( stmtInsert.getInt( 4 ) );
				} else {
					stmtUpdate.setInt( 1, personne.getId());
					stmtUpdate.setString( 2, telephone.getLibelle() );
					stmtUpdate.setString( 3, telephone.getNumero() );
					stmtUpdate.setInt( 4, telephone.getId());
					stmtUpdate.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmtDelete, stmtInsert, stmtUpdate, cn );
		}
	}


	public void supprimerPourPersonne(int idPersonne) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les telephones
			sql = "DELETE FROM tuto_telephone  WHERE IdPersonne = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idPersonne );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(  stmt, cn );
		}
	}


	public List<Telephone> listerPourPersonne(Personne personne) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_telephone WHERE IdPersonne = ? ORDER BY Libelle";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, personne.getId() );
			rs = stmt.executeQuery();

			List<Telephone> telephones = new ArrayList<>();
			while (rs.next()) {
				telephones.add( construireTelephone( rs, personne ) );
			}
			return telephones;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Telephone construireTelephone( ResultSet rs, Personne personne ) throws SQLException {
		Telephone telephone = new Telephone();
		telephone.setId(rs.getInt( "IdTelephone" ));
		telephone.setLibelle(rs.getString( "Libelle" ));
		telephone.setNumero(rs.getString( "Numero" ));
		return telephone;
	}

}
