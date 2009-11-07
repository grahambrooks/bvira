package bvira.model;

public class IdentityFactory {
    public static Identity parse(String value) {
        return new SimpleIdentity(value);
    }
}
