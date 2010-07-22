package bvira.web;

import bvira.framework.ContextPath;
import bvira.framework.ContextRoot;
import bvira.framework.ParameterName;
import bvira.framework.Parameters;
import bvira.framework.RequestContext;
import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;
import bvira.framework.TemplateWriter;
import bvira.util.DefaultContainer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleHomePageAcceptanceTests {
    @Test
    public void homePageTitleIsBviraHome() throws JDOMException, IOException {

        RequestContext requestContext = new RequestContext() {
            public ContextRoot getContextRoot() {
                return null;
            }

            public ContextPath getContextPath() {
                return null;
            }

            public RequestUri getRequestUri() {
                return new RequestUri("/", "");
            }

            public Parameters getParameters() {
                return null;
            }

            public String getParameter(ParameterName identity) {
                return null;
            }
        };

        final StringWriter writer = new StringWriter();

        ResponseContext responseContext = new ResponseContext() {
            public PrintWriter getWriter() {
                return null;
            }

            public void writeTemplate(TemplateWriter template) {
                template.write(writer);
            }

            public void redirectTo(String path) {
            }

            public void validateResponse() {
            }
        };

        WebApplication webApplication = new WebApplication(HttpRequestHandler.getComponents(), new DefaultContainer(), new StringTemplateFactory(), HttpRequestHandler.services);

        webApplication.executePresenter(requestContext, responseContext);

        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new StringReader(writer.toString()));
        Element node = (Element) XPath.selectSingleNode(document.getRootElement(), "//title");
        assertThat(node.getText(), is("Home - bvira"));
    }
}
