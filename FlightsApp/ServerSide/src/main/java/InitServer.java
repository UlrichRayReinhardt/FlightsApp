import FileRead.BackUpData;
import Jedis_db.DB;
import Jedis_db.JedisController;
import Jedis_db.RedisThreat;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static FlightApp.AirPlaneType.*;

public class InitServer {

    public static void run() {
        new RedisThreat().run();
        DB db = DB.getDbInstance();
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        JedisController controller = new JedisController(pool);

        controller.allCitiesToDB();
        if (db.getCityList().isEmpty()) {
            new BackUpData(pool);
            controller.allCitiesToDB();
        }

        System.out.println("=====================\nCities initialized\n=====================");


        db.addFlight("BA", "London", "Kyiv", EMBRAER_170);
        db.addFlight("BA", "London", "Riga", EMBRAER_195);
        db.addFlight("BA", "London", "Tokyo", EMBRAER_190);
        db.addFlight("BA", "London", "Paris", AIRBUS_A320);
        db.addFlight("UI", "Dubai", "London", BOEING_737);
        db.addFlight("UI", "Kyiv", "Riga", BOEING_737);
        db.addFlight("UI", "Kyiv", "Paris", BOEING_737);
        db.addFlight("UI", "Kyiv", "Dubai", BOEING_737);
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

        db.printFlightList(fromKyiv);*/
    }


}
