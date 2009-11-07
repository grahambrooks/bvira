package bvira.test.abstraction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebDriverAdaptor implements DriverAdaptor {
    private WebDriver driver;

    public WebDriverAdaptor(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public WebElement getSingleElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }
}
