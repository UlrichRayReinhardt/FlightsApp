package FlightApp;

public class FlightContext {

    private String company;
    private String departure;
    private String destination;
    private String airPlane;

    public FlightContext(String company, String departure, String destination, String airPlane) {
        this.company = company;
        this.departure = departure;
        this.destination = destination;
        this.airPlane = airPlane;
    }

    public String getCompany() {
        return company;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getAirPlane() {
        return airPlane;
    }
}
