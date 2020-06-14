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
import projet.data.Reponse;
import fwk3il.dao.jdbc.UtilJdbc;


public class DaoQuestion { 


	// Champs       

		@Inject
		private DataSource		dataSource;
		
		
		public List<Reponse> listReponseImg(Integer Idques	 ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    
   
			try {                 
				cn = dataSource.getConnection();
     
				sql = "SELECT  nom_mr FROM media_reponse WHERE Id_ques = ?  IdORDER BY id_mr ";
				stmt = cn.prepareStatement(sql);
				stmt.setInt( 1, Idques );
				rs = stmt.executeQuery();

				List<Reponse>Rep = new ArrayList<>();      
				while (rs.next()) {
					Rep.add( construireReponse(rs) );
									}
				return Rep;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );
			}                                  
		}  
		
		public List<Reponse> listReponse(Integer Idques ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                  
				cn = dataSource.getConnection();
     
				sql = "SELECT * FROM reponse_texte WHERE Id_ques = ? ORDER BY id_rt ";
				stmt = cn.prepareStatement(sql); 
				stmt.setInt( 1, Idques );
				rs = stmt.executeQuery();

				List<Reponse> Reponse = new ArrayList<>();      
				while (rs.next()) {
					Reponse.add( construireReponse(rs) );
									}
				return Reponse;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );
			}                                  
		}
		
		public int valeurIdQues(String libelleques)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				sql = "select id_ques from question where libelle_ques =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(	1, libelleques );
				rs = stmt.executeQuery();
				rs.next();
				return rs.getInt("id_ques");
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close(rs, stmt, cn );
			}  

			
		}
		
		public String ReponseJuste(Integer idques ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                  
				cn = dataSource.getConnection();
     
				sql = "SELECT r.libelle_rt FROM reponse_texte r INNER JOIN question q ON r.id_ques = q.id_ques WHERE r.id_ques  = ? AND r.verite_rt = ? ORDER BY r.libelle_rt ";
				stmt = cn.prepareStatement(sql); 
				stmt.setInt( 1, idques );
				stmt.setInt( 2, 1 );
				rs = stmt.executeQuery();
				
				rs.next();
				return rs.getString("libelle_rt");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );    
			}                                    
		}
		
		
		public String Rechindice(Integer idques ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                  
				cn = dataSource.getConnection();
     
				sql = "SELECT indice FROM question  WHERE id_ques  = ? ORDER BY id_ques ";
				stmt = cn.prepareStatement(sql); 
				stmt.setInt( 1, idques );
				rs = stmt.executeQuery();
				
				rs.next();
				return rs.getString("indice");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );    
			}                                    
		}
		
		public String Rechinfos(Integer idques ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;    

			try {                  
				cn = dataSource.getConnection();
     
				sql = "SELECT bulle_infos FROM question  WHERE id_ques  = ? ORDER BY id_ques ";
				stmt = cn.prepareStatement(sql); 
				stmt.setInt( 1, idques );
				rs = stmt.executeQuery();
				
				rs.next();
				return rs.getString("bulle_infos");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {       
				UtilJdbc.close( rs, stmt, cn );    
			}                                    
		}
		
		private Reponse construireReponse( ResultSet rs ) throws SQLException {
			Reponse reponse  = new Reponse ();
			reponse.setIdrt( rs.getInt( "id_rt" ) );
			reponse.setlibellert(rs.getString("libelle_rt"));
			reponse.setverite(rs.getInt("verite_rt"));			
			reponse.setIdques(rs.getInt("id_ques") );
			return reponse;
		}
		
		
	
}
