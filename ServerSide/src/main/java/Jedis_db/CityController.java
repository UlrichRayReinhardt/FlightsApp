package Jedis_db;

import Location.City;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class CityController extends JedisController {

    JedisPool pool;
    private static Set<City> cities;

    public CityController(JedisPool pool) {
        this.pool = pool;
        cities = new HashSet<>();
        loadCities();
    }

    public static City getCity(String name) {
        return cities.stream().filter(city -> city.getName() == name).findFirst().get();
    }

    public void remove(String key) {
        Jedis jedis = pool.getResource();
        jedis.del("city:" + key);
    }

    public void add(City city) {
        Jedis jedis = pool.getResource();
        jedis.hmset("city:" + city.getName(), city.getProperties());
    }

    private void loadCity(String name) {
        Jedis jedis = pool.getResource();
        Map<String, String> city = jedis.hgetAll("city:" + name);
        String lat = city.get("latitude");
        String lng = city.get("longitude");
        cities.add(new City(name, lat, lng));
    }

    private void loadCities() {
        Jedis jedis = pool.getResource();
        Set<String> data = jedis.keys("city:*");
        for (String name : data) {
            loadCity(name);
        }

    }

}

