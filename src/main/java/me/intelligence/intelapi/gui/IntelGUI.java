package me.intelligence.intelapi.gui;

import me.intelligence.intelapi.util.CC;
import me.intelligence.intelapi.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public abstract class IntelGUI implements Listener {

    protected boolean opened;
    protected Player player;
    protected String title;
    protected int size;
    protected Plugin plugin;
    protected Inventory inventory;
    private HashMap<Integer, GUIItem> map;
    protected ItemStack backgroundItem;

    public IntelGUI() {
        this.opened = false;
        this.map = new HashMap<Integer, GUIItem>();
    }

    public void open(final Player player, final Plugin plugin) {
        this.open(player, plugin, "Inventory", 36);
    }

    public void open(final Player player, final Plugin plugin, final String title, final int size) {
        Bukkit.getPluginManager().registerEvents((Listener)this, plugin);
        this.plugin = plugin;
        this.player = player;
        this.title = title;
        this.size = size;
        this.map.clear();
        this.opened = true;
        this.inventory = Bukkit.createInventory((InventoryHolder)player, size, CC.translate(title));
        this.redraw();
        player.openInventory(this.inventory);
    }

    public void setBackground() {
        if (this.backgroundItem == null) {
            this.backgroundItem = ItemBuilder.builder().type(Material.STAINED_GLASS_PANE).data(7).flag(ItemFlag.values()).name(" ").build();
        }
        for (int i = 0; i < this.inventory.getSize(); ++i) {
            this.inventory.setItem(i, this.backgroundItem.clone());
        }
    }

    public void redraw() {
        this.setBackground();
        this.map.clear();
    }

    protected GUIItem item(final int i) {
        final GUIItem item = new GUIItem(i, this);
        this.map.put(i, item);
        return item;
    }

    @EventHandler
    public void onClick(final InventoryClickEvent ev) {
        if (ev.getWhoClicked().equals(this.player) && ev.getInventory().equals(this.inventory)) {
            ev.setCancelled(true);
            final GUIItem item = this.map.get(ev.getRawSlot());
            if (item != null) {
                if (ev.getClick().isLeftClick() && item.getLeftClick() != null) {
                    item.getLeftClick().run();
                }
                if (ev.getClick().isRightClick() && item.getRightClick() != null) {
                    item.getRightClick().run();
                }
                if (item.getClick() != null) {
                    item.getClick().run();
                }
            }
        }
    }

    protected void invalidate() {
        HandlerList.unregisterAll((Listener)this);
    }

    protected void close() {
        this.player.closeInventory();
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent ev) {
        if (ev.getPlayer().equals(this.player) && ev.getInventory().equals(this.inventory)) {
            this.invalidate();
        }
    }

    public boolean isOpened() {
        return this.opened;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ItemStack getBackgroundItem() {
        return this.backgroundItem;
    }

    public void setBackgroundItem(final ItemStack backgroundItem) {
        this.backgroundItem = backgroundItem;
    }
}
