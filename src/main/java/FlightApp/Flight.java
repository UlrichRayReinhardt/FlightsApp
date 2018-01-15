package FlightApp;

import Location.City;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Flight {

    //TODO Номер рейсу IATA code http://www.iata.org/services/pages/codes.aspx

    private String company;
    private Direction direction;
    private final AirPlaneType airPlane;
    private String id;
    private double ticketPrice;

    public Flight(String id, String company, City from, City to, AirPlaneType airPlane) { //TODO filter for 2 the same cities
        this.company = company;
        this.direction = new Direction(from, to);
        this.airPlane = airPlane;
        this.id = direction.getDirectionCode() + "_" + id;
        setTicketPrice();
    }

    public String getId() {
        return id;
    }

    public Flight(String company, Direction direction, AirPlaneType airPlane) {
        this.company = company;
        this.direction = direction;
        this.airPlane = airPlane;
        this.id = direction.getDirectionCode() + "_" + id;
        setTicketPrice();
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
       return company + " " +
       id + " " +
                direction.departure.getName() + " " +
                direction.destination.getName() + " " +
                airPlane.name() + " " +
                "#ticketPrice " + formatter.format(ticketPrice) + "$";
    }

    public double getDistance() {
        return direction.getDistance();
    }

    public void setTicketPrice() {
        this.ticketPrice = direction.getDistance() * 0.6;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getCompany() {
        return company;
    }




}
