package me.untouchedodin0.tokens.utils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TokenDeductEvent extends Event {

    public double tokens = 0;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public TokenDeductEvent(UUID uuid, double tokens) {
        this.tokens = tokens;
    }

    public double getTokensDeducted() {
        return tokens;
    }
}