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

import projet.data.Memo;
import projet.data.Question;
import projet.data.Quizz;

import fwk3il.dao.jdbc.UtilJdbc;




public class DaoQuizz {
	
	// Champs       

		@Inject
		private static DataSource		dataSource;
	/*	@Inject
		private DaoStat_score	daoStat_score;  */
		
	  
		
		public  List<Quizz> ListQuizz() {

			Connection			cn		= null;   
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT q.* FROM Quizz q  ORDER BY id_qz ";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();

				List<Quizz> stat = new ArrayList<>();
				while (rs.next()) {
					stat.add( construireQuizz(rs));
					
				}
				return stat;
   
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

		public List<Question> ListQuestions( int numth) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM question   where numth=?";
				stmt = cn.prepareStatement(sql);
				stmt.setInt( 1, numth );
				rs = stmt.executeQuery();

				List<Question> questions = new ArrayList<>();
				while (rs.next()) {
					questions.add( construireQuestion(rs) );
				}
				return questions;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		
		private Quizz construireQuizz( ResultSet rs ) throws SQLException {
			Quizz quizz = new Quizz();
			quizz.setIdqz(rs.getInt("id_qz") );
			quizz.setTitre( rs.getString( "Titre" ) );
			quizz.setNb_ques(rs.getInt("NB_QUESTIONS"));
			quizz.setNumth(rs.getInt("Numth"));
			quizz.setidStatScore(rs.getInt("id_Stat_Score"));			
			return quizz;
		}
		
		private Question construireQuestion( ResultSet rs ) throws SQLException {
			Question question = new Question();
			question.setIdques(rs.getInt("id_ques") );
			question.setlibelle( rs.getString( "Libelle_ques" ) );
			question.setBulleInfo(rs.getString("Bulle_infos"));
			question.setIndice(rs.getString("Indice"));		
			question.setNumth(rs.getInt("numth"));
			return question;
		}
		
}
