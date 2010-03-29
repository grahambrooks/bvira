package bvira.util;

public interface Container {


    public <T> T getInstance(Class<T> aClass);

    public Container register(Class<?>... classes);

    public Container register(Object... objects);

    public void dispose();

    public Container registerSpecificInstance(Class<?> interfaceClass, Object instance);

    public Container registerSpecificImplementation(Class<?> interfaceClass, Class<?> implementation);

    public <T> boolean containsInstance(Class<T> classInstance);

    Container transientContainer();
}
