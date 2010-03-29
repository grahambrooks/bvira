package bvira.test.abstraction;

import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StaticDriverAdaptor implements DriverAdaptor, SearchContext {
    private final Document document;
    private final String source;



    public StaticDriverAdaptor(String htmlText) {
        source = htmlText;
        SAXBuilder builder = new SAXBuilder();
        try {
            document = builder.build(new StringReader(source));
        } catch (Exception e) {
            throw new RuntimeException(("failure when trying to parse page in " + this.getClass().getName()), e);
        }
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

    public WebElement findElementByXPath(String xpath) {
        return findSingleElement(xpath);
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        return getElements(xpath);
    }

    public WebElement findElementByName(String name) {
        return findSingleElement("//input[@name='" + name + "']");
    }

    public List<WebElement> findElementsByName(String name) {
        return getElements("//input[@name='" + name + "']");
    }

    public WebElement findElementByTagName(String tagName) {
        return getElementByTagName(tagName);
    }

    private WebElement getElementByTagName(String tagName) {
        return findSingleElement("//" + tagName);
    }

    public List<WebElement> findElementsByTagName(String tagName) {
        return getElements("//" + tagName);
    }

    public List<WebElement> findElements(By by) {
        return by.findElements(this);
    }

    public WebElement findElement(By by) {
        return by.findElement(this);
    }

    public boolean elementExists(String id) {
        return elementExistsXpath(String.format("//*[@id='%s']", id));
    }

    public boolean elementExistsXpath(String xpath) {
        return elementExists(By.xpath(xpath));
    }

    public boolean elementExists(By by) {
        boolean exists = true;
        try {
            by.findElement(this);
        } catch (ElementNotFoundException e) {
            exists = false;
        }
        return exists;
    }

    public WebElement findSingleElement(String xpath) {
        try {
            Element node = (Element) XPath.selectSingleNode(getRoot(), xpath);

            if (node == null) {
                throw new ElementNotFoundException("Unable to find element by xpath: " + xpath);
            }

            return new HtmlElement(node);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement findElementById(String id) {
        WebElement element = findSingleElement(("//.[@id='" + id + "']"));

        if (element != null) {
            return element;
        } else {
            throw new ElementNotFoundException("");
        }
    }

    public WebElement getSingleElement(String xpath) {
        return findSingleElement(xpath);
    }

    public WebElement getElementById(String id) {
        WebElement element = findElementById(id);
        if (element == null) {
            throw new ElementNotFoundException(String.format("Element with id %s not found while looking for single element with id", id));
        }
        return element;
    }

    public String getDocument() {
        return source;
    }

    public List<String> getElementValues(String xpath) {
        List<String> values = new ArrayList<String>();
        List<Object> result = selectMultiElements(xpath);

        for (Object object : result) {
            if (object instanceof Content) {
                values.add(((Element) object).getValue());
            }
            if (object instanceof Attribute) {
                values.add(((Attribute) object).getValue());
            }
        }
        return values;
    }


    public List<String> getLinkHrefs() {
        return getElementValues("//a[@href]/@href");
    }

    public Element getRoot() {
        return document.getRootElement();
    }

    public String getSelectValueById(String id) {
        return findSingleElement(("//select[@id='" + id + "']/option[@selected]")).getAttribute("value");
    }

    public String getTextInputById(String id) {
        WebElement el = findSingleElement(("//input[@id='" + id + "' and @type='text']"));
        return el.getAttribute("value");
    }

    public String getTitle() {
        return findSingleElement("//title").getText();
    }

    @SuppressWarnings("unchecked")
    public List<Object> selectMultiElements(String xpath) {
        try {
            return XPath.selectNodes(getRoot(), xpath);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<WebElement> getElements(String xpath) {
        try {
            List<Element> nodes = XPath.selectNodes(document, xpath);
            List<WebElement> htmlElements = new ArrayList<WebElement>();
            for (Element element : nodes) {
                htmlElements.add(new HtmlElement(element));
            }
            return htmlElements;
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(String xpath) {
        return findSingleElement(xpath) != null;
    }

    public Link findLinkByLabelText(String label) {
        List<Link> links = this.getLinkElements();
        for (Link link : links) {
            if (label.equals(link.getValue())) {
                return link;
            }
        }
        throw new ElementNotFoundException("Could not find anchor with label: " + label);
    }

    private List<Link> getLinkElements() {
        List<WebElement> anchors = getElements("//a");

        List<Link> links = new ArrayList<Link>();
        for (WebElement anchor : anchors) {
            links.add(new Link(anchor));
        }
        return links;
    }


}
