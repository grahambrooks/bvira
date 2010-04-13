package bvira.util;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    private Lists() {
    }

    public static <K> List<K> create() {
        return new ArrayList<K>();
    }
}
