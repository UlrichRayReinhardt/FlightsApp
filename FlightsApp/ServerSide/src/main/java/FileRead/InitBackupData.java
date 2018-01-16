package FileRead;

import Jedis_db.JedisController;
import redis.clients.jedis.JedisPool;

public class InitBackupData {

    JedisPool pool;

    public InitBackupData(JedisPool pool) {
        JedisController controller = new JedisController(pool);
        controller.addCityRedis("London", 51.5073509, -0.1277583);
        controller.addCityRedis("Paris", 48.856614, 2.3522219);
        controller.addCityRedis("Dubai", 25.2048493, 55.2707828);
        controller.addCityRedis("Kyiv", 50.4501, 30.5234);
        controller.addCityRedis("Riga", 56.9496487, 24.1051865);
        controller.addCityRedis("Tokyo", 35.6894875, 139.6917064);
    }}
