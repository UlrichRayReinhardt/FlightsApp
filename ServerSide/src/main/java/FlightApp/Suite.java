package FlightApp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Suite extends HashSet<String> {

    String suiteName;

    public Suite(String suiteName, Set<String> flightName) {
        this.suiteName = suiteName;
        addAll(flightName);
    }

    public String getName() {
        return suiteName;
    }

    @Override
    public String toString() {
        return suiteName;
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }
}