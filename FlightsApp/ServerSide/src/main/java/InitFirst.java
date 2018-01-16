package sample;

import Jedis_db.DB;
import Location.City;

import static FlightApp.AirPlaneType.*;

public class MainFlights {

    public static void run() {

        DB db = DB.getDbInstance();
        db.addCity(new City("London", 51.5073509, -0.1277583));
        db.addCity(new City("Paris", 48.856614, 2.3522219));
        db.addCity(new City("Dubai", 25.2048493, 55.2707828));
        db.addCity(new City("Kyiv", 50.4501, 30.5234));
        db.addCity(new City("Riga", 56.9496487, 24.1051865));
        db.addCity(new City("Tokyo", 35.6894875, 139.6917064));
        System.out.println("=====================\nCities initialized\n=====================");

        db.addFlight("BA", db.getCity("London"), db.getCity("Kyiv"), EMBRAER_170);
        db.addFlight("BA", db.getCity("London"), db.getCity("Kyiv"), EMBRAER_170);
        db.addFlight("BA", db.getCity("London"), db.getCity("Riga"), EMBRAER_195);
        db.addFlight("BA", db.getCity("London"), db.getCity("Tokyo"), EMBRAER_190);
        db.addFlight("BA", db.getCity("London"), db.getCity("Paris"), AIRBUS_A320);
        db.addFlight("UI", db.getCity("Dubai"), db.getCity("London"), BOEING_737);
        db.addFlight("UI", db.getCity("Kyiv"), db.getCity("Riga"), BOEING_737);
        db.addFlight("UI", db.getCity("Kyiv"), db.getCity("Paris"), BOEING_737);
        db.addFlight("UI", db.getCity("Kyiv"), db.getCity("Dubai"), BOEING_737);
        System.out.println("=====================\nFlights initialized\n=====================");
        /*List<Flight> brit = db.getFlightsOfCompany("BA");
        List<Flight> ukr = db.getFlightsOfCompany("UI");
        List<Flight> myList = new ArrayList<>();
        myList.addAll(ukr);
        myList.addAll(brit);
        Collections.sort(myList, ComparatorFlight.DistanceComparator);
        db.printFlightList(myList);

        List<Flight> fromKyiv = db.getFlightsFrom("Kyiv");
        List<Flight> toKyiv = db.getFlightsTo("Kyiv");

        db.printFlightList(fromKyiv);

        //System.exit(0);
    }


}
