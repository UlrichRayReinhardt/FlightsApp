import FlightApp.FlightBuilder;
import Jedis_db.JedisController;
import Location.City;
import redis.clients.jedis.JedisPool;

import static FlightApp.AirPlaneType.AIRBUS_A330;
import static FlightApp.AirPlaneType.EMBRAER_195;

public class BackUpData {

     public BackUpData(JedisPool pool) {
        JedisController controller = new JedisController(pool);
        City london = new City("London", "51.5073509", "-0.1277583");
        City paris = new City("Paris", "48.856614", "2.3522219");
        City dubai = new City("Dubai", "25.2048493", "55.2707828");
        City kyiv = new City("Kyiv", "50.4501", "30.5234");
        City riga = new City("Riga", "56.9496487", "24.1051865");
        City tokyo = new City("Tokyo", "35.6894875", "139.6917064");
        controller.addAll(london,paris,dubai,kyiv,riga,tokyo);

        controller.addToRedis(
                new FlightBuilder()
                        .setCompany("BA")
                        .setDeparture(london)
                        .setDestination(kyiv)
                        .setAirplane(AIRBUS_A330.toString())
                        .build());

        controller.addToRedis(
                new FlightBuilder()
                        .setCompany("BA")
                        .setDeparture(london)
                        .setDestination(tokyo)
                        .setAirplane(EMBRAER_195.toString())
                        .build());


        /*controller.addToRedis( new FlightBuilder()("BA", "London", "Riga", AirPlaneType.EMBRAER_195.toString(), controller));
        controller.addToRedis( new FlightBuilder()("BA", "London", "Tokyo", AirPlaneType.EMBRAER_190.toString(), controller));
        controller.addToRedis(FlightContextBuilder.createFromContext("BA", "London", "Paris", AirPlaneType.AIRBUS_A320.toString(), controller));
        controller.addToRedis(FlightContextBuilder.createFromContext("UI", "Dubai", "London", AirPlaneType.BOEING_737.toString(), controller));
        controller.addToRedis(FlightContextBuilder.createFromContext("UI", "Kyiv", "Riga", AirPlaneType.BOEING_737.toString(), controller));
        controller.addToRedis(FlightContextBuilder.createFromContext("UI", "Kyiv", "Paris", AirPlaneType.BOEING_737.toString(), controller));
        controller.addToRedis(FlightContextBuilder.createFromContext("UI", "Kyiv", "Dubai", AirPlaneType.BOEING_737.toString(), controller));*/
    }
}