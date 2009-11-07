package bvira.services;

import bvira.model.Annotation;
import bvira.model.ChangeSpecification;
import static bvira.model.Field.Content;
import static bvira.model.Field.ResourceReference;
import static bvira.model.Field.UserReference;
import bvira.model.Identity;
import bvira.model.Specification;
import bvira.persistance.AnnotationRepository;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultAnnotationServiceTests {

    @Test
    public void canCreateAnAnnotation() {
        Specification specification = new Specification();

        DefaultAnnotationService service = new DefaultAnnotationService(mock(AnnotationRepository.class));

        Annotation annotation = service.create(specification);

        Assert.assertNotNull(annotation);
    }

    @Test
    public void serviceStoresNewAnnotationsInRepository() {
        Specification specification = new Specification();

        AnnotationRepository repository = mock(AnnotationRepository.class);

        DefaultAnnotationService service = new DefaultAnnotationService(repository);

        Annotation annotation = service.create(specification);

        verify(repository).set(annotation);
    }

    @Test
    public void serviceCreatesAnnotationWithAllDataFieldsPopulated() {
        Specification specification = new Specification() {
            {
                field(ResourceReference, "Some resource reference");
                field(UserReference, "Some user reference");
                field(Content, "Some content");
            }
        };

        AnnotationRepository repository = mock(AnnotationRepository.class);
        DefaultAnnotationService service = new DefaultAnnotationService(repository);
        Annotation annotation = service.create(specification);

        Assert.assertEquals("Some resource reference", annotation.getResourceReference());
        Assert.assertEquals("Some user reference", annotation.getUserReference());
        Assert.assertEquals("Some content", annotation.getContent());
    }

    @Test
    public void serviceCanModifyAnnotationResouceReference() {
        AnnotationRepository repository = mock(AnnotationRepository.class);
        Identity testIdentity = new Identity() {
        };

        ChangeSpecification specification = new ChangeSpecification(testIdentity) {
            {
                field(ResourceReference, "two");
            }
        };

        DefaultAnnotationService service = new DefaultAnnotationService(repository);

        Annotation expectedAnnotation = new Annotation();
        expectedAnnotation.setResourceReference("one");

        when(repository.get(testIdentity)).thenReturn(expectedAnnotation);

        Annotation annotation = service.modify(specification);

        Assert.assertEquals("two", annotation.getResourceReference());
    }

    @Test
    public void serviceCanModifyAnnotationUserReference() {
        AnnotationRepository repository = mock(AnnotationRepository.class);
        Identity testIdentity = new Identity() {
        };

        ChangeSpecification specification = new ChangeSpecification(testIdentity) {
            {
                field(UserReference, "user2");
            }
        };

        DefaultAnnotationService service = new DefaultAnnotationService(repository);

        Annotation expectedAnnotation = new Annotation();
        expectedAnnotation.setUserReference("user1");

        when(repository.get(testIdentity)).thenReturn(expectedAnnotation);

        Annotation annotation = service.modify(specification);

        Assert.assertEquals("user2", annotation.getUserReference());
    }

    @Test
    public void serviceCanModifyAnnotationContent() {
        AnnotationRepository repository = mock(AnnotationRepository.class);
        Identity testIdentity = new Identity() {
        };

        ChangeSpecification specification = new ChangeSpecification(testIdentity) {
            {
                field(Content, "new content");
            }
        };

        DefaultAnnotationService service = new DefaultAnnotationService(repository);

        Annotation expectedAnnotation = new Annotation();
        expectedAnnotation.setResourceReference("original content");

        when(repository.get(testIdentity)).thenReturn(expectedAnnotation);

        Annotation annotation = service.modify(specification);

        Assert.assertEquals("new content", annotation.getContent());
    }

    @Test
    public void modifiedAnnotationsAreSavedToRepository() {
        AnnotationRepository repository = mock(AnnotationRepository.class);

        Annotation annotation = new Annotation();

        DefaultAnnotationService service = new DefaultAnnotationService(repository);
        Identity identity = new Identity() {
        };
        when(repository.get(identity)).thenReturn(annotation);

        service.modify(new ChangeSpecification(identity));

        verify(repository).set(annotation);
    }

    @Test
    public void serviceDelegatesDeletionToRepository() {
        AnnotationRepository repository = mock(AnnotationRepository.class);

        DefaultAnnotationService service = new DefaultAnnotationService(repository);
        Identity testIdentity = new Identity() {
        };
        service.delete(testIdentity);

        verify(repository).delete(testIdentity);
    }

    @Test
    public void serviceDelegatesToRepositoryForFindByIdentity() {
        AnnotationRepository repository = mock(AnnotationRepository.class);
        Identity identity = new Identity() {
        };

        DefaultAnnotationService service = new DefaultAnnotationService(repository);

        service.find(identity);

        verify(repository).get(identity);
    }

}
