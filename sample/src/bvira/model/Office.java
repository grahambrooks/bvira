package bvira.model;

public class Office {
    private String region;
    private int consultants;

    public Office(String region, int consultants) {
        this.region = region;
        this.consultants = consultants;
    }

    public int getConsultants() {
        return consultants;
    }

    public String getRegion() {
        return region;

    }
}
