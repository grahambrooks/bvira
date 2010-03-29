package bvira.test.abstraction;

import java.util.List;

import org.openqa.selenium.WebElement;

public class HtmlPage  {

    private final DriverAdaptor driver;

    public HtmlPage(DriverAdaptor driver) {
        this.driver = driver;
    }

    public List<WebElement> findElementsById(String id) {
        return getElements(("//.[@id='" + id + "']"));
    }

    public WebElement findElementByLinkText(String linkText) {
        List<WebElement> anchors = findElementsByLinkText(linkText);
        if (anchors.size() > 0) {
            return anchors.get(0);
        } else {
            throw new ElementNotFoundException("Unable to find anchor with link text: " + linkText);
        }
    }

    public List<WebElement> findElementsByLinkText(String linkText) {
        return getElements(String.format("//a[text() = '%s']", linkText));
    }

    public WebElement findElementByPartialLinkText(String linkText) {
        List<WebElement> anchors = findElementsByPartialLinkText(linkText);
        if (anchors.size() > 0) {
            return anchors.get(0);
        } else {
            throw new ElementNotFoundException("Unable to find anchor with partial link text: " + linkText);
        }
    }

    public List<WebElement> findElementsByPartialLinkText(String linkText) {
        return getElements(String.format("//a[contains(text(), '%s')]", linkText));
    }

    public WebElement findElementByXPath(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        return getElements(xpath);
    }

    public List<WebElement> findElementsByName(String name) {
        return getElements("//input[@name='" + name + "']");
    }

    public List<WebElement> findElementsByTagName(String tagName) {
        return getElements("//" + tagName);
    }


    public WebElement findElementById(String id) {
        return driver.findElementById(id);
    }

    public WebElement getElementById(String id) {
        WebElement element = findElementById(id);
        if (element == null) {
            throw new ElementNotFoundException(String.format("Element with id %s not found while looking for single element with id", id));
        }
        return element;
    }

    public String getTitle() {
        return driver.getSingleElement("//title").getText();
    }

    @SuppressWarnings("unchecked")
    public List<WebElement> getElements(String xpath) {
        return driver.getElements(xpath);
    }

}
