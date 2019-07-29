package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_Postgres {

	private final String ip = "localhost";
	private final Integer port = 5432;
	private final String user = "postgres";
	private final String password = "";
	private final String database = "fbd";

/*	
	private final String ip = "sgconmec.cq4s7ekotzef.sa-east-1.rds.amazonaws.com";
	private final Integer port = 5432;
	private final String user = "sgcm";
	private final String password = "ufcsgcm2019";
	private final String database = "SGCM";
*/
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + database, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
