package bvira.persistance;

import bvira.model.Office;

import java.util.ArrayList;
import java.util.List;

public class StubOfficeFinder implements Finder<Office> {
    public List<Office> findAll() {
        List<Office> offices = new ArrayList<Office>();

        offices.add(new Office("Australia", 113));
        offices.add(new Office("Canada", 52));
        offices.add(new Office("China", 72));
        offices.add(new Office("India", 432));
        offices.add(new Office("Sweden", 1));
        offices.add(new Office("United Kingdom", 148));
        offices.add(new Office("United States", 200));

        return offices;
    }
}
