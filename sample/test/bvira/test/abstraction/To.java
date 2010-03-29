package bvira.test.abstraction;

import bvira.framework.Navigable;

public class To implements Navigator {
	private final String path;

	public To(String path) {
		this.path = path;
	}

	public static To path(String path) {
		return new To(path);
	}

    public static To homePage() {
		return new To("/");
	}
	
	public static To loginPage() {
		return new To("/login");
	}

    public void navigate(Navigable navigable) {
        navigable.setPath(path);
    }
}
