package me.kodysimpson.pointy.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.kodysimpson.pointy.database.entities.PlayerPoints;
import org.bukkit.entity.Player;

import java.sql.*;

public class PointsDatabase {

    private final Dao<PlayerPoints, String> playerPointsDao;

    public PointsDatabase(String path) throws SQLException {
        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + path);
        TableUtils.createTableIfNotExists(connectionSource, PlayerPoints.class);
        playerPointsDao = DaoManager.createDao(connectionSource, PlayerPoints.class);
    }

    public PlayerPoints addPlayer(Player player) throws SQLException {
        PlayerPoints playerPoints = new PlayerPoints();
        playerPoints.setUuid(player.getUniqueId().toString());
        playerPoints.setUsername(player.getName());
        playerPointsDao.create(playerPoints);
        return playerPoints;
    }

    public boolean playerExists(Player player) throws SQLException {
        return playerPointsDao.idExists(player.getUniqueId().toString());
    }

    public void updatePlayerPoints(Player player, int points) throws SQLException{
        PlayerPoints playerPoints = playerPointsDao.queryForId(player.getUniqueId().toString());
        if (playerPoints != null) {
            playerPoints.setPoints(points);
            playerPointsDao.update(playerPoints);
        }
    }

    //delete method as an example
    public void deletePlayer(Player player) throws SQLException{
        playerPointsDao.deleteById(player.getUniqueId().toString());
    }

    public PlayerPoints getPlayerPoints(Player player) throws SQLException {
        return playerPointsDao.queryForId(player.getUniqueId().toString());
    }

}
