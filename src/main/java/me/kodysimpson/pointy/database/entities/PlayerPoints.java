package me.kodysimpson.pointy.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "players_points")
public class PlayerPoints {

    @DatabaseField(id = true)
    private String uuid;
    @DatabaseField(canBeNull = false)
    private String username;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int points;

    //No args constructor required
    public PlayerPoints() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
