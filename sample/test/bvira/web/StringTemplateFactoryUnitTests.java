package bvira.web;

import bvira.components.HomePagePresenter;
import bvira.framework.TemplateWriter;
import bvira.framework.UnrecoverableErrorException;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class StringTemplateFactoryUnitTests {
    @Test
    public void canInstantiateFactory() {
        new StringTemplateFactory();
    }

    @Test
    public void canRetrieveTemplateForClass() {
        StringTemplateFactory factory = new StringTemplateFactory();

        TemplateWriter writer = factory.templateFor(HomePagePresenter.class);

        assertThat(writer, is(notNullValue()));
    }

    @Test(expected = UnrecoverableErrorException.class)
    public void writerThrowsExceptionIfThereIsAnIOError() throws IOException {
        StringTemplateFactory factory = new StringTemplateFactory();

        TemplateWriter writer = factory.templateFor(HomePagePresenter.class);
        Writer writer1 = mock(Writer.class);
        doThrow(new IOException()).when(writer1).write(anyString());

        writer.write(writer1);

        assertThat(writer, is(notNullValue()));

    }
}
