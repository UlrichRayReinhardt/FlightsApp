package Jedis_db;

import FlightApp.AirPlaneType;
import FlightApp.DuplicateException;
import FlightApp.Flight;
import Location.City;
import Location.Location;

import java.util.*;
import java.util.stream.Collectors;

public class DB {
    //TODO create db to hold all the info. used only for 1st init. Current class is temporary
    private static DB dbInstance;
    /*private HashSet<Flight> flights = null;
    private HashMap<String, Location> cityGeo = null;*/


    public DB() {
        /*flights = new HashSet<>();
        cityGeo = new HashMap<>();*/
    }

    public static synchronized DB getDbInstance() {
        if (dbInstance == null)
            dbInstance = new DB();
        return dbInstance;
    }

    public List<City> getCityList() {
        return new ArrayList<>();
    }

    /*public List<Flight> getFlightsList() {
        return new ArrayList<>(flights);
    }

    public void addCity(String city, Location location) {
        cityGeo.put(city,location);
    }*/

    /*public void addFlight(String company, String from, String to, AirPlaneType airPlane) {
        try {
            if (from.equals(to))
                throw new DuplicateException("Departure and destination cities couldn't be the same");
            flights.add(new Flight(company, from, to, airPlane));
        } catch (DuplicateException e) {
            System.out.println(e.toString() + "\nFlight " + from + " to " + to + " doesn't added");
        }
    }*/

  /*  public List<Flight> getFlightsOfCompany(String company) {
        return flights.stream().filter(x -> x.getCompany() == company).collect(Collectors.toList());
    }

    public List<Flight> getFlightsFrom(String from) {
        return flights.stream().filter(x -> x.getDeparture() == from).collect(Collectors.toList());
    }

    public List<Flight> getFlightsTo(String to) {
        return flights.stream().filter(x -> x.getDestination() == to).collect(Collectors.toList());
    }*/

    public void printFlightList(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        System.out.println("-----------------");
    }

    /*public void printCityList() {
        for (City city : cities) {
            System.out.println(city.toString());
        }
        System.out.println("-----------------");
    }*/
}
