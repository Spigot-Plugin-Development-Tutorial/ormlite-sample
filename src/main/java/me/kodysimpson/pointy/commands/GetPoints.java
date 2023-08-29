package me.kodysimpson.pointy.commands;

import me.kodysimpson.pointy.Pointy;
import me.kodysimpson.pointy.database.entities.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class GetPoints implements CommandExecutor {

    private final Pointy pointyPlugin;

    public GetPoints(Pointy pointyPlugin) {
        this.pointyPlugin = pointyPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        try{
            if (args.length == 0 && sender instanceof Player p){

                PlayerPoints playerPoints = pointyPlugin.getPointsDatabase().getPlayerPoints(p);
                if (playerPoints == null){
                    playerPoints = pointyPlugin.getPointsDatabase().addPlayer(p);
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have &e" + playerPoints.getPoints() + "&a points!"));

                return true;
            }else{

                //Get the player
                String playerName = args[0];
                Player player = Bukkit.getServer().getPlayer(playerName);

                if (player == null){
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }

                //Get the amount
                PlayerPoints playerPoints = pointyPlugin.getPointsDatabase().getPlayerPoints(player);
                if (playerPoints == null){
                    playerPoints = pointyPlugin.getPointsDatabase().addPlayer(player);
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getName() + " has &e" + playerPoints.getPoints() + "&a points!"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "An error occurred while getting your points. Try again later.");
            return true;
        }

        return true;
    }
}
