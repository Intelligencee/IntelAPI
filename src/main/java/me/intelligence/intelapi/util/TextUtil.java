package me.intelligence.intelapi.util;

public class TextUtil {

    public static String combine(final String[] args, final int start, final int end) {
        return combine(args, start, end, " ");
    }

    public static String combine(final String[] args, final int start, int end, final String sep) {
        String s = "";
        if (end > args.length || end < 0) {
            end = args.length;
        }
        for (int i = start; i < end; ++i) {
            s = s + (s.equals("") ? "" : sep) + args[i];
        }
        return CC.translate(s);
    }
}
