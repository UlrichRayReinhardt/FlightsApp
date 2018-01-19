package FlightApp;

import Location.City;
import Location.Distance;


public class FlightBuilder { //class builds new Flight

    String company;
    City departure;
    City destination;
    String airPlane;

    public FlightBuilder() {
    }

    public FlightBuilder setCompany(String company) {
        this.company = company;
        return this;
    }

    public FlightBuilder setDeparture(City departure) {
        this.departure = departure;
        return this;
    }

    public FlightBuilder setDestination(City destination) {
        this.destination = destination;
        return this;
    }

    public FlightBuilder setAirplane(String airPlane) {
        this.airPlane = airPlane;
        return this;
    }

    String distance() {
        return String.valueOf(Distance.getDistance(departure.getLocation(), destination.getLocation()));
    }

    String id() {
      String tmp = departure.codeLetter() + "" + destination.codeLetter() + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                departure.hashCode() % 10 + "" +
                destination.hashCode() % 10;
        return tmp.replaceAll("-", "");
    }

    String ticketPrice() {
        return String.valueOf(Distance.getDistance(departure.getLocation(), destination.getLocation()) * 0.6);
    }

    public Flight build() {
        return new Flight(this);
    }


}
