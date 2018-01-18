package FlightApp;

import java.util.List;

public class Suite {

    String suiteName;
    Flight[] flights;

    public Suite(String suiteName, List<Flight> flightList) {
        this.suiteName = suiteName;
        this.flights = (Flight[]) flightList.toArray();
    }

    public Suite(String suiteName, Flight... flightList) {
        this.suiteName = suiteName;
        this.flights = flightList;
    }

    public Flight[] getList() {
        return flights;
    }

  /*  public byte[] getBytes() {
        return new byte[]{Arrays.};
    }*/

    public String getName() {
        return suiteName;
    }
}
