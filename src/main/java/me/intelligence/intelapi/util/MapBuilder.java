package me.intelligence.intelapi.util;

import java.util.HashMap;

public class MapBuilder<T1, T2> {

    private HashMap<T1, T2> map;

    public MapBuilder() {
        this.map = new HashMap<T1, T2>();
    }

    public MapBuilder<T1, T2> put(final T1 k, final T2 v) {
        this.map.put(k, v);
        return this;
    }

    public HashMap<T1, T2> build() {
        return this.map;
    }

    public static MapBuilder<String, Object> get() {
        return new MapBuilder<String, Object>();
    }
}
