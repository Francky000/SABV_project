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




public class DaoQuizz {
	
	// Champs       

		@Inject
		private DataSource		dataSource;
		@Inject
		private DaoStat_score	daoStat_score;
		     
		
}
