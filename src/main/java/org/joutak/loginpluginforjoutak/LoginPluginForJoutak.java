package org.joutak.loginpluginforjoutak;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.joutak.loginpluginforjoutak.commands.LoginAddAndRemovePlayerCommand;

@Getter
public final class LoginPluginForJoutak extends JavaPlugin {

    @Getter
    private static LoginPluginForJoutak instance;

    @Override
    public void onEnable() {

        instance=this;
        new LoginAddAndRemovePlayerCommand("login");
        System.out.println("123");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
