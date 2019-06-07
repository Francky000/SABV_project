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

import projet.data.Question;
import projet.data.Quizz;
          
import fwk3il.dao.jdbc.UtilJdbc;


public class DaoQuestion { 


	// Champs       

		@Inject
		private DataSource		dataSource;
		
		
		public List<String> listReponseImg(Question question ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                 
				cn = dataSource.getConnection();
     
				sql = "SELECT  nom_mr FROM media_reponse ORDER BY id_mr ";
				stmt = cn.prepareStatement(sql);
				stmt.setInt( 1, question.getIdques() );
				rs = stmt.executeQuery();

				List<String> Ques = new ArrayList<>();      
				while (rs.next()) {
					Ques.add( rs.getString("nom_mr") );
									}
				return Ques;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );
			}                                  
		}  
		
		public List<String> listReponse(Question question ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                 
				cn = dataSource.getConnection();
     
				sql = "SELECT libelle_rt FROM reponse_rt ORDER BY id_rt ";
				stmt = cn.prepareStatement(sql); 
				stmt.setInt( 1, question.getIdques() );
				rs = stmt.executeQuery();

				List<String> Ques = new ArrayList<>();      
				while (rs.next()) {
					Ques.add( rs.getString("libelle_rt") );
									}
				return Ques;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );
			}                                  
		}  
		
}
