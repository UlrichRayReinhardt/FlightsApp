import Jedis_db.JedisController;
import Jedis_db.RedisThreat;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class InitServer {

    public static void run() {
        new RedisThreat().run();
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        JedisController controller = new JedisController(pool);
        //new BackUpData(pool);
        //controller.getCityFromRedis("London");
        /*if (db.getCityList().isEmpty() | db.getFlightsList().isEmpty()) {
            new BackUpData(pool);*/
            //controller.readCitiesFromRedis();

        System.out.println("Cities initialized\n=====================");
        System.out.println("Flights initialized\n=====================");
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
