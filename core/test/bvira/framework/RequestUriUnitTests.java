package bvira.framework;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestUriUnitTests {
    @Test
    public void requestUriDefaultsToEmptyValues() {
        RequestUri requestUri = new RequestUri();

        assertThat(requestUri.getPath(), is(""));
        assertThat(requestUri.getQuery(), is(""));
        assertThat(requestUri.getExtension(), is(""));
    }

    @Test
    public void requestUriReturnsExtension() {
        RequestUri requestUri = new RequestUri("some-resource.ext", "");

        assertThat(requestUri.getExtension(), is("ext"));
    }

    @Test
    public void requestUriRetainsPath() {
        RequestUri requestUri = new RequestUri("some-resource.ext", "");

        assertThat(requestUri.getPath(), is("some-resource.ext"));
    }
}
