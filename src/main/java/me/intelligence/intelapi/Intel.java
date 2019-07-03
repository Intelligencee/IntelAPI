package me.intelligence.intelapi;

import me.intelligence.intelapi.util.CC;
import me.intelligence.intelapi.util.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Intel extends JavaPlugin {

    public void onEnable() {
        new TitleUtil();
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7--------------------------------"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(CC.translate("   &c&l&nINTEL-API ENABLED SUCCESSFULLY&7   "));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7--------------------------------"));
    }

    public void onDisable(){}
}
