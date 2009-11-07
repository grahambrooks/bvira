package bvira.test.abstraction;

public class For {
	private final String domain;

	public For(String domain) {
		this.domain = domain;
	}

	public static For domain(String domain) {
		return new For(domain);
	}

	public String getDomain() {
		return domain;
	}

}
