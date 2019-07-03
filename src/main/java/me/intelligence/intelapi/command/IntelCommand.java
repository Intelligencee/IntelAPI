package me.intelligence.intelapi.command;

import me.intelligence.intelapi.IntelPlugin;
import me.intelligence.intelapi.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class IntelCommand implements TabCompleter, CommandExecutor {

    private CommandInfo info;

    public IntelCommand() {
        this.info = (this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getAnnotation(CommandInfo.class) : null);
    }

    protected List<String> onTabComplete(final CommandSender sender, final String[] args) {
        return null;
    }

    protected void onCommand(final CommandSender sender, final String[] args) {
    }

    protected void onCommand(final Player player, final String[] args) {
    }

    protected void onCommand(final ConsoleCommandSender sender, final String[] args) {
    }

    protected void onCommand(final BlockCommandSender sender, final String[] args) {
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] strings) {
        try {
            this.onCommand(sender, strings);
            if (sender instanceof Player) {
                this.onCommand((Player)sender, strings);
            }
            else if (sender instanceof ConsoleCommandSender) {
                this.onCommand((ConsoleCommandSender)sender, strings);
            }
            else if (sender instanceof BlockCommandSender) {
                this.onCommand((BlockCommandSender)sender, strings);
            }
        }
        catch (CommandException e) {
            sender.sendMessage(CC.Poor + CC.translate(e.getMessage()));
        }
        return true;
    }

    protected void checkArgs(final String[] args, final int check, final String msg) {
        if (args.length < check) {
            throw new CommandException(msg);
        }
    }

    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        return this.onTabComplete(commandSender, strings);
    }

    public void register(final IntelPlugin plugin) {
        plugin.registerCommand(this);
    }

    public Player getPlayer(final String s) {
        return this.getPlayer(s, "Player not found.");
    }

    public Player getPlayer(final String s, final String msg) {
        final Player player = Bukkit.getPlayer(s);
        if (player == null) {
            throw new CommandException(msg);
        }
        return player;
    }

    public CommandInfo getInfo() {
        return this.info;
    }
}
