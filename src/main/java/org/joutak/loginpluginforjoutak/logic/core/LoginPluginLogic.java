package org.joutak.loginpluginforjoutak.logic.core;

import org.bukkit.entity.Player;

public interface LoginPluginLogic {

    void addPlayer(Player player, Integer prolongTimeInMonths);

    void removePlayer(Player player);

    boolean checkPlayer(Player player);

}
