package FlightApp;

import Jedis_db.StoreElement;
import Location.City;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class Flight extends StoreElement implements Suitable{ //class represents data in redis
    private final String name;
    private final String company;
    private final City departure;
    private final City destination;
    private AirPlaneType airPlane;
    private final double distance;
    private double ticketPrice;

    public String getInfo() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return "#" + name + " " +
                company + " " +
                departure + " " +
                destination + " " +
                airPlane + " " +
                " ticket " + formatter.format(ticketPrice) + "$";
    }

    /*return new FlightBuilder().setCompany(data.get("company"))
                .setDeparture(getCityFromRedis(data.get("departure")))
                .setDestination(getCityFromRedis(data.get("destination")))
                .setAirplane(data.get("airplane"))
                .build();
                }
                */

    public Flight(FlightBuilder builder) {
        this.name = builder.name();
        this.company = builder.company;
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.airPlane = AirPlaneType.valueOf(builder.airPlane);
        this.distance = builder.distance;
        this.ticketPrice = builder.ticketPrice;
    }

    public Map<String, String> getProperties() { //uses for serializable and put to redis
        Map<String, String> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("company", company);
        properties.put("departure", departure.getName());
        properties.put("destination", destination.getName());
        properties.put("airplane", airPlane.toString());
        properties.put("distance", String.valueOf(distance));
        properties.put("ticketPrice", String.valueOf(ticketPrice));
        return properties;
    }
}
