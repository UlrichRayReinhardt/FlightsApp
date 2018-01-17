package FlightApp;

public class FlightCode {

    public static String generateCode(String company, String from, String to, AirPlaneType plane) {
        return from.charAt(0) + "" +
                to.charAt(0) + "_" +
                company.hashCode() % 10 + "" +
                plane.hashCode() % 10 + "" +
                from.hashCode() % 10 + "" +
                to.hashCode() % 10;
    }


}
