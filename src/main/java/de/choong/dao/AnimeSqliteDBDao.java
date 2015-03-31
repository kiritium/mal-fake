package de.choong.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.choong.model.AnimeDO;

/**
 * Anime Dao that saves the data in a SQLite DB.
 * 
 */
public class AnimeSqliteDBDao implements IAnimeDao<AnimeDO> {

    @Override
    public void create(AnimeDO newObject) {
        Connection connection = connectToDB();
        try {
//            long id = 0;
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * from T_ANIME");
//            while (rs.next()) {
//                id = rs.getLong("ID");
//            }
//            id++;
            PreparedStatement prestmt = connection
                    .prepareStatement("INSERT INTO T_ANIME (TITLE,AUTHOR,YEAR) VALUES (?, ?, ?)");
//            prestmt.setLong(1, id);
            prestmt.setString(1, newObject.getTitle());
            prestmt.setString(2, newObject.getAuthor());
            prestmt.setInt(3, newObject.getYear());
            prestmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public AnimeDO read(long id) {
        Connection connection = connectToDB();
        AnimeDO anime = new AnimeDO();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from T_ANIME");
            while (rs.next()) {
                long readId = rs.getLong("ID");
                if (readId == id) {
                    anime.setId(readId);
                    anime.setTitle(rs.getString("TITLE"));
                    anime.setAuthor(rs.getString("AUTHOR"));
                    anime.setYear(rs.getInt("YEAR"));
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anime;
    }

    @Override
    public void update(AnimeDO updatedObj) {
        Connection connection = connectToDB();
        long id = updatedObj.getId();
        String title = updatedObj.getTitle();
        String author = updatedObj.getAuthor();
        int year = updatedObj.getYear();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE T_ANIME SET TITLE=" + title + ",AUTHOR="
                    + author + ",YEAR=" + year + " WHERE ID=" + id + ";");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = connectToDB();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE from T_ANIME where ID=" + id + ";");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connectToDB() {
        String db_path = "src/main/resources/mysite.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public ArrayList<AnimeDO> readAll() {
        ArrayList<AnimeDO> animes = new ArrayList<>();
        Connection connection = connectToDB();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from T_ANIME");
            while (rs.next()) {
                AnimeDO anime = new AnimeDO();
                anime.setId(rs.getLong("ID"));
                anime.setTitle(rs.getString("TITLE"));
                anime.setAuthor(rs.getString("AUTHOR"));
                anime.setYear(rs.getInt("YEAR"));
                animes.add(anime);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animes;
    }

}
