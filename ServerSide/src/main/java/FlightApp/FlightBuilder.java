package FlightApp;

import Jedis_db.CityController;
import Location.City;
import Location.Distance;

public class FlightBuilder { //class builds new Flight

    String company;
    City departure, destination;
    String airPlane;
    double distance, ticketPrice;

    public FlightBuilder(String company, String departure, String destination, String airPlane) {
        this.company = company;
        this.departure = CityController.getCity(departure);
        this.destination = CityController.getCity(destination);
        this.airPlane = airPlane;
        checkDistance();
        setTicketPrice();
    }

    private void checkDistance() {
        distance = Distance.getDistance(departure, destination);
    }

    String name() {
        String tmp = departure.getName().charAt(0) + "" + destination.getName().charAt(0) + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                departure.hashCode() % 10 + "" +
                destination.hashCode() % 10;
        return tmp.replaceAll("-", "");
    }

    public Flight build() {
        return new Flight(this);
    }

    private void setTicketPrice() {
        ticketPrice = distance * 0.6;
    }

    public void setTicketPrice(double price) {
        ticketPrice = price;
    }
}
