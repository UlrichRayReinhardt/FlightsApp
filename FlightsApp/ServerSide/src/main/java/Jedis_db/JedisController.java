package Jedis_db;

import FlightApp.Flight;
import FlightApp.FlightBuilder;
import Location.City;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class JedisController {

    JedisPool pool;

    public JedisController(JedisPool pool) {
        this.pool = pool;
    }

    public void addAll(City... c) {
        for (City city : c) {
            addToRedis(city);
        }
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
        data.put("ticketPrice", flight.getTicketPrice());
        data.put("distance", flight.getDistance());
        data.put("airplane", flight.getAirPlane());
        data.put("destination", flight.getDestination());
        data.put("departure", flight.getDeparture());
        data.put("company", flight.getCompany());
        data.put("id", flight.getId());
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
            return new FlightBuilder().setCompany(data.get("company"))
                    .setDeparture(getCityFromRedis(data.get("departure")))
                    .setDestination(getCityFromRedis(data.get("destination")))
                    .setAirplane(data.get("airplane"))
                    .build();
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public List<Flight> getFlightList() {
        List<Flight> list = new ArrayList<>();
        Jedis jedis = pool.getResource();
        Set<String> data = jedis.keys("flight:" + "*");
        for (String name : data) {
            String cleanName = name.replaceAll("flight:", "");
            Flight flight = getFlightFromRedis(cleanName);
            list.add(flight);
        }
        return list;
    }


    public List<String> getCityList() {
        List<String> list = new ArrayList<>();
        Jedis jedis = pool.getResource();
        Set<String> data = jedis.keys("city:*");
        for (String name : data) {
            City city = getCityFromRedis(name);
            String cleanName = city.getName().replaceAll("city:", "");
            list.add(cleanName);

        }
        return list;
    }

}
