package bvira.web;

import bvira.test.AcceptanceTestRunner;
import bvira.test.WebEnvironment;
import bvira.test.abstraction.LoginPage;
import bvira.test.abstraction.To;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AcceptanceTestRunner.class)
public class LoginAcceptanceTests {
    @Test
    public void loginTitleIsAppropriate() {
        LoginPage page = WebEnvironment.getInstance()
                .navigate(To.loginPage())
                .responsePage(LoginPage.class);

        assertThat(page.getTitle(), is("Login - bvira"));
    }

    @Test
    public void loginPageContainsUsernameAndPasswordFields() {
        LoginPage page = WebEnvironment.getInstance()
                .navigate(To.loginPage())
                .responsePage(LoginPage.class);

        WebElement username = page.username();
        assertThat(username.getAttribute("name"), is("username"));

        WebElement password = page.password();

        assertThat(password.getAttribute("name"), is("password"));
        assertThat(password.getAttribute("type"), is("password"));
    }
}
