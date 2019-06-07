//package projet.dao;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.sql.Blob;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//import javax.inject.Inject;
//import javax.sql.DataSource;
//
//import fwk3il.dao.jdbc.UtilJdbc;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.image.Image;
//import projet.data.Memo;
//
//
//public class DaoMemo {
//
//	
//	// Champs
//
//	@Inject
//	private DataSource		dataSource;          
//	
//	@Inject
//	private DaoPersonne		daoPersonne;           
//
//	
//	// Actions       
//
//	public int inserer( Memo memo ) {
//
//		Connection			cn		= null;
//		CallableStatement	stmt	= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "{ CALL tuto_memo_insert( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) }";
//			stmt = cn.prepareCall( sql );
//			stmt.setObject(	1, memo.getTitre() );
//			stmt.setObject(	2, memo.getDescription() );
//			stmt.setObject( 3, memo.getFlagUrgent() );
//			stmt.setObject(	4, memo.getStatut() );
//			stmt.setObject(	5, memo.getEffectif() );
//			stmt.setObject(	6, memo.getBudget() );
//			stmt.setObject(	7, memo.getEcheance() );
//			stmt.setObject(	8, memo.getCheminImage2() );
//			if ( memo.getPersonnee() == null ) {
//				stmt.setObject(	9, null );
//			} else {
//				stmt.setObject(	9, memo.getPersonnee().getId() );
//			}
//
//			stmt.registerOutParameter( 10, Types.INTEGER );
//			stmt.executeUpdate();
//
//			// Récupère l'identifiant généré par le SGBD
//			memo.setId( stmt.getInt( 10 ) );
//			return memo.getId();
//	
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( stmt, cn );
//		}
//	}
//
//
//	public void modifier( Memo memo ) {
//
//		Connection			cn		= null;
//		PreparedStatement	stmt	= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "UPDATE tuto_memo SET Titre = ?, Description = ?, FlagUrgent = ?, Statut = ?, Effectif = ?, Budget = ?,  Echeance = ?, CheminImage2 = ?, IdPersonne = ?  WHERE IdMemo =  ?";
//			stmt = cn.prepareStatement( sql );
//			stmt.setObject(	1, memo.getTitre() );
//			stmt.setObject(	2, memo.getDescription() );
//			stmt.setObject( 3, memo.getFlagUrgent() );
//			stmt.setObject(	4, memo.getStatut() );
//			stmt.setObject(	5, memo.getEffectif() );
//			stmt.setObject(	6, memo.getBudget() );
//			stmt.setObject(	7,  memo.getEcheance()  );
//			stmt.setObject(	8, memo.getCheminImage2() );
//			if ( memo.getPersonnee() == null ) {
//				stmt.setObject(	9, null );
//			} else {
//				stmt.setObject(	9, memo.getPersonnee().getId() );
//			}
//			stmt.setObject(	10, memo.getId() );
//			stmt.executeUpdate();
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( stmt, cn );
//		}
//	}
//
//
//	public void modifierImage1( int idMemo, Image image ) {
//
//		Connection			cn		= null;
//		PreparedStatement	stmt	= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "UPDATE tuto_memo SET  Image1 = ?  WHERE IdMemo =  ?";
//			stmt = cn.prepareStatement( sql );
//			if ( image == null ) {
//				stmt.setObject(	1, null  );
//			} else {
//				BufferedImage bImage = SwingFXUtils.fromFXImage( image, null);
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				ImageIO.write( bImage,"jpg", out);
//				InputStream in = new ByteArrayInputStream(out.toByteArray());
//				stmt.setBinaryStream( 1, in );
//			}
//			stmt.setObject(	2, idMemo );
//			stmt.executeUpdate();
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( stmt, cn );
//		}
//	}
//
//
//	public void supprimer( int idMemo ) {
//
//		Connection			cn 		= null;
//		PreparedStatement	stmt 	= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "DELETE FROM tuto_memo WHERE IdMemo = ? ";
//			stmt = cn.prepareStatement( sql );
//			stmt.setInt( 1, idMemo );
//			stmt.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( stmt, cn );
//		}
//	}
//
//	
//	public Memo retrouver( int idMemo ) {
//
//		Connection			cn 		= null;
//		PreparedStatement	stmt	= null;
//		ResultSet 			rs		= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "SELECT * FROM tuto_memo WHERE IdMemo = ?";
//			stmt = cn.prepareStatement( sql );
//			stmt.setInt(1, idMemo);
//			rs = stmt.executeQuery();
//
//			if ( rs.next() ) {
//				return construireMemo( rs );
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//	}
//
//	
//	public Image retrouverImage1( int idMemo ) {
//
//		Connection			cn 		= null;
//		PreparedStatement	stmt	= null;
//		ResultSet 			rs		= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "SELECT image1 FROM tuto_memo WHERE IdMemo = ?";
//			stmt = cn.prepareStatement( sql );
//			stmt.setInt(1, idMemo);
//			rs = stmt.executeQuery();
//
//			if ( rs.next() ) {
//				Blob blob = rs.getBlob( "Image1" );
//				if ( blob == null ) {
//					return null;
//				} else {
//					return new Image( blob.getBinaryStream()  );
//				}
//				
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//	}
//
//
//	public List<Memo> listerTout() {
//
//		Connection			cn 		= null;
//		PreparedStatement	stmt 	= null;
//		ResultSet 			rs		= null;
//		String				sql;
//
//		try {
//			cn = dataSource.getConnection();
//			sql = "SELECT * FROM tuto_memo ORDER BY Titre";
//			stmt = cn.prepareStatement( sql );
//			rs = stmt.executeQuery();
//
//			List<Memo> memos = new ArrayList<>();
//			while (rs.next()) {
//				memos.add( construireMemo( rs ) );
//			}
//			return memos;
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//	}
//
//    
//    public int compterourPersonne( int idPersonne ) {
//    	
//		Connection			cn		= null;
//		PreparedStatement	stmt 	= null;
//		ResultSet 			rs		= null;
//
//		try {
//			cn = dataSource.getConnection();
//            String sql = "SELECT COUNT(*) FROM tuto_memo WHERE IdPersonne = ?";
//            stmt = cn.prepareStatement( sql );
//            stmt.setInt( 1, idPersonne );
//            rs = stmt.executeQuery();
//
//            rs.next();
//            return rs.getInt( 1 );
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			UtilJdbc.close( rs, stmt, cn );
//		}
//    }
//	
//	
//	// Méthodes auxiliaires
//	
//	private Memo construireMemo( ResultSet rs ) throws SQLException {
//		Memo memo = new Memo();
//		memo.setId( rs.getInt( "IdMemo" ) );
//		memo.setTitre( rs.getString( "Titre" ) );
//		memo.setDescription( rs.getString( "Description" ) );
//		memo.setFlagUrgent( rs.getBoolean( "FlagUrgent" ) );
//		memo.setStatut(   rs.getInt( "Statut" ) );
//		memo.setEffectif( rs.getObject( "Effectif", Integer.class ) );
//		memo.setBudget(   rs.getObject( "Budget", Double.class ) );
//		memo.setEcheance( rs.getObject( "Echeance", LocalDate.class) );
//		memo.setCheminImage2( rs.getString( "CheminImage2" ) );
//		Integer idPersonne = rs.getObject( "IdPersonne", Integer.class );
//		if ( idPersonne != null ) {
//			memo.setPersonnee( daoPersonne.retrouver( idPersonne ) );
//		}
//		return memo;
//	}
//
//}
