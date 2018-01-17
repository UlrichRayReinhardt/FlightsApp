package FlightApp;

import Jedis_db.JedisController;
import Location.City;
import Location.Distance;


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

    public static Flight createFromContext(String company, String departure, String destination, String airPlane, JedisController controller) {
        return createFromContext(new FlightContext(company, departure, destination, airPlane), controller);
    }

    public static Flight createFromContext(FlightContext context, JedisController controller) {
        return new Flight(
                context.getCompany(),
                context.getDeparture(),
                context.getDestination(),
                context.getAirPlane(),
                context.countDistance(controller.getCityFromRedis(context.getDeparture()), controller.getCityFromRedis(context.getDestination())),
                context.generateCode(context),
                context.generateTicketPrice(controller.getCityFromRedis(context.getDeparture()), controller.getCityFromRedis(context.getDestination())));
    }


    private String countDistance(City from, City to) {
        return String.valueOf(Distance.getDistance(from.getLocation(), to.getLocation()));
    }

    private String generateCode(FlightContext context) {
        return departure.charAt(0) + "" + destination.charAt(0) + "_" +
                company.hashCode() % 10 + "" +
                airPlane.hashCode() % 10 + "" +
                departure.hashCode() % 10 + "" +
                destination.hashCode() % 10;
    }

    private String generateTicketPrice(City from, City to) {
        return String.valueOf(Distance.getDistance(from.getLocation(), to.getLocation()) * 0.6);
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
