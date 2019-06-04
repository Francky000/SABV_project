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
import projet.data.Quizz;

import fwk3il.dao.jdbc.UtilJdbc;




public class DaoQuizz {
	
	// Champs       

		@Inject
		private DataSource		dataSource;
	/*	@Inject
		private DaoStat_score	daoStat_score;  */
		
		
		
		
		public List<String> ListQuizz() {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM Quizz  ORDER BY id_stat_score ";
				stmt = cn.prepareStatement(sql);
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
		
		public List<String> ListQuestions( Quizz qz ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT q.libelle_ques, qz.titre FROM question q INNER JOIN appartenir a " + 
						"ON q.id_ques = a.id_ques INNER JOIN quizz qz ON qz.id_qz = a.id_qz" + 
						"WHERE qz.id_qz = ? ORDER BY qz.titre ;";
				stmt = cn.prepareStatement(sql);
				stmt.setInt( 1, qz.getIdqz() );
				rs = stmt.executeQuery();

				List<String> questions = new ArrayList<>();
				while (rs.next()) {
					questions.add( rs.getString("q.libelle_ques") );
				}
				return questions;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
}
