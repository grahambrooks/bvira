package bvira.model;

public interface AnnotationService {
    public Annotation find(Identity identity);

    public Annotation create(Specification specification);

    public void delete(Identity identity);

    public Annotation modify(ChangeSpecification changeSpecification);
}
