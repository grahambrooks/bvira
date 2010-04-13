package bvira.util;

public interface Container {
    <T> T getInstance(Class<T> aClass);

    Container register(Class<?>... classes);

    Container register(Object... objects);

    Container transientContainer();
}
