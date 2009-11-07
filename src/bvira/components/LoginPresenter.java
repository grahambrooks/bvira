package bvira.components;

import bvira.web.Presenter;
import bvira.web.RequestContext;
import bvira.web.ResponseContext;
import bvira.web.TemplateFactory;
import bvira.web.TemplateWriter;

public class LoginPresenter implements Presenter {
    private TemplateFactory templateFactory;

    public LoginPresenter(TemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    public void present(RequestContext requestContext, ResponseContext responseContext) {
        TemplateWriter writer = templateFactory.templateFor(LoginPresenter.class);
        writer.setProperty("title", "Login - bvira");

        responseContext.writeTemplate(writer);
    }
}
