package me.untouchedodin0.tokens.listener;

import me.untouchedodin0.tokens.utils.SQLUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SQLUtils.setupPlayer(player.getUniqueId());
    }
}
