package bvira.web;

import bvira.framework.ParameterName;
import bvira.framework.RequestContext;
import bvira.framework.RequestUri;
import bvira.util.Maps;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class WebRequestContextUnitTests {
    @Test
    public void detailsExtractedFromFromHttpRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        doReturn("context").when(request).getContextPath();
        doReturn("root").when(request).getServerName();
        doReturn(8080).when(request).getServerPort();
        Map requestParameters  = Maps.create("foo", "bar1");
        doReturn(requestParameters).when(request).getParameterMap();
        RequestContext requestContext = WebRequestContext.create(request, new RequestUri("/some/path", "foo=bar"));

        assertThat(requestContext.getContextPath().getValue(), is("context"));
        assertThat(requestContext.getContextRoot().getValue(), is("http://root:8080"));
        assertThat(requestContext.getRequestUri().toString(), is("/some/path?foo=bar"));
        assertThat(requestContext.getParameter(new ParameterName("foo")), is("bar1"));
    }
}
