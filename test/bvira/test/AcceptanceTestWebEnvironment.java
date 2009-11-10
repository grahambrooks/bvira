package bvira.test;

import bvira.model.Field;
import bvira.model.ResponseContext;
import bvira.test.abstraction.DriverAdaptor;
import bvira.test.abstraction.For;
import bvira.test.abstraction.HtmlPage;
import bvira.test.abstraction.HTTPInteraction;
import bvira.test.abstraction.StaticDriverAdaptor;
import bvira.test.abstraction.To;
import bvira.web.ContextPath;
import bvira.web.ContextRoot;
import bvira.web.Parameters;
import bvira.model.RequestContext;
import bvira.web.RequestUri;
import bvira.web.TemplateWriter;
import bvira.web.WebApplication;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class AcceptanceTestWebEnvironment extends WebEnvironment {
    WebApplication application;
    private String responseText;

    public AcceptanceTestWebEnvironment() {
        application = new WebApplication();

    }

    public WebEnvironment navigate(final To uri) {
        RequestContext requestContext = new RequestContext() {
            public ContextRoot getContextRoot() {
                return null;
            }

            public ContextPath getContextPath() {
                return null;
            }

            public RequestUri getRequestUri() {
                return new RequestUri(uri.getPath(), "");
            }

            public Parameters getParameters() {
                return null;
            }

            public String getParameter(Field identity) {
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
        };
        application.executePresenter(requestContext, responseContext);

        this.responseText = writer.toString();
        return this;
    }

    public <T extends HtmlPage> T responsePage(Class<T> pageClass) {
        try {
            return pageClass.getConstructor(DriverAdaptor.class).newInstance(new StaticDriverAdaptor(responseText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WebEnvironment start() {
        return this;
    }

    public void stop() {
    }

    public WebEnvironment clickLink(String linkText) {
        throw new UnsupportedOperationException("User operations are not supported for Acceptance tests");
    }

    public List<HTTPInteraction> findHttpInteractions(For clause) {
        throw new UnsupportedOperationException("getInteractions not supported for acceptance tests");
    }

    public void clearHttpInteractionLog() {
        throw new UnsupportedOperationException("Clearing interactions is not suported");
    }
}
