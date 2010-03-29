package bvira.web;

import bvira.test.WebEnvironment;
import bvira.test.WorkflowTestRunner;
import bvira.test.abstraction.HomePage;
import bvira.test.abstraction.LoginPage;
import bvira.test.abstraction.To;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WorkflowTestRunner.class)
public class LoginWorkflowTests {
    @Test
    public void usersCanLogInWithoutJavaScript() throws InterruptedException {
        WebEnvironment environment = WebEnvironment.getInstance();
        
		HomePage page = environment
                .navigate(To.homePage())
                .responsePage(HomePage.class);

        assertThat(page.getTitle(), is("Home - bvira"));

        environment.clickLink("login");
        LoginPage loginPage = environment.responsePage(LoginPage.class);

        assertThat(loginPage.getTitle(), is("Login - bvira"));
    }
}
