package bvira.test.abstraction;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface DriverAdaptor  {
    List<WebElement> getElements(String xpath);
    WebElement findElementById(String id);

    WebElement getSingleElement(String xpath);
}
