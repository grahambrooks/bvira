package bvira.util;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.containers.TransientPicoContainer;


public class DefaultContainer implements Container {
    final MutablePicoContainer pico;

    public DefaultContainer() {
        pico = new DefaultPicoContainer();
    }

    public DefaultContainer(DefaultContainer parent) {
        pico = new TransientPicoContainer(parent.pico);
    }

    public <T> T getInstance(Class<T> aClass) {
        T instance = pico.getComponent(aClass);
        if (instance == null) {
            throw new IllegalArgumentException("Cannot find instance of " + aClass);
        }
        return instance;
    }

    public Container register(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            pico.addComponent(clazz);
        }
        return this;
    }

    public Container register(Object... objects) {
        for (Object o : objects) {
            unregister(o.getClass());
            pico.addComponent(o);
        }
        return this;
    }

    private void unregister(Class<?> clazz) {
        for (Class type : clazz.getInterfaces()) {
            pico.removeComponent(type);
        }
        pico.removeComponent(clazz);
    }

    public void dispose() {
        pico.dispose();
    }

    public Container registerSpecificInstance(Class<?> interfaceClass, Object instance) {
        pico.addComponent(interfaceClass, instance);
        return this;
    }

    public Container registerSpecificImplementation(Class<?> interfaceClass, Class<?> implementation) {
        pico.addComponent(interfaceClass, implementation);
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

    public Container transiantContainer() {
        return new DefaultContainer(this);
    }
}
