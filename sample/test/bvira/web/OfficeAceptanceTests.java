package bvira.web;

import bvira.test.AcceptanceTestRunner;
import bvira.test.WebEnvironment;
import bvira.test.abstraction.OfficeDetailsPage;
import bvira.test.abstraction.To;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AcceptanceTestRunner.class)
public class OfficeAceptanceTests {
    @Test
    public void pageTitleIsAppropriate() {
        OfficeDetailsPage page = WebEnvironment.getInstance()
                .navigate(To.officeDetailsPage())
                .responsePage(OfficeDetailsPage.class);

        assertThat(page.getTitle(), is("Office details - bvira"));

    }
}
