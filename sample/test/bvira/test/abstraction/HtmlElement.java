package bvira.test.abstraction;

import org.antlr.stringtemplate.StringTemplate;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HtmlElement implements WebElement, SearchContext, FindsById,
        FindsByXPath, FindsByTagName, FindsByLinkText, FindsByName {

    private final Element element;
    private static final String NOT_SUPPORTED = "Not supported";
    private static final String NOT_YET_IMPLEMENTED = "Not yet implemented.";
    private static final String CHECKED = "checked";

    public HtmlElement(Element element) {
        this.element = element;
    }

    public WebElement findElementByTagName(String tagName) {
        return getElementOfType(tagName);
    }

    public List<WebElement> findElementsByTagName(String s) {
        return getElementsOfType(s);
    }

    public void click() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public void submit() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public void sendKeys(CharSequence... charSequences) {

        StringBuilder keys = new StringBuilder();
        for (CharSequence charSequence : charSequences) {
            keys.append(charSequence.toString());
        }

        element.setAttribute("value", keys.toString());
    }

    public void clear() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public String getTagName() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public String getElementName() {
        return element.getName();
    }

    public WebElement findElementByLinkText(String linkText) {
        List<WebElement> anchors = this.findElements(By.tagName("a"));
        for (WebElement anchor : anchors) {
            if (anchor.getText().equals(linkText)) {
                return anchor;
            }
        }
        throw new ElementNotFoundException("Unable to find anchor with link text: " + linkText);
    }

    public List<WebElement> findElementsByLinkText(String s) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    public WebElement findElementByPartialLinkText(String s) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    public List<WebElement> findElementsByPartialLinkText(String s) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    public WebElement findElementByName(String s) {
        return getElementWithAttribute("name", s);
    }

    public List<WebElement> findElementsByName(String s) {
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    public String getAttribute(String attributeName) {
        return element.getAttributeValue(attributeName);
    }

    public boolean toggle() {
        if (isSelected()) {
            element.removeAttribute(CHECKED);
            return false;
        } else {
            element.setAttribute(CHECKED, CHECKED);
            return true;
        }
    }

    public boolean isSelected() {
        return element.getAttributeValue(CHECKED) != null;
    }

    public void setSelected() {

        String name = element.getName();
        String selector;
        String attributeName;
        if ("option".equals(name)) {
            selector = name;
            attributeName = "selected";
        } else if ("input".equals(name) && "radio".equals(element.getAttribute("type").getValue())) {
            selector = "input[@type='radio']";
            attributeName = CHECKED;
        } else {
            throw new UnsupportedOperationException("Unsupported for " + name);
        }

        try {
            Element oldSelectedElement = (Element) XPath.selectSingleNode(this.element, String.format("./../%s[@%s='%s']", selector, attributeName, attributeName));
            if (oldSelectedElement != null) {
                oldSelectedElement.removeAttribute(attributeName);
            }
        } catch (JDOMException e) {
            throw new TestFrameworkException(e);
        }

        element.setAttribute(attributeName, attributeName);
    }

    public boolean isEnabled() {
        return element.getAttributeValue("disabled") == null;
    }

    public List<WebElement> findElements(By by) {
        return by.findElements(this);
    }

    public WebElement findElement(By by) {
        return by.findElement(this);
    }

    public HtmlElement getElementWithAttribute(String name, String value) {
        return selectSingleNode(String.format(".//*[@%s='%s']", name, value));
    }

    public HtmlElement getElementOfType(String type) {
        return selectSingleNode(".//" + type);
    }

    public List<WebElement> getElementsOfType(String type) {
        return selectMultiHtmlElements(".//" + type);
    }

    public boolean hasElementWithName(String name) {
        try {
            selectSingleNode(String.format(".//*[@name='%s']", name));
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    public String getText() {
        return element.getTextTrim();
    }

    public String getValue() {
        return element.getValue();
    }

    public List<WebElement> getChildElements() {
        return toHtmlElements(element.getChildren());
    }

    private HtmlElement selectSingleNode(String xpath) {
        try {
            Object node = XPath.selectSingleNode(this.element, xpath);

            if (node == null) {
                throw new ElementNotFoundException("Unable to find element matching xpath: " + xpath);
            } else if (node instanceof Element) {
                return new HtmlElement((Element) node);
            } else if (node instanceof Text) {
                return new TextNode((Text) node);
            }
            throw new ElementNotFoundException("Unable to find element matching xpath: " + xpath);
        } catch (JDOMException e) {
            throw new TestFrameworkException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<WebElement> selectMultiHtmlElements(String xpath) {
        try {
            return toHtmlElements(XPath.selectNodes(element, xpath));
        } catch (JDOMException e) {
            throw new TestFrameworkException(e);
        }
    }

    private List<WebElement> toHtmlElements(List<Element> elements) {
        List<WebElement> htmlElements = new ArrayList<WebElement>();
        for (Element element : elements) {
            htmlElements.add(new HtmlElement(element));
        }
        return htmlElements;
    }

    private String containingWord(String attribute, String word) {
        return "contains(concat(' ',normalize-space(@" + attribute + "),' '),' " + word + " ')";
    }

    public WebElement findElementById(String id) {
        return selectSingleNode(findByIdXPath(id));
    }

    private String findByIdXPath(String id) {
        return ".//*[" + containingWord("id", id) + "]";
    }

    public List<WebElement> findElementsById(String id) {
        return Arrays.asList(findElementById(id));
    }

    public WebElement findElementByXPath(String xpath) {
        return selectSingleNode(xpath);
    }

    public List<WebElement> findElementsByXPath(String xpath) {
        return selectMultiHtmlElements(xpath);
    }

    @Override
    public String toString() {
        StringTemplate st = new StringTemplate("<$element.name$ $element.attributes:{attr|$attr.name$=\"$attr.value$\" }$/>");
        st.setAttribute("element", element);
        return st.toString();
    }
}
