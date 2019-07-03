package me.intelligence.intelapi.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TitleUtil {

    private static String nmsver;
    private static boolean useOldMethods;

    public static void sendTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, String title, String subtitle) {
        try {
            if (title != null) {
                title = CC.translate(title);
                title = title.replaceAll("%player%", player.getDisplayName());
                Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
                Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                Constructor subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                Object titlePacket = subtitleConstructor.newInstance(e, chatTitle, fadeIn, stay, fadeOut);
                sendPacket(player, titlePacket);
                e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
                chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"));
                titlePacket = subtitleConstructor.newInstance(e, chatTitle);
                sendPacket(player, titlePacket);
            }
            if (subtitle != null) {
                subtitle = CC.translate(subtitle);
                subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
                Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
                Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                Constructor subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                Object subtitlePacket = subtitleConstructor.newInstance(e, chatSubtitle, fadeIn, stay, fadeOut);
                sendPacket(player, subtitlePacket);
                e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
                subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                subtitlePacket = subtitleConstructor.newInstance(e, chatSubtitle, fadeIn, stay, fadeOut);
                sendPacket(player, subtitlePacket);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void sendPacket(final Player player, final Object packet) {
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getNMSClass(final String name) {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TitleUtil() {
        TitleUtil.nmsver = Bukkit.getServer().getClass().getPackage().getName();
        TitleUtil.nmsver = TitleUtil.nmsver.substring(TitleUtil.nmsver.lastIndexOf(".") + 1);
        if (TitleUtil.nmsver.equalsIgnoreCase("v1_8_R1") || TitleUtil.nmsver.equalsIgnoreCase("v1_7_")) {
            TitleUtil.useOldMethods = true;
        }
    }

    public static void sendActionBar(final Player player, final String message) {
        try {
            final Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + TitleUtil.nmsver + ".entity.CraftPlayer");
            final Object p = c1.cast(player);
            final Class<?> c2 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".PacketPlayOutChat");
            final Class<?> c3 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".Packet");
            Object ppoc;
            if (TitleUtil.nmsver.equalsIgnoreCase("v1_12_R1")) {
                final Class typeC = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".ChatMessageType");
                Object enom = null;
                for (final Object o : typeC.getEnumConstants()) {
                    if (o.toString().equalsIgnoreCase("GAME_INFO")) {
                        enom = o;
                    }
                }
                if (enom == null) {
                    return;
                }
                final Class c4 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".ChatComponentText");
                final Class c5 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".IChatBaseComponent");
                final Object o2 = c4.getConstructor(String.class).newInstance(message);
                ppoc = c2.getConstructor(c5, typeC).newInstance(o2, enom);
            }
            else if (TitleUtil.useOldMethods) {
                final Class c4 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".ChatSerializer");
                final Class c5 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".IChatBaseComponent");
                final Method m3 = c4.getDeclaredMethod("a", String.class);
                final Object pc = c5.cast(m3.invoke(c4, "{\"text\": \"" + message + "\"}"));
                ppoc = c2.getConstructor(c5, Byte.TYPE).newInstance(pc, 2);
            }
            else {
                final Class c4 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".ChatComponentText");
                final Class c5 = Class.forName("net.minecraft.server." + TitleUtil.nmsver + ".IChatBaseComponent");
                final Object o3 = c4.getConstructor(String.class).newInstance(message);
                ppoc = c2.getConstructor(c5, Byte.TYPE).newInstance(o3, 2);
            }
            final Method m4 = c1.getDeclaredMethod("getHandle", (Class<?>[])new Class[0]);
            final Object h = m4.invoke(p, new Object[0]);
            final Field f1 = h.getClass().getDeclaredField("playerConnection");
            final Object pc = f1.get(h);
            final Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c3);
            m5.invoke(pc, ppoc);
        }
        catch (Exception var13) {
            var13.printStackTrace();
        }
    }

    static {
        TitleUtil.useOldMethods = false;
    }
}
