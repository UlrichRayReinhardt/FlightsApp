package FlightApp;

import Jedis_db.DB;
import Location.City;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Flight {
    private String company;
    private String departure;
    private String destination;
    private double distance;
    private String designatorsCode;

    private final AirPlaneType airPlane;
    private String id;
    private double ticketPrice;

    public Flight(String company, String departure, String destination, AirPlaneType airPlane) {
        this.company = company;
        this.departure = departure;
        this.destination = destination;
        this.airPlane = airPlane;
        this.id = generateCode();
        distance = Direction.getDistance(departure, destination);
        designatorsCode = departure.charAt(0) + "" + destination.charAt(0);
        setTicketPrice();
    }

    public double getDistance() {
        return distance;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return company + " " +
                id + " " +
                departure + " " +
                destination + " " +
                airPlane.name() + " " +
                "#ticketPrice " + formatter.format(ticketPrice) + "$";
    }

    private void setTicketPrice() {
        this.ticketPrice = distance * 0.6;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getCompany() {
        return company;
    }

    private String generateCode() {
        return designatorsCode + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                departure.hashCode() % 10 + "" +
                designatorsCode.hashCode() % 10;
    }

}
