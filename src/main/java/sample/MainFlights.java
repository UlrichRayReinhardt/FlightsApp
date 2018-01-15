package sample;

import FlightApp.ComparatorFlight;
import FlightApp.Flight;
import Jedis_db.DB;
import Location.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static FlightApp.AirPlaneType.*;

public class MainFlights {

     public static void run() {

        DB db = DB.getDbInstance();

        List<City> cityList = new ArrayList<>();
        cityList.add(new City("London", 51.5073509, -0.1277583));
        cityList.add(new City("Paris", 48.856614, 2.3522219));
        cityList.add(new City("Dubai", 25.2048493, 55.2707828));
        cityList.add(new City("Kyiv", 50.4501, 30.5234));
        cityList.add(new City("Riga", 56.9496487, 24.1051865));
        cityList.add(new City("Tokyo", 35.6894875, 139.6917064));
        for (City cityName : cityList) {
            db.addCity(cityName);
        }
        db.printCityList();

        db.addFlight("4645", "BA", db.getCity("London"), db.getCity("Kyiv"), EMBRAER_170);
        db.addFlight("2342", "BA", db.getCity("London"), db.getCity("Kyiv"), EMBRAER_170);
        db.addFlight("2346", "BA", db.getCity("London"), db.getCity("Riga"), EMBRAER_195);
        db.addFlight("6745", "BA", db.getCity("London"), db.getCity("Tokyo"), EMBRAER_190);
        db.addFlight("5434", "BA", db.getCity("London"), db.getCity("Paris"), AIRBUS_A320);
        db.addFlight("4645", "UI", db.getCity("Dubai"), db.getCity("London"), BOEING_737);
        db.addFlight("4861", "UI", db.getCity("Kyiv"), db.getCity("Riga"), BOEING_737);
        db.addFlight("3486", "UI", db.getCity("Kyiv"), db.getCity("Paris"), BOEING_737);
        db.addFlight("4864", "UI", db.getCity("Kyiv"), db.getCity("Dubai"), BOEING_737);


        List<Flight> brit = db.getFlightsOfCompany("BA");
        List<Flight> ukr = db.getFlightsOfCompany("UI");
        List<Flight> myList = new ArrayList<>();
        myList.addAll(ukr);
        myList.addAll(brit);
        Collections.sort(myList, ComparatorFlight.DistanceComparator);
        db.printFlightList(myList);


        List<Flight> fromKyiv = db.getFlightsFrom("Kyiv");
        db.printFlightList(fromKyiv);

        //System.exit(0);
    }


}
