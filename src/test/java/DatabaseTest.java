import lesson2.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {
	private static final Database database = new Database();
	private Connection connection;

	@BeforeEach
	void init() {
		connection = database.getNewConnection();
	}

	@AfterEach
	void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void shouldGetJdbcConnection() {
		Assertions.assertAll(
				() -> assertTrue(connection.isValid(1)),
				() -> assertFalse(connection.isClosed()));
	}
}
