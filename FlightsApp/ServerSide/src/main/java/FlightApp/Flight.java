package FlightApp;

import Jedis_db.JedisController;
import Location.City;
import Location.Distance;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Flight {
    private String company;
    private String departure;
    private String destination;
    private double distance;
    private String designatorsCode;
    private String airPlane;
    private String id;
    private double ticketPrice;

    public Flight(FlightContext context, JedisController controller) {
        this.company = context.getCompany();
        this.departure = context.getDeparture();
        this.destination = context.getDestination();
        this.airPlane = context.getAirPlane();
        City from = controller.getCityFromRedis(departure);
        City to = controller.getCityFromRedis(destination);
        distance = Distance.getDistance(from, to);
        designatorsCode = context.getDeparture().charAt(0) + "" + context.getDestination().charAt(0);
        generateCode();
        setTicketPrice();
    }

    public Flight(String company, String departure, String destination, String airPlane, String distance, String code, String ticketPrice) {
        this.company = company;
        this.departure = departure;
        this.destination = destination;
        this.airPlane = airPlane;
        this.distance = Double.valueOf(distance);
        this.id = code;
        this.ticketPrice = Double.valueOf(ticketPrice);
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

    public String getAirPlane() {
        return airPlane;
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
                airPlane + " " +
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

    private void generateCode() {
        id = designatorsCode + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                departure.hashCode() % 10 + "" +
                designatorsCode.hashCode() % 10;
    }

}
