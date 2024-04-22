package org.joutak.loginpluginforjoutak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;
import org.joutak.loginpluginforjoutak.LoginPluginForJoutak;

public abstract class AbstractCommand implements CommandExecutor {

    public AbstractCommand(String command) {
        PluginCommand pluginCommand = LoginPluginForJoutak.getInstance().getCommand(command);
        if (pluginCommand!=null){
            pluginCommand.setExecutor(this);
        }
    }

    public abstract void execute(CommandSender commandSender, Command command, String string, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        execute(commandSender, command, string, args);
        return true;
    }

}
