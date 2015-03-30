package de.choong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.choong.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        createDB();
        // add your configuration here

    }

    private void createDB() {
        String db_path = "src/main/resources/mysite.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='T_ANIME'");
            try {
                rs.getString("NAME");
            } catch (SQLException e) {
                stmt.executeUpdate("CREATE TABLE T_ANIME (ID LONG PRIMARY KEY NOT NULL, TITLE TEXT NOT NULL, AUTHOR TEXT NOT NULL, YEAR INT NOT NULL)");
            }
//            if (rs.getString("NAME") == null) {
//                stmt.executeUpdate("CREATE TABLE T_ANIME (ID LONG PRIMARY KEY NOT NULL, TITLE TEXT NOT NULL, AUTHOR TEXT NOT NULL, YEAR INT NOT NULL)");
//            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
