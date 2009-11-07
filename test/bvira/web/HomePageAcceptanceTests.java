package bvira.web;

import bvira.test.AcceptanceTestRunner;
import bvira.test.WebEnvironment;
import bvira.test.abstraction.HomePage;
import bvira.test.abstraction.To;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AcceptanceTestRunner.class)
public class HomePageAcceptanceTests {
    @Test
    public void homepageTitleIsSet() {
        HomePage page = WebEnvironment.getInstance().navigate(To.homePage()).responsePage(HomePage.class);

        assertThat(page.getTitle(), is("Home - bvira"));
    }
}
