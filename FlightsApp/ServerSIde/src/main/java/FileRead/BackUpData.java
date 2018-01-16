package FileRead;

import Jedis_db.JedisController;
import redis.clients.jedis.JedisPool;

public class BackUpData {

    JedisPool pool;

    public BackUpData(JedisPool pool) {
        JedisController controller = new JedisController(pool);
        controller.addToRedis("London", 51.5073509, -0.1277583);
        controller.addToRedis("Paris", 48.856614, 2.3522219);
        controller.addToRedis("Dubai", 25.2048493, 55.2707828);
        controller.addToRedis("Kyiv", 50.4501, 30.5234);
        controller.addToRedis("Riga", 56.9496487, 24.1051865);
        controller.addToRedis("Tokyo", 35.6894875, 139.6917064);
    }}
