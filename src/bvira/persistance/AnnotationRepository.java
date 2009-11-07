package bvira.persistance;

import bvira.model.Annotation;
import bvira.model.Identity;

public interface AnnotationRepository {

    Annotation get(Identity identity);

    void set(Annotation annotation);

    void delete(Identity identity);
}
