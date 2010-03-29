package bvira.components;

import bvira.framework.Presenter;
import bvira.framework.RequestContext;
import bvira.framework.ResponseContext;
import bvira.framework.TemplateFactory;
import bvira.framework.TemplateWriter;

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
