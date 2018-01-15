package Jedis_db;

import FlightApp.AirPlaneType;
import FlightApp.DuplicateException;
import FlightApp.Flight;
import Location.City;

import java.util.*;
import java.util.stream.Collectors;

public class DB {
    //TODO create db to hold all the info. used only for 1st init. Current class is temporary
    private static DB dbInstance;
    private HashSet<City> cities = null;
    private HashSet<Flight> flights = null;

    public DB() {
        cities = new HashSet<>();
        flights = new HashSet<>();
    }

    public static synchronized DB getDbInstance() {
        if (dbInstance == null)
            dbInstance = new DB();
        return dbInstance;
    }

    public City getCity(String name) {
        for (City city : cities) {
            if (city.getName().equals(name))
                return city;
        }
        System.out.println("No city " + name + " in database. Please recheck name or add new City");
        return null;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void addFlight(String company, City from, City to, AirPlaneType airPlane) {
        try {
            if (from.equals(to))
                throw new DuplicateException("Departure and destination cities couldn't be the same");
            flights.add(new Flight(company, from, to, airPlane));
        } catch (DuplicateException e) {
            System.out.println(e.toString() + "\nFlight "+ from.getName() + " to " + to.getName() + " doesn't added");
        }
        }

        public List<Flight> getFlightsOfCompany (String company){
            return flights.stream().filter(x -> x.getCompany() == company).collect(Collectors.toList());
        }

        public List<Flight> getFlightsFrom (String from){
            return flights.stream().filter(x -> x.getDirection().getDeparture() == from).collect(Collectors.toList());
        }

        public List<Flight> getFlightsTo (String to){
            return flights.stream().filter(x -> x.getDirection().getDeparture() == to).collect(Collectors.toList());
        }

    public void printFlightList(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        System.out.println("-----------------");
    }

    public void printCityList() {
        for (City city : cities) {
            System.out.println(city.toString());
        }
        System.out.println("-----------------");
    }
}
