package com.perceus.eol.branch.mmo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.perceus.eol.utils.PrintUtils;

public class PlayerHud
{
		
    public static void createDisplay(Player player) 
    {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective playerName = board.registerNewObjective("name", Criteria.DUMMY, ChatColor.WHITE + player.getDisplayName());
        playerName.setDisplaySlot(DisplaySlot.SIDEBAR);
        playerName.setDisplayName(PrintUtils.ColorParser("&r&f&lUser: " + player.getDisplayName()));
        
        player.setScoreboard(board);
    }

    //redundant?: 
    public static void clearBoard(Player player) 
    {
    	Scoreboard board = player.getScoreboard();
        board.resetScores(player.getUniqueId().toString());
        createDisplay(player);
    }
}
