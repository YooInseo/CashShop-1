package com.github.nicks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CashShopPurchaseEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();


    private PurchaseType purchaseType;

    private Player player;

    public CashShopPurchaseEvent(Player player) {
        this.player = player;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
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

    public enum PurchaseType {
        SET, SINGLE
    }
}
