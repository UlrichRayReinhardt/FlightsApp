package FileRead;

import Jedis_db.JedisController;
import Location.City;
import redis.clients.jedis.JedisPool;

public class BackUpData {
    /*public BackUpData(JedisPool pool) {
        JedisController controller = new JedisController(pool);
        controller.addToRedis("London", 51.5073509, -0.1277583));
        controller.addToRedis("Paris", 48.856614, 2.3522219);
        controller.addToRedis("Dubai", 25.2048493, 55.2707828);
        controller.addToRedis("Kyiv", 50.4501, 30.5234);
        controller.addToRedis("Riga", 56.9496487, 24.1051865);
        controller.addToRedis("Tokyo", 35.6894875, 139.6917064);

        controller.addToRedis("BA", "London", "Kyiv", EMBRAER_170);
        controller.addToRedis("BA", "London", "Riga", EMBRAER_195);
        controller.addToRedis("BA", "London", "Tokyo", EMBRAER_190);
        controller.addToRedis("BA", "London", "Paris", AIRBUS_A320);
        controller.addToRedis("UI", "Dubai", "London", BOEING_737);
        controller.addToRedis("UI", "Kyiv", "Riga", BOEING_737);
        controller.addToRedis("UI", "Kyiv", "Paris", BOEING_737);
        controller.addToRedis("UI", "Kyiv", "Dubai", BOEING_737);
    }*/

    public BackUpData(JedisPool pool) {
        JedisController controller = new JedisController(pool);
        controller.addToRedis(new City("London", 51.5073509, -0.1277583));
        controller.addToRedis(new City("Paris", 48.856614, 2.3522219));
        controller.addToRedis(new City("Dubai", 25.2048493, 55.2707828));
        controller.addToRedis(new City("Kyiv", 50.4501, 30.5234));
        controller.addToRedis(new City("Riga", 56.9496487, 24.1051865));
        controller.addToRedis(new City("Tokyo", 35.6894875, 139.6917064));

        /*controller.addToRedis(new Flight("BA", "London", "Kyiv", EMBRAER_170.toString()));
        controller.addToRedis(new Flight("BA", "London", "Riga", EMBRAER_195.toString()));
        controller.addToRedis(new Flight("BA", "London", "Tokyo", EMBRAER_190.toString()));
        controller.addToRedis(new Flight("BA", "London", "Paris", AIRBUS_A320.toString()));
        controller.addToRedis(new Flight("UI", "Dubai", "London", BOEING_737.toString()));
        controller.addToRedis(new Flight("UI", "Kyiv", "Riga", BOEING_737.toString()));
        controller.addToRedis(new Flight("UI", "Kyiv", "Paris", BOEING_737.toString()));
        controller.addToRedis(new Flight("UI", "Kyiv", "Dubai", BOEING_737.toString()));*/
    }}