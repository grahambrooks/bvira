package bvira.util;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.containers.TransientPicoContainer;


public class DefaultContainer implements Container {
    final MutablePicoContainer picoContainer;

    public DefaultContainer() {
        picoContainer = new DefaultPicoContainer();
    }

    public DefaultContainer(DefaultContainer parent) {
        picoContainer = new TransientPicoContainer(parent.picoContainer);
    }

    public <T> T getInstance(Class<T> aClass) {
        T instance = picoContainer.getComponent(aClass);
        if (instance == null) {
            throw new IllegalArgumentException("Cannot find instance of " + aClass);
        }
        return instance;
    }

    public Container register(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            picoContainer.addComponent(clazz);
        }
        return this;
    }

    public Container register(Object... objects) {
        for (Object o : objects) {
            remove(o.getClass());
            picoContainer.addComponent(o);
        }
        return this;
    }

    private void remove(Class<?> clazz) {
        for (Class type : clazz.getInterfaces()) {
            picoContainer.removeComponent(type);
        }
        picoContainer.removeComponent(clazz);
    }

    public void dispose() {
        picoContainer.dispose();
    }

    public Container registerSpecificInstance(Class<?> interfaceClass, Object instance) {
        picoContainer.addComponent(interfaceClass, instance);
        return this;
    }

    public Container registerSpecificImplementation(Class<?> interfaceClass, Class<?> implementation) {
        picoContainer.addComponent(interfaceClass, implementation);
        return this;
    }

    public <T> boolean containsInstance(Class<T> classInstance) {
        try {
            getInstance(classInstance);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Container transientContainer() {
        return new DefaultContainer(this);
    }
}
