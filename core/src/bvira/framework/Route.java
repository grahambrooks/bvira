package bvira.framework;

public interface Route {
    boolean match(String path);

    boolean match(Route route);
}
