package Jedis_db;

import FlightApp.Flight;
import Location.City;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class JedisController {

    JedisPool pool;

    public JedisController(JedisPool pool) {
        this.pool = pool;
    }

    public void addToRedis(City city) {
        Jedis jedis = null;
        Map<String, String> data = new HashMap<>();
        data.put("name", city.getName());
        data.put("latitude", city.getLatitude());
        data.put("longitude", String.valueOf(city.getLongitude()));
        try {
            jedis = pool.getResource();
            jedis.hmset("city:" + city.getName(), data);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void addToRedis(Flight flight) {
        Jedis jedis = pool.getResource();
        Map<String, String> data = new HashMap<>();
        data.put("company", flight.getCompany());
        data.put("departure", flight.getDeparture());
        data.put("destination", flight.getDestination());
        data.put("airplane", flight.getAirPlane());
        data.put("distance", String.valueOf(flight.getDistance()));
        data.put("id", flight.getId());
        data.put("ticketPrice", flight.getId());
        jedis.hmset("flight:" + flight.getId(), data);
    }


    public void removeCity(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del("city:" + key);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void removeFlight(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del("flight:" + key);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void clear() {
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            jedis.del(key);
        }
    }

    public City getCityFromRedis(String name) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> city = jedis.hgetAll("city:" + name);
            String lat = city.get("latitude");
            String lng = city.get("longitude");
            return new City(name, lat, lng);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }

    }

    public Flight getFlightFromRedis(String name) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> data = jedis.hgetAll("flight:" + name);
            String company = data.get("company");
            String departure = data.get("departure");
            String destination = data.get("destination");
            String airplane = data.get("airplane");
            String distance = data.get("distance");
            String id = data.get("id");
            String price = data.get("ticketPrice");
            return new Flight(company, departure, destination, airplane, distance, id, price);

        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public List<String> getFlightStringList() {
        List<String> list = new ArrayList<>();
        Jedis jedis = pool.getResource();
        Set<String> data = jedis.keys("flight:" + "*");
        list.addAll(data);
        return list;
    }

    public List<String> getCityList() {
        List<String> list = new ArrayList<>();
        Jedis jedis = pool.getResource();
        Set<String> names = jedis.keys("city:*");
        for (String name : names) {
            City city = getCityFromRedis(name);
            String cleanName = city.getName().replaceAll("city:", "");
            list.add(cleanName);

        }
        return list;
    }

}
