package lesson4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	public static Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties");) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}
}
