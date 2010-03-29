package bvira.components;

import bvira.framework.RequestContext;
import bvira.framework.ResponseContext;
import bvira.framework.Presenter;
import bvira.framework.TemplateFactory;
import bvira.framework.TemplateWriter;

public class HomePagePresenter implements Presenter {
    private TemplateFactory templateFactory;

    public HomePagePresenter(TemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    public void present(RequestContext requestContext, ResponseContext responseContext) {
        TemplateWriter writer = templateFactory.templateFor(HomePagePresenter.class);
        writer.setProperty("title", "Home - bvira");

        responseContext.writeTemplate(writer);
    }
}
