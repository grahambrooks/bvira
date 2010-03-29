package bvira.framework;

public class ContextPath {

    private final String value;

    public ContextPath(String value) {
        if ("/".equals(value)) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }

    public String join(String path) {
        return value + path;
    }
}
