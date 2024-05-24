package Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

/**
 * The ConnectionUtil class will be utilized to create an active connection to
 * our database. This class utilizes the singleton design pattern. We will be
 * utilizing an in-memory called h2database for the sql demos.
 *
 * DO NOT CHANGE ANYTHING IN THIS CLASS
 */
public class ConnectionUtil {

	/**
	 * url will represent our connection string. Since this is an in-memory db, we
	 * will represent a file location to store the data
	 */
	private static String url = "jdbc:h2:./h2/db;";
	/**
	 * Default username for connecting to h2
	 */
	private static String username = "sa";
	/**
	 * Default password for connecting to h2
	 */
	private static String password = "sa";

	/**
	 * DataSource for pooling. Pooling enables the creation of multiple connections when connections are closed.
	 */
	private static JdbcDataSource pool = new JdbcDataSource();

	/**
	 * static initialization block to establish credentials for DataSoure Pool
	 */
	static {
		pool.setURL(url);
		pool.setUser(username);
		pool.setPassword(password);
	}

	/**
	 * @return an active connection to the database
	 */
	public static Connection getConnection() {
		try {
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * For the purpose of testing, we will need to drop and recreate our database
	 * tables to keep it consistent across all tests. The method will read the sql
	 * file in resources. This will be performed before every test.
	 */
	public static void resetTestDatabase() {
		try {
			FileReader sqlReader = new FileReader("src/main/resources/SocialMedia.sql");
			RunScript.execute(getConnection(), sqlReader);
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
