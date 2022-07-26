package me.untouchedodin0.tokens.utils.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TokenGainEvent extends Event {

    public UUID uuid;
    public Player player;
    public double tokens;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public TokenGainEvent(UUID uuid, double tokens) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
        this.tokens = tokens;
    }

    public double getTokensGained() {
        return tokens;
    }

    public Player getPlayer() {
        return player;
    }
}