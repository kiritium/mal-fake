package de.choong.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

/**
 * Anime Dao that saves the data in a SQLite DB.
 * 
 */
public class AnimeSqliteDBDao implements IAnimeDao {

    private static final long serialVersionUID = -6371901382222268180L;

    @Override
    public void create(AnimeDO newObject) throws DBException {
        Connection connection = connectToDB();
        try {
            PreparedStatement prestmt = connection
                    .prepareStatement("INSERT INTO T_ANIME (TITLE,AUTHOR,YEAR) VALUES (?, ?, ?)");
            prestmt.setString(1, newObject.getTitle());
            prestmt.setString(2, newObject.getAuthor());
            prestmt.setInt(3, newObject.getYear());
            prestmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DBException("SQL Error", e);
        }

    }

    @Override
    public AnimeDO read(int id) throws DBException {
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
            throw new DBException("SQL Error", e);
        }

        return anime;
    }

    @Override
    public void update(AnimeDO updatedObj) throws DBException {
        Connection connection = connectToDB();
        int id = updatedObj.getId();
        String title = updatedObj.getTitle();
        String author = updatedObj.getAuthor();
        int year = updatedObj.getYear();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE T_ANIME SET TITLE=" + title + ",AUTHOR=" + author + ",YEAR="
                    + year + " WHERE ID=" + id + ";");
            connection.close();
        } catch (SQLException e) {
            throw new DBException("SQL Error", e);
        }
    }

    @Override
    public void delete(int id) throws DBException {
        Connection connection = connectToDB();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE from T_ANIME where ID=" + id + ";");
            connection.close();
        } catch (SQLException e) {
            throw new DBException("SQL Error", e);
        }
    }

    private Connection connectToDB() throws DBException {
        String db_path = "src/main/resources/mysite.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);
        } catch (SQLException e) {
            throw new DBException("SQL Error", e);
        }
        return connection;
    }

    @Override
    public ArrayList<AnimeDO> readAll() throws DBException {
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
            throw new DBException("SQL Error", e);
        }

        return animes;
    }

}
