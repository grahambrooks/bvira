package bvira.web;

import bvira.test.WebEnvironment;
import bvira.test.WorkflowTestRunner;
import bvira.test.abstraction.For;
import bvira.test.abstraction.HTTPInteraction;
import bvira.test.abstraction.To;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(WorkflowTestRunner.class)
public class OfficeDetailsWorkflowTests {
    @Test
    public void officeDetailsPageMakesFourRequestsToGoogle() {
        WebEnvironment environment = WebEnvironment.getInstance();

        environment.clearHttpInteractionLog();
        environment.navigate(To.path("/office/details"));

        List<HTTPInteraction> interactions = environment.findHttpInteractions(For.domain("www.google.com"));

        assertThat(interactions.size(), is(4));
    }
}
