package bvira.util;

import java.io.Closeable;
import java.io.IOException;

public class Using {
    public static <T extends Closeable> void using(T resource, Block<T> block) {
        try {
            block.yield(resource);
        } finally {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
