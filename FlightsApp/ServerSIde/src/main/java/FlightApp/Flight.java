package FlightApp;

import Location.City;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Flight {
    private String company;
    private Direction direction;
    private final AirPlaneType airPlane;
    private String id;
    private double ticketPrice;

    public Flight(String company, City from, City to, AirPlaneType airPlane) {
        this.company = company;
        this.direction = new Direction(from, to);
        this.airPlane = airPlane;
        this.id = generateCode();
        setTicketPrice();
    }

    public String getId() {
        return id;
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

    private String generateCode() {
        return direction.getDesignatorsCode() + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                direction.hashCode() % 10 + "" +
                direction.getDesignatorsCode().hashCode() % 10;
    }


}
