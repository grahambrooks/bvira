package bvira.model;

public class ChangeSpecification extends Specification {

    private final Identity identity;

    public ChangeSpecification(Identity identity) {
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }
}
