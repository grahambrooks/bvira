package bvira.components;

import bvira.model.ResponseContext;
import bvira.model.Presenter;
import bvira.web.TemplateFactory;
import bvira.model.RequestContext;
import bvira.web.TemplateWriter;

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
