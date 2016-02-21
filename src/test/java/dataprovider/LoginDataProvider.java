package dataprovider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.JDBCConnector;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	@DataProvider(name = "invalidLogin")
	public static Object[][] getInvalidLoginData() {
		// create logger
		Logger log = Logger.getLogger(LoginDataProvider.class);
		log.info("started collecting invalid login data");
			// get connection
			Connection connection = JDBCConnector.getJDBCConnection();

		// null check for connections
		if (connection == null) {
			log.error("no db connection available, connection is null");
			// return empty Object
			return new Object[][] {};
		}

		// create statement
		Statement createStatementForCount = null;
		try {
			createStatementForCount = connection.createStatement();
		} catch (SQLException e) {
			log.error("unable to create statement using connection object" + e);
			// return empty Object
			return new Object[][] {};
		}

		// execute statement
		String countQuery = "select count(*) as count from users where flag=0";
		ResultSet executeQuery = null;
		try {
			executeQuery = createStatementForCount.executeQuery(countQuery);
		} catch (SQLException e) {
			log.error("unable to execute statement using connection object. Check the query"
					+ e);
			// return empty Object
			return new Object[][] {};
		}

		int totalRecord = 0;
		// get result
		try {
			while (executeQuery.next()) {
				totalRecord = executeQuery.getInt("count");
			}
		} catch (SQLException e) {
			log.error("Unable to get the value, check the column name" + e);
			// return empty Object
			return new Object[][] {};
		}

		// create 2d object
		Object[][] obj = new Object[totalRecord][3];

		try {
			executeQuery.close();
			createStatementForCount.close();
		} catch (SQLException e) {
			log.error("unable to close execute or create statment" + e);
		}

		// query to get the result
		String query = "select * from users where flag=0";
		Statement csForActualData = null;
		try {
			csForActualData = connection.createStatement();
		} catch (SQLException e) {
			log.error("unable to execute statement using connection object. Check the query"
					+ e);
			// return empty Object
			return new Object[][] {};
		}
		ResultSet executeQuery2 = null;
		try {
			executeQuery2 = csForActualData.executeQuery(query);
		} catch (SQLException e) {
			log.error("unable to create statement using connection object" + e);
			// return empty Object
			return new Object[][] {};
		}

		// get result
		try {
			int i = 0;
			while (executeQuery2.next()) {
				obj[i][0] = executeQuery2.getString("username");
				obj[i][1] = executeQuery2.getString("password");
				obj[i][2] = executeQuery2.getInt("os");
				i++;
			}
		} catch (SQLException e) {
			log.error("Unable to get the value, check the column name" + e);
			// return empty Object
			return new Object[][] {};
		}

		// close all connections
		try {
			executeQuery2.close();
			csForActualData.close();
			connection.close();
		} catch (SQLException e) {
			log.error("unable to close connections" + e);
		}

		log.info("got the data from db exiting login data provider");
		// return 2d object
		return obj;
	}
}
