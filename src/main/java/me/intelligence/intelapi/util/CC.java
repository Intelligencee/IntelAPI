package me.intelligence.intelapi.util;

import org.bukkit.ChatColor;

public enum CC
{
    Good("§a§l(!) §a"),
    Welp("§e§l(!) §e"),
    Poor("§c§l(!) §c"),
    Black(ChatColor.BLACK.toString()),
    BlackB(ChatColor.BLACK + ChatColor.BOLD.toString()),
    BlackI(ChatColor.BLACK + ChatColor.ITALIC.toString()),
    BlackU(ChatColor.BLACK + ChatColor.UNDERLINE.toString()),
    DarkBlue(ChatColor.DARK_BLUE.toString()),
    DarkBlueB(ChatColor.DARK_BLUE + ChatColor.BOLD.toString()),
    DarkBlueI(ChatColor.DARK_BLUE + ChatColor.ITALIC.toString()),
    DarkBlueU(ChatColor.DARK_BLUE + ChatColor.UNDERLINE.toString()),
    DarkGreen(ChatColor.DARK_GREEN.toString()),
    DarkGreenB(ChatColor.DARK_GREEN + ChatColor.BOLD.toString()),
    DarkGreenI(ChatColor.DARK_GREEN + ChatColor.ITALIC.toString()),
    DarkGreenU(ChatColor.DARK_GREEN + ChatColor.UNDERLINE.toString()),
    DarkAqua(ChatColor.DARK_AQUA.toString()),
    DarkAquaB(ChatColor.DARK_AQUA + ChatColor.BOLD.toString()),
    DarkAquaI(ChatColor.DARK_AQUA + ChatColor.ITALIC.toString()),
    DarkAquaU(ChatColor.DARK_AQUA + ChatColor.UNDERLINE.toString()),
    DarkRed(ChatColor.DARK_RED.toString()),
    DarkRedB(ChatColor.DARK_RED + ChatColor.BOLD.toString()),
    DarkRedI(ChatColor.DARK_RED + ChatColor.ITALIC.toString()),
    DarkRedU(ChatColor.DARK_RED + ChatColor.UNDERLINE.toString()),
    DarkPurple(ChatColor.DARK_PURPLE.toString()),
    DarkPurpleB(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString()),
    DarkPurpleI(ChatColor.DARK_PURPLE + ChatColor.ITALIC.toString()),
    DarkPurpleU(ChatColor.DARK_PURPLE + ChatColor.UNDERLINE.toString()),
    Gold(ChatColor.GOLD.toString()),
    GoldB(ChatColor.GOLD + ChatColor.BOLD.toString()),
    GoldI(ChatColor.GOLD + ChatColor.ITALIC.toString()),
    GoldU(ChatColor.GOLD + ChatColor.UNDERLINE.toString()),
    Gray(ChatColor.GRAY.toString()),
    GrayB(ChatColor.GRAY + ChatColor.BOLD.toString()),
    GrayI(ChatColor.GRAY + ChatColor.ITALIC.toString()),
    GrayU(ChatColor.GRAY + ChatColor.UNDERLINE.toString()),
    DarkGray(ChatColor.DARK_GRAY.toString()),
    DarkGrayB(ChatColor.DARK_GRAY + ChatColor.BOLD.toString()),
    DarkGrayI(ChatColor.DARK_GRAY + ChatColor.ITALIC.toString()),
    DarkGrayU(ChatColor.DARK_GRAY + ChatColor.UNDERLINE.toString()),
    Blue(ChatColor.BLUE.toString()),
    BlueB(ChatColor.BLUE + ChatColor.BOLD.toString()),
    BlueI(ChatColor.BLUE + ChatColor.ITALIC.toString()),
    BlueU(ChatColor.BLUE + ChatColor.UNDERLINE.toString()),
    Green(ChatColor.GREEN.toString()),
    GreenB(ChatColor.GREEN + ChatColor.BOLD.toString()),
    GreenI(ChatColor.GREEN + ChatColor.ITALIC.toString()),
    GreenU(ChatColor.GREEN + ChatColor.UNDERLINE.toString()),
    Aqua(ChatColor.AQUA.toString()),
    AquaB(ChatColor.AQUA + ChatColor.BOLD.toString()),
    AquaI(ChatColor.AQUA + ChatColor.ITALIC.toString()),
    AquaU(ChatColor.AQUA + ChatColor.UNDERLINE.toString()),
    Red(ChatColor.RED.toString()),
    RedB(ChatColor.RED + ChatColor.BOLD.toString()),
    RedI(ChatColor.RED + ChatColor.ITALIC.toString()),
    RedU(ChatColor.RED + ChatColor.UNDERLINE.toString()),
    LightPurple(ChatColor.LIGHT_PURPLE.toString()),
    LightPurpleB(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString()),
    LightPurpleI(ChatColor.LIGHT_PURPLE + ChatColor.ITALIC.toString()),
    LightPurpleU(ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE.toString()),
    Yellow(ChatColor.YELLOW.toString()),
    YellowB(ChatColor.YELLOW + ChatColor.BOLD.toString()),
    YellowI(ChatColor.YELLOW + ChatColor.ITALIC.toString()),
    YellowU(ChatColor.YELLOW + ChatColor.UNDERLINE.toString()),
    White(ChatColor.WHITE.toString()),
    WhiteB(ChatColor.WHITE + ChatColor.BOLD.toString()),
    WhiteI(ChatColor.WHITE + ChatColor.ITALIC.toString()),
    WhiteU(ChatColor.WHITE + ChatColor.UNDERLINE.toString()),
    Bold(ChatColor.BOLD.toString()),
    Strike(ChatColor.STRIKETHROUGH.toString()),
    Underline(ChatColor.UNDERLINE.toString()),
    Magic(ChatColor.MAGIC.toString()),
    Italic(ChatColor.ITALIC.toString()),
    Reset(ChatColor.RESET.toString());

    private String s;

    private CC(final String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }

    public static String translate(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
