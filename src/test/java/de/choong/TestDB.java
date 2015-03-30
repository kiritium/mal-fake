package de.choong;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			statement.executeUpdate("CREATE TABLE T_ANIME (ID LONG PRIMARY KEY NOT NULL, NAME TEXT NOT NULL, AUTHOR TEXT NOT NULL, YEAR INT NOT NULL)");
			statement.execute("INSERT INTO T_ANIME (ID,NAME,AUTHOR,YEAR) VALUES (1, 'Kiseijuu', 'Tom', 2014)");
			statement.execute("INSERT INTO T_ANIME (ID,NAME,AUTHOR,YEAR) VALUES (2, 'Death Parade', 'Decim', 2015)");
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO T_ANIME (ID,NAME,AUTHOR,YEAR) VALUES (?, ?, ?, ?)");
			stmt.setLong(1, 3);
			stmt.setString(2, "SAO");
			stmt.setString(3, "Bob");
			stmt.setInt(4, 2012);
			stmt.execute();
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
