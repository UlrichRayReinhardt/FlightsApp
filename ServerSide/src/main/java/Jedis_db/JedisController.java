package Jedis_db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class JedisController {

    JedisPool pool;

    public JedisController(JedisPool pool) {
        this.pool = pool;
    }

    public JedisController() {
    }




   public void clear() {
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            jedis.del(key);
        }
    }

      /*public Suite load(String name); {
        Jedis jedis = pool.getResource();
        jedis.smembers("suite:" + name);
        return new Suite(name, jedis.smembers("suite:" + name));
    }

    public void addToRedis(String suiteName, Set<String> flights) { //add new suite
        Jedis jedis = pool.getResource();
        for (String flightname : flights) {
            jedis.sadd("suite:" + suiteName, flightname);
        }
    }

    public void addToRedis(String suiteName, String... flights) { //add new suite
        Jedis jedis = pool.getResource();
        jedis.sadd("suite:" + suiteName, flights);

    }

    public void removeFromSuite(String suiteName, String flight) { //remove flight from suite
        Jedis jedis = pool.getResource();
        jedis.srem("suite:" + suiteName, flight);
    }
    public void removeFromSuite(Suite suite, String flight) { //remove flight from suite
        Jedis jedis = pool.getResource();
        jedis.srem("suite:" + suite.getName(), flight);
    }

    public void removeSuite(String suiteName) { //remove flight from suite
        Jedis jedis = pool.getResource();
        jedis.srem("suite:" + suiteName);
    }*/
}


