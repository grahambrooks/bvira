package bvira.framework;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContextPathUnitTests {
    @Test
    public void contextPathStoresPath() {
        ContextPath path = new ContextPath("/some/path");

        assertThat(path.getValue(), is("/some/path"));
    }

    @Test
    public void contextPathStoresRootAsEmptyString() {
        ContextPath path = new ContextPath("/");

        assertThat(path.getValue(), is(""));
    }
}
