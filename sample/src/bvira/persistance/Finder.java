package bvira.persistance;

import java.util.List;

public interface Finder<T> {
    List<T> findAll();
}
