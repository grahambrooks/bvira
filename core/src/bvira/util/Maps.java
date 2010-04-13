package bvira.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {
    private Maps() {
    }

    public static <K, V> Map<K, V> create() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> create(K key, V value) {
        Map<K, V> map = create();
        map.put(key, value);
        return map;
    }

    public static <K, V> Map<K, V> createOrdered() {
        return new LinkedHashMap<K, V>();
    }

    public static <K, V> Map<K, V> createOrdered(K key, V value) {
        Map<K, V> map = createOrdered();
        map.put(key, value);
        return map;
    }
}
