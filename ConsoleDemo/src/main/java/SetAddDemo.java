import Jedis_db.CityController;
import redis.clients.jedis.JedisPool;

public class SetAddDemo {

    public static void main(String[] args) {

        JedisPool pool = new JedisPool("localhost");
        CityController cityController = new CityController(pool);




        /*String suiteKeyAll = "---all---";
        String suiteKeyMySuite1 = "MySuite1";

        controller.addToRedis(suiteKeyAll, "KL 5686", "RT 2525", "AZ 9898");
        Suite all = controller.loadSuite(suiteKeyAll);
        controller.removeFromSuite(all,"RT 2525");
        controller.addToRedis(suiteKeyAll,"AZ 1231");

        controller.addToRedis(suiteKeyMySuite1, "KR 1631", "RT 8468", "TL 4889");
        controller.removeFromSuite(suiteKeyMySuite1, "RT 8468");

        Printer.print(all);*/


        }
}

