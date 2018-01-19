package Jedis_db;

import FlightApp.Flight;
import FlightApp.FlightBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FlightsController extends JedisController {

    private static Set<Flight> flights;

    FlightsController(JedisPool pool) {
        this.pool = pool;
        flights = new HashSet<>();
        loadFlights();
    }

    private void loadFlights() {
        Jedis jedis = pool.getResource();
        Set<String> data = jedis.keys("flight:*");
        for (String name : data) {
            loadFlight(name);
        }
    }

    public void add(Flight flight) {
        Jedis jedis = pool.getResource();
        jedis.hmset("flight:" + flight.getName(), flight.getProperties());
    }

    private Flight loadFlight(String name) {
        Jedis jedis = pool.getResource();
        Map<String, String> data = jedis.hgetAll("flight:" + name);
        return new FlightBuilder("company", "departure", "destination", "airplane")
                .build();
    }

    public void remove(String key) {
        Jedis jedis = pool.getResource();
        jedis.del("flight:" + key);
    }

}
