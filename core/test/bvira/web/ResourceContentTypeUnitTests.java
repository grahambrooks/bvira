package bvira.web;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResourceContentTypeUnitTests {
    @Test
    public void defaultContentTypeIsUtf8EncodeHtml() {
        ResourceContentType contentType = new ResourceContentType();

        assertThat(contentType.toString(), is("text/html;charset=UTF-8"));
    }
}
