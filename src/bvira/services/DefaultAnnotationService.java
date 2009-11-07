package bvira.services;

import bvira.model.Annotation;
import bvira.model.AnnotationService;
import bvira.model.ChangeSpecification;
import static bvira.model.Field.Content;
import static bvira.model.Field.ResourceReference;
import static bvira.model.Field.UserReference;
import bvira.model.Identity;
import bvira.model.Specification;
import bvira.persistance.AnnotationRepository;

public class DefaultAnnotationService implements AnnotationService {
    private final AnnotationRepository repository;

    public DefaultAnnotationService(AnnotationRepository repository) {
        this.repository = repository;
    }

    public Annotation find(Identity identity) {
        return repository.get(identity);
    }

    public Annotation create(Specification specification) {
        Annotation annotation = new Annotation();
        annotation.setResourceReference(specification.get(ResourceReference));
        annotation.setUserReference(specification.get(UserReference));
        annotation.setContent(specification.get(Content));
        repository.set(annotation);
        return annotation;
    }

    public void delete(Identity identity) {
        repository.delete(identity);
    }

    public Annotation modify(ChangeSpecification changeSpecification) {
        Annotation annotation = repository.get(changeSpecification.getIdentity());

        if (changeSpecification.contains(ResourceReference)) {
            annotation.setResourceReference(changeSpecification.get(ResourceReference));
        }

        if (changeSpecification.contains(UserReference)) {
            annotation.setUserReference(changeSpecification.get(UserReference));
        }

        if (changeSpecification.contains(Content)) {
            annotation.setContent(changeSpecification.get(Content));
        }
        repository.set(annotation);
        return annotation;
    }
}
