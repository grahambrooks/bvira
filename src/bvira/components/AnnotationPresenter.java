package bvira.components;

import bvira.model.Annotation;
import bvira.model.Field;
import bvira.model.Identity;
import bvira.model.IdentityFactory;
import bvira.persistance.AnnotationRepository;
import bvira.web.Presenter;
import bvira.web.RequestContext;
import bvira.web.ResponseContext;
import bvira.web.TemplateFactory;

public class AnnotationPresenter implements Presenter {
    private final AnnotationRepository annotationRepository;
    private final TemplateFactory templateWriter;

    public AnnotationPresenter(AnnotationRepository annotationRepository, TemplateFactory templateFactory) {

        this.annotationRepository = annotationRepository;
        this.templateWriter = templateFactory;
    }

    public void present(RequestContext requestContext, ResponseContext responseContext) {
        Identity annotationIdentity = IdentityFactory.parse(requestContext.getParameter(Field.Identity));

        Annotation annotation = annotationRepository.get(annotationIdentity);

        templateWriter.write(this.getClass(), responseContext.getWriter(), "annotation", annotation);
    }
}
