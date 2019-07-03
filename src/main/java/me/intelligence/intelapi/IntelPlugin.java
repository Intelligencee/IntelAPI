package me.intelligence.intelapi;

import me.intelligence.intelapi.command.CommandInfo;
import me.intelligence.intelapi.command.IntelCommand;
import me.intelligence.intelapi.util.CC;
import me.intelligence.intelapi.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class IntelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] &7Enabled Intel-API hook &b&l" + getClass().getName()));

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] &7Disabled Intel-API hook &b&l" + getClass().getName() + "&7 :("));

    }

    public void registerCommand(IntelCommand cmd){

        CommandInfo info = cmd.getInfo();
        if(info == null)
            throw new IllegalArgumentException("The command " + cmd.getClass().getName() + " cannot be registered as it has no CommandInfo annotation!");

        PluginCommand pcmd = getCommand(info.name());

        if(pcmd == null){

            // inject into command map

            try {
                Constructor cmdConst = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                cmdConst.setAccessible(true);
                pcmd = (PluginCommand) cmdConst.newInstance(info.name(), this);

                pcmd.setAliases(Arrays.asList(info.aliases()));
                pcmd.setDescription(info.description());
                pcmd.setUsage(info.usage());

                Field cmdMap = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                cmdMap.setAccessible(true);
                CommandMap map = (CommandMap) cmdMap.get(Bukkit.getPluginManager());

                map.register(info.name(), pcmd);

            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException |
                    NoSuchFieldException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] Failed to register /" + info.name() + "."));
            }

        }

        pcmd.setExecutor(cmd);
        pcmd.setTabCompleter(cmd);

        if(info.aliases().length <= 0)
            Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] &7Command &e/" + info.name() + "&7 registered."));
        else
            Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] &7Command &e/" + info.name() + "&7 registered, with aliases &e/" + TextUtil.combine(info.aliases(), 0, -1, "&7, &e/") + "&7."));

    }

}
