package de.choong;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDB {

	private final String PATH_TO_DB = "src/test/resources/test.db";
	private Connection connection;
	
	@Before
	public void setup() {
		File db = new File(PATH_TO_DB);
		db.delete();
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not connect to DB.");
		}
	}
	
	@After
	public void afterTest() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreate() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE ANIME (ID LONG PRIMARY KEY NOT NULL, NAME TEXT NOT NULL)");
			statement.execute("INSERT INTO ANIME (ID,NAME) VALUES (1, 'Paul')");
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
