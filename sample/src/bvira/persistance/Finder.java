package bvira.persistance;

import java.util.List;

public interface Finder<T> {
    public List<T> findAll();
}
