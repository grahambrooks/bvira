package bvira.components;

import bvira.model.Office;
import bvira.model.Presenter;
import bvira.persistance.Finder;
import bvira.model.RequestContext;
import bvira.model.ResponseContext;
import bvira.web.TemplateFactory;
import bvira.web.TemplateWriter;

public class OfficeDetailsPresenter implements Presenter {
    private TemplateFactory templateFactory;
    private Finder<Office> officeFinder;

    public OfficeDetailsPresenter(TemplateFactory templateFactory, Finder<Office> officeFinder) {
        this.templateFactory = templateFactory;
        this.officeFinder = officeFinder;
    }

    public void present(RequestContext requestContext, ResponseContext responseContext) {
        TemplateWriter writer = templateFactory.templateFor(OfficeDetailsPresenter.class);
        writer.setProperty("title", "Office details - bvira");

        writer.setProperty("offices", officeFinder.findAll());

        responseContext.writeTemplate(writer);

    }
}
