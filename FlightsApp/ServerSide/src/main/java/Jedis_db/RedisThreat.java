package Jedis_db;

import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class RedisThreat {

    public void runRedis() {
        Thread thread = new Thread() {
            public synchronized void run() {
                try {
                    Runtime rt = Runtime.getRuntime();
                    String[] options = new String[]{"redis.windows.conf"};
                    rt.exec("c:\\redis\\redis-server.exe", options);
                    System.out.println("Server started\n=====================");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
        try {
            JedisPool pool = new JedisPool("localhost");
            pool.getResource();
        } catch (Exception e) {
            System.out.println("no connection");
        }
    }
}

