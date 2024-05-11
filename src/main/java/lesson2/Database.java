package lesson2;

import lesson4.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private static final Properties properties = PropertiesLoader.loadProperties();

	private Connection connection;

	public Connection getNewConnection() {
		try {
			connection = DriverManager.getConnection(
					properties.getProperty("DB_URL"),
					properties.getProperty("DB_USERNAME"),
					properties.getProperty("DB_PASSWORD")
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
