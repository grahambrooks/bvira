package bvira.model;

public enum Field {
    Identity("identity"),
    ResourceReference("resource-reference"),
    UserReference("user-reference"),
    Content("content");

    private String parameterName;

    private Field(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
