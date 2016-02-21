package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class will provide the data base connection
 * 
 * @author Admin
 *
 */
public class JDBCConnector {

	/**
	 * Create a new jdbc connection and return the new connection
	 * 
	 * @return connection
	 */
	public static Connection getJDBCConnection() {
		// logger object to create log files
		Logger log = Logger.getLogger(JDBCConnector.class);
		log.info("started to get the jdbc connection");

		// create a property object to read the jdbc property and supply the
		// jdbc connection information
		Properties jdbcProperties = new Properties();

		// get the path of jdbc properties
		String path = ClassLoader.getSystemResource("jdbc.properties")
				.getPath();

		// file input stream to read the property
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			log.error("unable to create inputstream for jdbc property file. Please check the property file path"
					+ e);
		}
		// load the properties
		try {
			jdbcProperties.load(fis);
		} catch (IOException e) {
			log.error("unable to parse the property file, please check the format."
					+ e);
		}

		// load the driver
		try {
			Class.forName(jdbcProperties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			log.error("Unable to load the driver, please check the project settings if the jar is present or not"
					+ e);
		}
		// create the db connection
		Connection conn = null;

		try {
			conn = (Connection) DriverManager.getConnection(
					jdbcProperties.getProperty("dburl"),
					jdbcProperties.getProperty("username"),
					jdbcProperties.getProperty("password"));
		} catch (SQLException e) {
			log.error("Unable to connect to database, please check the url/username/pwd data in property file"
					+ e);
		}

		log.info("exiting form jdbc connection method");
		return conn;
	}
}
