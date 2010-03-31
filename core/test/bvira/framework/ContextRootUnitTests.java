package bvira.framework;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ContextRootUnitTests {
    @Test
    public void contextRootFormatsHostAndPort() {
        ContextRoot root = new ContextRoot("somehost", 80);

        assertThat(root.getValue(), is("http://somehost:80"));
    }
}
