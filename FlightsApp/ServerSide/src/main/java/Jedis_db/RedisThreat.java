package Jedis_db;

import java.io.IOException;

public class RedisThreat extends Thread {

    @Override
    public void run() {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] options = new String[]{"redis.windows.conf"};
            rt.exec("c:\\redis\\redis-server.exe", options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
