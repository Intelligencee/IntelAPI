package me.intelligence.intelapi.util;

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

public class ItemBuilder {

    private Material type;
    private int data;
    private int amount;
    private String name;
    private List<String> lore;
    private ItemFlag[] flags;
    private HashMap<Enchantment, Integer> enchants;
    private String skullOwner;
    private Color color;
    private ItemStack item;

    public ItemBuilder() {
        this.data = 0;
        this.amount = 1;
        this.name = null;
        this.lore = new ArrayList<String>();
        this.flags = new ItemFlag[0];
        this.enchants = new HashMap<Enchantment, Integer>();
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder type(final Material type) {
        this.type = type;
        return this;
    }

    public ItemBuilder data(final int data) {
        this.data = data;
        return this;
    }

    public ItemBuilder amount(final int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder lore(final String... lore) {
        for (final String s : lore) {
            this.lore.add(CC.translate(s));
        }
        return this;
    }

    public ItemBuilder lore(final List<String> lore) {
        lore.forEach(s -> this.lore.add(CC.translate(s)));
        return this;
    }

    public ItemBuilder flag(final ItemFlag... flags) {
        this.flags = flags;
        return this;
    }

    public ItemBuilder enchant(final Enchantment enchant, final int level) {
        this.enchants.put(enchant, level);
        return this;
    }

    public ItemBuilder skullOwner(final String s) {
        this.skullOwner = s;
        return this;
    }

    public ItemBuilder item(final ItemStack item) {
        this.item = item;
        return this;
    }

    public ItemBuilder color(final Color color) {
        this.color = color;
        return this;
    }

    public ItemStack build() {
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
            im.addItemFlags(this.flags);
            if (this.skullOwner != null) {
                ((SkullMeta)im).setOwner(this.skullOwner);
            }
            if (im instanceof LeatherArmorMeta && this.color != null) {
                ((LeatherArmorMeta)im).setColor(this.color);
            }
            this.item.setItemMeta(im);
        }
        this.enchants.keySet().forEach(e -> this.item.addUnsafeEnchantment(e, (int)this.enchants.get(e)));
        return this.item;
    }
}
