package com.github.nicks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CashShopSellEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();


    private CashShopSellEvent.SellType sellType;

    private Player player;

    public CashShopSellEvent(Player player) {
        this.player = player;
    }

    public void setSellType(CashShopSellEvent.SellType sellType) {
        this.sellType = sellType;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    public CashShopSellEvent.SellType getSellType() {
        return sellType;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public enum SellType {
        SET, SINGLE
    }
}
