package bvira.util;

public interface Container {
    public <T> T getInstance(Class<T> aClass);

    public Container register(Class<?>... classes);

    public Container register(Object... objects);

    Container transientContainer();
}
