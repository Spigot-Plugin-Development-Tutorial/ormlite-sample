package me.kodysimpson.pointy.listeners;

import me.kodysimpson.pointy.Pointy;
import me.kodysimpson.pointy.database.entities.PlayerPoints;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.sql.SQLException;

public class MobKillListener implements Listener {

    private final Pointy pointyPlugin;

    public MobKillListener(Pointy pointyPlugin) {
        this.pointyPlugin = pointyPlugin;
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent e) throws SQLException {

        if (e.getEntity().getKiller() == null) return;

        Player killer = e.getEntity().getKiller();
        PlayerPoints playerPoints = pointyPlugin.getPointsDatabase().getPlayerPoints(killer);
        if (playerPoints == null){
            playerPoints = pointyPlugin.getPointsDatabase().addPlayer(killer);
        }

        int newPoints = (int) (Math.random() * 10) + 1;
        playerPoints.setPoints(playerPoints.getPoints() + newPoints);

        //update the player's points in the DB
        pointyPlugin.getPointsDatabase().updatePlayerPoints(killer, playerPoints.getPoints());

        killer.sendMessage(ChatColor.GREEN + "+" + newPoints + " pts");
    }

}
