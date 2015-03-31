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
            PreparedStatement prestmt = connection.prepareStatement("INSERT INTO T_ANIME (TITLE,AUTHOR,YEAR) VALUES (?, ?, ?)");
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
    public AnimeDO read(int id) {
        Connection connection = connectToDB();
        AnimeDO anime = new AnimeDO();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from T_ANIME");
            while (rs.next()) {
                int readId = rs.getInt("ID");
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
        int id = updatedObj.getId();
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
    public void delete(int id) {
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
                anime.setId(rs.getInt("ID"));
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
