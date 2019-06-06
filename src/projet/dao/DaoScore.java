package projet.dao;

import javax.inject.Inject;
import javax.sql.DataSource;


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

public class DaoScore {    

	// Champs

	@Inject
	private DataSource		dataSource;

	  /* id_stat_score int NOT NULL ,   
       valeur_min    float NOT NULL ,
       valeur_max    float NOT NULL ,
       valeur_moy    float NOT NULL */
	
	/* il est important de souligner que dès qu'une action doit être menée sur une table ,
	 * même si l'action n'a pas de bouton dans l'application mais doit affectée la base, la méthode 
	 * correspondante doit être codée dans son Dao 
	 */

	// je doiscoder sa méthode lister() 
}
