package bvira.test.abstraction;

public class To {
	private final String path;

	public To(String path) {
		this.path = path;
	}

	public static To path(String path) {
		return new To(path);
	}

	public String getPath() {
		return path;
	}

	public static To homePage() {
		return new To("/");
	}
	
	public static To loginPage() {
		return new To("/login");
	}
}
