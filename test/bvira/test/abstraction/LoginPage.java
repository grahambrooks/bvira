package bvira.test.abstraction;

import org.openqa.selenium.WebElement;

public class LoginPage extends HtmlPage {
    public LoginPage(DriverAdaptor adaptor) {
        super(adaptor);
    }

    public WebElement username() {
        return this.getElementById("username");
    }

    public WebElement password() {
        return this.getElementById("password");
    }
}
