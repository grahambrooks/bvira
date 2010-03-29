package bvira.test.abstraction;

import org.jdom.Text;

public class TextNode extends HtmlElement {

	private final Text text;

	public TextNode(Text text) {
		super(null);
		this.text = text;
	}

	public String getText() {
		return text.getText();
	}
}
