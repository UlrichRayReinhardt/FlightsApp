package FlightApp;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Flight { //class represents data in redis
    private String company;
    private String departure;
    private String destination;
    private String distance;
    private String airPlane;
    private String id;
    private String ticketPrice;

    public String getCompany() {
        return company;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDistance() {
        return distance;
    }

    public String getAirPlane() {
        return airPlane;
    }

    public String getId() {
        return id;
    }

    public String getInfo() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        double ticketTmp = Double.valueOf(ticketPrice);
        return "\n#" + company + " \n" +
                id + "\n" +
                departure + "\n" +
                destination + "\n" +
                airPlane + "\n" +
                "# " + formatter.format(ticketTmp) + "$";
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public Flight(FlightBuilder builder){
        this.company = builder.company;
        this.departure = builder.departure.getName();
        this.destination = builder.destination.getName();
        this.airPlane = builder.airPlane;
        this.distance = builder.distance();
        this.id = builder.id();
        this.ticketPrice = builder.ticketPrice();
    }
   }
