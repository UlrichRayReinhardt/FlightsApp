package Jedis_db;

import FlightApp.Flight;
import FlightApp.FlightContext;
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

    public void addToRedis(City city) {
        Jedis jedis = null;
        Map<String, String> data = new HashMap<>();
        data.put("name", city.getName());
        data.put("latitude", String.valueOf(city.getLatitude()));
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
        Jedis jedis = null;
        Map<String, String> data = new HashMap<>();
        data.put("id", flight.getId());
        data.put("company", flight.getCompany());
        data.put("departure", flight.getDeparture());
        data.put("destination", flight.getDestination());
        data.put("airplane", flight.getAirPlane());
        data.put("distance", String.valueOf(flight.getDistance()));
        try {
            jedis = pool.getResource();
            jedis.hmset("flight:" + flight.getId(), data);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
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
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Set<String> names = jedis.keys("*");
            java.util.Iterator<String> it = names.iterator();

            while (it.hasNext()) {
                jedis.del(it.next());
            }
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public City getCityFromRedis(String name) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> city = jedis.hgetAll("city:" + name);
            double lat = Double.valueOf(city.get("lat"));
            double lng = Double.valueOf(city.get("lng"));
            return new City(name, lat, lng);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }

    }

    public Flight getFlightFromRedis(String id) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> data = jedis.hgetAll("flight:" + id);
            String name = data.get("id");
            String company = data.get("company");
            String departure = data.get("departure");
            String destination = data.get("destination");
            String airplane = data.get("airplane");
            String distance = data.get("distance");
            return new Flight(new FlightContext(company, departure, destination, airplane),this);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    /*public Flight getALLFlightFromRedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Map<String, String> data = jedis.hgetAll("flight:" + id);
            String name = data.get("id");
            String company = data.get("company");
            String departure = data.get("departure");
            String destination = data.get("destination");
            String airplane = data.get("airplane");
            String distance = data.get("distance");
            return new Flight(company, departure, destination, airplane);
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }

    }*/


    public void readFlightsFromRedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            Set<String> names = jedis.keys("flight:*");
            java.util.Iterator<String> it = names.iterator();
            while (it.hasNext()) {
                String s = it.next();
                System.out.println(s + " : " + jedis.get(s));
            }
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public void readCitiesFromRedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            Set<String> names = jedis.keys("city:*");
            java.util.Iterator<String> it = names.iterator();
            while (it.hasNext()) {
                String s = it.next();
                System.out.println(s + " : " + jedis.get(s));
            }
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }
    /*

    public void readCitiesFromRedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            Set<String> names = jedis.keys("*");
            java.util.Iterator<String> it = names.iterator();
            while (it.hasNext()) {
                DB.getDbInstance().addCity(
                        getFromRedis(it.next()).getKey(),
                        getFromRedis(it.next()).getValue());
            }
        } finally {
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }*/
}
