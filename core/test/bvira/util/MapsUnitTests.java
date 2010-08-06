package bvira.util;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapsUnitTests {
    @Test
    public void orderedMapsAreOrderedByInsertionSequence(){
        Map<String, String> map = Maps.createOrdered();
        map.put("B", "A");
        map.put("A", "B");

        Iterator<String> iterator = map.keySet().iterator();

        assertThat(iterator.next(), is("B"));
        assertThat(iterator.next(), is("A"));

        Iterator<String> iterator1 = map.values().iterator();

        assertThat(iterator1.next(), is("A"));
        assertThat(iterator1.next(), is("B"));
    }
}
