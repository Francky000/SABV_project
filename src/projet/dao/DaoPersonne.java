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

import fwk3il.dao.jdbc.UtilJdbc;
import projet.data.Categorie;
import projet.data.Personne;


public class DaoPersonne {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoTelephone	daoTelephone;
	@Inject
	private DaoCategorie	daoCategorie;

	
	// Actions
	

	public int inserer(Personne personne)  {

		Connection			cn		= null;
		CallableStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le personne
			sql = "{ CALL tuto_personne_insert( ?, ?, ?, ? ) }";
			stmt = cn.prepareCall( sql );
			stmt.setInt(	1, personne.getCategorie().getId() );
			stmt.setString(	2, personne.getNom() );
			stmt.setString(	3, personne.getPrenom() );
			stmt.registerOutParameter( 4, Types.INTEGER );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			personne.setId( stmt.getInt( 4 ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Insère les telephones
		daoTelephone.insererPourPersonne( personne );
		
		// Retourne l'identifiant
		return personne.getId();
	}

	
	public void modifier(Personne personne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le personne
			sql = "UPDATE tuto_personne SET IdCategorie = ?, Nom = ?, Prenom = ? WHERE IdPersonne =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(	1, personne.getCategorie().getId() );
			stmt.setString(	2, personne.getNom() );
			stmt.setString(	3, personne.getPrenom() );
			stmt.setInt(	4, personne.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Modifie les telephones
		daoTelephone.modifierPourPersonne( personne );
	}

	
	public void supprimer(int idPersonne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		// Supprime les telephones
		daoTelephone.supprimerPourPersonne( idPersonne );

		try {
			cn = dataSource.getConnection();

			// Supprime le personne
			sql = "DELETE FROM tuto_personne WHERE IdPersonne = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setInt( 1, idPersonne );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Personne retrouver(int idPersonne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_personne WHERE IdPersonne = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setInt(1, idPersonne);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construirePersonne(rs, null );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Personne> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM tuto_personne ORDER BY Nom, Prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Personne> personnes = new ArrayList<>();
			Map<Integer, Categorie> mapCategorie = new HashMap<>();
			while (rs.next()) {
				personnes.add( construirePersonne(rs, mapCategorie) );
			}
			return personnes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

    
    public int compterourCategorie(int idCategorie) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM tuto_personne WHERE IdCategorie = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setInt( 1, idCategorie );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
	
	
	// Méthodes auxiliaires
	
	private Personne construirePersonne( ResultSet rs, Map<Integer, Categorie> mapCategorie ) throws SQLException {
		Categorie categorie = null;
		if ( mapCategorie != null ) {
			categorie = mapCategorie.get( rs.getInt("IdCategorie") );
		}
		if ( categorie == null ) {
			categorie = daoCategorie.retrouver( rs.getInt( "idCategorie" ) );
			if ( mapCategorie != null ) {
				mapCategorie.put( categorie.getId(), categorie );
			}
		}
		Personne personne = new Personne();
		personne.setId(rs.getInt( "IdPersonne" ));
		personne.setCategorie( categorie );
		personne.setNom(rs.getString( "Nom" ));
		personne.setPrenom(rs.getString( "Prenom" ));
		personne.getTelephones().addAll( daoTelephone.listerPourPersonne( personne ) );
		return personne;
	}
	
}
