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


public class DaoCompte {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	//private DaoRole			daoRole;

	
	// Actions
	

	public int inserer(Compte compte)  {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le compte
			sql = "{ CALL tuto_compte_insert( ?, ?, ?, ? ) }";
			stmt = cn.prepareCall( sql );
			stmt.setString(	1, compte.getPseudo() );
			stmt.setString(	2, compte.getMotDePasse() );
			stmt.setString(	3, compte.getEmail() );
			stmt.registerOutParameter( 4, Types.INTEGER );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			compte.setId( stmt.getInt( 4 ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Insère les rôles
		//daoRole.insererPourCompte( compte );
		
		// Retourne l'identifiant
		return compte.getId();
	}

	

	public void modifier(Compte compte)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le compte
			sql = "UPDATE tuto_compte SET Pseudo = ?, MotDePasse = ?, Email = ? WHERE IdCompte =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString(	1, compte.getPseudo() );
			stmt.setString(	2, compte.getMotDePasse() );
			stmt.setString(	3, compte.getEmail() );
			stmt.setInt(	4, compte.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Modifie les rôles
		//daoRole.modifierPourCompte( compte );
	}

	

	public void supprimer(int idCompte)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		// Supprime les rôles
		//daoRole.supprimerPourCompte( idCompte );

		try {
			cn = dataSource.getConnection();

			// Supprime le compte
			sql = "DELETE FROM tuto_compte WHERE IdCompte = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idCompte );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	

	public Compte retrouver(int idCompte)  {
		
		
		
		
		
		
		

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_compte WHERE IdCompte = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setInt( 1, idCompte );
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireCompte( rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	

	public List<Compte> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_compte ORDER BY Pseudo";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Compte> comptes = new ArrayList<>();
			while ( rs.next() ) {
				comptes.add( construireCompte(rs) );
			}
			return comptes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}



	public Compte validerAuthentification(String pseudo, String motDePasse)  {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_compte WHERE Pseudo = ? AND MotDePasse = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString( 1, pseudo );
			stmt.setString( 2, motDePasse );
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireCompte( rs );			
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}



	public boolean verifierUnicitePseudo( String pseudo, int idCompte )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT COUNT(*) AS nbComptes"
				+ " FROM tuto_compte WHERE Pseudo = ? AND IdCompte <> ?";
			stmt = cn.prepareStatement( sql );
			stmt.setString(	1, pseudo );
			stmt.setInt(	2, idCompte );
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getInt( "nbComptes" ) == 0;
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Compte construireCompte( ResultSet rs ) throws SQLException {
		Compte compte = new Compte();
		compte.setId( rs.getInt( "IdCompte" ) );
		compte.setPseudo( rs.getString( "Pseudo" ) );
		compte.setMotDePasse( rs.getString( "MotDePasse" ) );
		compte.setEmail( rs.getString( "Email" ) );
		//compte.getRoles().addAll( daoRole.listerPourCompte( compte ) );
		return compte;
	}
	
}
