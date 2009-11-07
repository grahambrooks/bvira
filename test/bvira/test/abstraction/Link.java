package bvira.test.abstraction;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.ArrayList;

public class Link {
    private final WebElement htmlElement;

    public Link(WebElement htmlElement) {
        this.htmlElement = htmlElement;
    }

    public String getHref() {
        return htmlElement.getAttribute("href");
    }

    public String getValue() {
        return htmlElement.getValue();
    }

    public String getClassName() {
    	return htmlElement.getAttribute("class");
    }

    public String getText() {
        return htmlElement.getText();
    }

    public boolean isPopup() {
        return "_blank".equals(htmlElement.getAttribute("target"));
    }
    public String getTitle(){
    	return htmlElement.getAttribute("title");
    }

    public boolean isInclude() {
        return cssClassContains("include");
    }

    private boolean cssClassContains(String className) {
        String classValue = htmlElement.getAttribute("class");
        if (classValue != null) {
            return classValue.contains(className);
        }
        return false;
    }

    public Boolean isDisabled() {
        return cssClassContains("disabled");
    }

    public Boolean isDisplayNone() {
    	return cssClassContains("display: none");
    }

    public boolean isVisible() {
        return !htmlElement.getAttribute("class").contains("hidden");
    }

    public static List<Link> create(List<WebElement> elementList) {
        List<Link> links = new ArrayList<Link>();
        for (WebElement webElement : elementList) {
            links.add(new Link(webElement));
        }

        return links;
    }

    public String getRel() {
        return htmlElement.getAttribute("rel");
    }

	public String getTarget() {
		return htmlElement.getAttribute("target");
	}

	public String href() {
		return htmlElement.getAttribute("href");
	}

    public String getId() {
        return htmlElement.getAttribute("id");
    }

    public WebElement getTrackingSpan(){
        return htmlElement.findElement(By.xpath("./following-sibling::*[@class = 'tracking']"));
    }
}
