package bvira.web;

import bvira.framework.RequestUri;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceContentTypeUnitTests {
    @Test
    public void defaultContentTypeIsUtf8EncodeHtml() {
        ResourceContentType contentType = new ResourceContentType();

        assertThat(contentType.toString(), is("text/html;charset=UTF-8"));
    }

    @Test
    public void contentTypeReflectsConstructorParams() {
        RequestUri uri = mock(RequestUri.class);
        when(uri.getExtension()).thenReturn("txt");
        ResourceContentType contentType = ResourceContentType.fromRequest(uri);

        assertThat(contentType.toString(), is("text/text;charset=UTF-8"));
    }
}
