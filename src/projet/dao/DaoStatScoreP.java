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
import projet.data.Quizz;
import projet.data.Theme;

public class DaoStatScoreP {

	// Champs

		@Inject
		private DataSource		dataSource;
		
		// on doit update la table statscore pour ajouter une propriété affich de type varchar 
		// Une fois cela fait , la méthode ci-dessous sera utilisable 
		
		public List<String> listerPourQuizz( Quizz qz  ) {   // inutile pour l'instant 

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT valeur_min, valeur_max, valeur_moy FROM stat_parcours WHERE id_stat_score = ? ORDER BY id_stat_score ";
				stmt = cn.prepareStatement(sql);
				stmt.setInt( 1, qz.getidStatScore() );
				rs = stmt.executeQuery();

				List<String> stat = new ArrayList<>();
				while (rs.next()) {
					stat.add( rs.getString("id_stat_score") );   
					
				}
				return stat;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

}
