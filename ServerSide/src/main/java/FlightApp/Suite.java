package FlightApp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Suite<T extends Suitable> extends HashSet<T> {

    String suiteName;

    public Suite(String suiteName) {
        this.suiteName = suiteName;

    }

    public Suite(String suiteName, Set<T> set) {
        this.suiteName = suiteName;
        addAll(set);
    }



    public String getName() {
        return suiteName;
    }

    @Override
    public String toString() {
        return suiteName;
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }
}