package Jedis_db;

import Location.City;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisController {

    JedisPool pool;

    public JedisController(JedisPool pool) {
        this.pool = pool;
    }

    public void addCityRedis(String key, double lat, double lng) {
        Jedis jedis = null;
        Map<String, String> city = new HashMap<>();
        city.put("name", key);
        city.put("lat", String.valueOf(lat));
        city.put("lng", String.valueOf(lng));
        try {
            jedis = pool.getResource();
            jedis.hmset(key, city);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void removeCityRedis(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public City getCityRedis(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> retrieveMap = jedis.hgetAll(key);
            return new City(
                    retrieveMap.get("name"),
                    Double.valueOf(retrieveMap.get("lat")),
                    Double.valueOf(retrieveMap.get("lng")));
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void allCitiesToDB() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Set<String> names = jedis.keys("*");
            java.util.Iterator<String> it = names.iterator();

            while (it.hasNext()) {
                DB.getDbInstance().addCity(getCityRedis(it.next()));
            }
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
   }
}
