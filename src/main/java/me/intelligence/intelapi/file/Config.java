package me.intelligence.intelapi.file;

import me.intelligence.intelapi.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Config
{
    private File configFile;
    private YamlConfiguration config;
    private boolean changed;

    public Config(final Plugin plugin, final HashMap<String, Object> defaults, final String name) {
        this.changed = false;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        this.configFile = new File(plugin.getDataFolder(), name + ".yml");
        if (!this.configFile.exists()) {
            try {
                this.configFile.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.load();
        if (defaults != null) {
            for (final String path : defaults.keySet()) {
                this.config.addDefault(path, defaults.get(path));
            }
            this.config.options().copyDefaults(true);
            this.save();
        }
        final int delay = 6000;
        new BukkitRunnable() {
            public void run() {
                if (Config.this.isChanged()) {
                    Config.this.save();
                }
            }
        }.runTaskTimerAsynchronously(plugin, (long)delay, (long)delay);
        Bukkit.getConsoleSender().sendMessage(CC.translate("&c[INTEL API] &7YAML file &e" + name + ".yml&7 registered."));
    }

    public YamlConfiguration getFile() {
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.configFile);
            this.changed = false;
        }
        catch (Exception ex) {}
    }

    public void load() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public boolean isChanged() {
        return this.changed;
    }

    public void setChanged(final boolean changed) {
        this.changed = changed;
    }
}
