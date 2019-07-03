package me.intelligence.intelapi.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    public static String serialize(final Location loc) {
        if (loc == null) {
            return null;
        }
        return loc.getWorld().getName() + "///" + loc.getX() + "///" + loc.getY() + "///" + loc.getZ() + "///" + loc.getYaw() + "///" + loc.getPitch();
    }

    public static Location deserialize(final String s) {
        if (s == null) {
            return null;
        }
        final String[] split = s.split("///");
        final Location l = new Location(Bukkit.getWorld(split[0]), p(split[1]), p(split[2]), p(split[3]));
        l.setYaw(f(split[4]));
        l.setPitch(f(split[5]));
        return l;
    }

    private static double p(final String s) {
        return Double.parseDouble(s);
    }

    private static float f(final String s) {
        return Float.parseFloat(s);
    }
}