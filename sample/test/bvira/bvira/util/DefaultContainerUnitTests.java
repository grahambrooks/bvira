package bvira.bvira.util;

import bvira.util.Container;
import bvira.util.DefaultContainer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultContainerUnitTests {
    @Test
    public void canInstantiateContainer() {
        new DefaultContainer();
    }

    @Test
    public void canRetrieveClassInstancesFromContainer() {
        Container container = new DefaultContainer();

        container.register(TestClass1.class);

        TestClass1 instance = container.getInstance(TestClass1.class);

        assertThat("Valid instance instance retrieved from container", instance, is(notNullValue()));
        container.dispose();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ThrowsExceptionWhenClassOrInstanceNotRegistered() {
        Container container = new DefaultContainer();

        container.getInstance(TestClass1.class);
        container.dispose();
    }

    @Test
    public void returnsSpecificRegisteredInstanceIfRegistered() {
        Container container = new DefaultContainer();

        TestClass1 testInstance = new TestClass1();

        container.register(testInstance);

        TestClass1 instance = container.getInstance(TestClass1.class);

        assertThat("Valid instance instance retrieved from container", instance, is(testInstance));

        container.dispose();
    }

    @Test
    public void transientContainerDefaultsToParent(){
        Container container = new DefaultContainer();

        TestClass1 testInstance = new TestClass1();

        container.register(testInstance);

        TestClass1 instance = container.transientContainer().getInstance(TestClass1.class);

        assertThat("Valid instance instance retrieved from container", instance, is(testInstance));

        container.dispose();
    }
    @Test

    public void transientContainerOverridesParent(){
        Container container = new DefaultContainer();

        container.register(new TestClass1());

        Container transientContainer = container.transientContainer();

        TestClass1 testInstance = new TestClass1();
        container.register(testInstance);
        TestClass1 instance = transientContainer.getInstance(TestClass1.class);

        assertThat("Valid instance instance retrieved from container", instance, is(testInstance));

        container.dispose();
    }

    @Test
    public void canTestForInstanceAvailability() {
        DefaultContainer container = new DefaultContainer();

        TestClass1 testInstance = new TestClass1();

        container.register(testInstance);

        assertThat("Valid instance instance retrieved from container", container.containsInstance(TestClass1.class), is(true));

        container.dispose();

    }
}
