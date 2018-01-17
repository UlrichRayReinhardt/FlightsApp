package FlightApp;

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

    public String getInfo() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return "\n#" + company + " \n" +
                id + "\n" +
                departure + "\n" +
                destination + "\n" +
                airPlane + "\n" +
                "# " + formatter.format(ticketPrice) + "$";
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getCompany() {
        return company;
    }


}
