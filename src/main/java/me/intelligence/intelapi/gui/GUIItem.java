package me.intelligence.intelapi.gui;

import me.intelligence.intelapi.util.CC;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIItem
{
    private Material type;
    private int data;
    private int amount;
    private String name;
    private List<String> lore;
    private ItemFlag[] flags;
    private HashMap<Enchantment, Integer> enchants;
    private ItemStack item;
    private Runnable leftClick;
    private Runnable rightClick;
    private Runnable click;
    private Color color;
    private String skullOwner;
    private int slot;
    private IntelGUI gui;

    public GUIItem(final int slot, final IntelGUI gui) {
        this.data = 0;
        this.amount = 1;
        this.name = null;
        this.lore = new ArrayList<String>();
        this.flags = new ItemFlag[0];
        this.enchants = new HashMap<Enchantment, Integer>();
        this.slot = slot;
        this.gui = gui;
    }

    public GUIItem type(final Material type) {
        this.type = type;
        return this;
    }

    public GUIItem data(final int data) {
        this.data = data;
        return this;
    }

    public GUIItem amount(final int amount) {
        this.amount = amount;
        return this;
    }

    public GUIItem name(final String name) {
        this.name = name;
        return this;
    }

    public GUIItem lore(final String... lore) {
        for (final String s : lore) {
            this.lore.add(CC.translate(s));
        }
        return this;
    }

    public GUIItem lore(final List<String> lore) {
        lore.forEach(s -> this.lore.add(CC.translate(s)));
        return this;
    }

    public GUIItem flag(final ItemFlag... flags) {
        this.flags = flags;
        return this;
    }

    public GUIItem enchant(final Enchantment enchant, final int level) {
        this.enchants.put(enchant, level);
        return this;
    }

    public GUIItem leftClick(final Runnable r) {
        this.leftClick = r;
        return this;
    }

    public GUIItem rightClick(final Runnable r) {
        this.rightClick = r;
        return this;
    }

    public GUIItem click(final Runnable r) {
        this.click = r;
        return this;
    }

    public GUIItem skullOwner(final String s) {
        this.skullOwner = s;
        return this;
    }

    public GUIItem item(final ItemStack item) {
        this.item = item;
        return this;
    }

    public GUIItem color(final Color color) {
        this.color = color;
        return this;
    }

    public void build() {
        if (this.item == null) {
            if (this.skullOwner != null) {
                this.type = Material.SKULL_ITEM;
                this.data = 3;
            }
            this.item = new ItemStack(this.type, this.amount, (short)(byte)this.data);
            final ItemMeta im = this.item.getItemMeta();
            if (this.name != null) {
                im.setDisplayName(CC.translate(this.name));
            }
            if (this.lore != null) {
                im.setLore((List)this.lore);
            }
            if (this.skullOwner != null) {
                ((SkullMeta)im).setOwner(this.skullOwner);
            }
            im.addItemFlags(this.flags);
            if (im instanceof LeatherArmorMeta && this.color != null) {
                ((LeatherArmorMeta)im).setColor(this.color);
            }
            this.item.setItemMeta(im);
        }
        this.enchants.keySet().forEach(e -> this.item.addUnsafeEnchantment(e, (int)this.enchants.get(e)));
        this.gui.getInventory().setItem(this.slot, this.item);
    }

    public Material getType() {
        return this.type;
    }

    public int getData() {
        return this.data;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public ItemFlag[] getFlags() {
        return this.flags;
    }

    public HashMap<Enchantment, Integer> getEnchants() {
        return this.enchants;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Runnable getLeftClick() {
        return this.leftClick;
    }

    public Runnable getRightClick() {
        return this.rightClick;
    }

    public Runnable getClick() {
        return this.click;
    }
}

