package Jedis_db;

import FlightApp.AirPlaneType;
import FlightApp.Flight;
import Location.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DB {
    //TODO create db to hold all the info. used only for 1st init. Current class is temporary

    private static DB dbInstance;
    private HashMap<String, City> cities = null;
    private HashMap<String, Flight> flights = null;

    public DB() {
        cities = new HashMap<>();
        flights = new HashMap<>();

    }

    public static synchronized DB getDbInstance() {
        if (dbInstance == null)
            dbInstance = new DB();

        return dbInstance;
    }

    public City getCity(String name) {
        if (cities.containsKey(name))
            return cities.get(name);
        else {
            System.out.println("No city " + name + " in database. Please recheck name or add new City");
            return null;
        }
    }

    public void addCity(City city) {
        cities.putIfAbsent(city.getName(), city);
    }

    public List<City> getCitiesList() {
        return new ArrayList<>(cities.values());
    }

    private void addFlight(String id, Flight flight) {
        flights.putIfAbsent(id, flight);
    }

    public void addFlight(String id, String company, City from, City to, AirPlaneType airPlane) {
        addFlight(id, (new Flight(id, company, from, to, airPlane)));
    }

    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    public List<Flight> getFlightsOfCompany(String company) {
        return flights.values().stream().filter(x -> x.getCompany() == company).collect(Collectors.toList());
    }

    public List<Flight> getFlightsFrom(String from) {
        return flights.values().stream().filter(x -> x.getDirection().getDeparture() == from).collect(Collectors.toList());
    }

    public void printFlightList(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        System.out.println("-----------------");
    }

    public void printCityList() {
        for (Map.Entry<String, City> entry : cities.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        System.out.println("-----------------");
    }
}
