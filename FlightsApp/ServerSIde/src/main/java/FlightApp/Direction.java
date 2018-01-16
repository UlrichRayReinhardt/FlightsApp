package FlightApp;

import Jedis_db.DB;
import Location.Location;

public class Direction {

   public static double getDistance(String from, String to){
         Location dept = DB.getDbInstance().getCitylocation(from);
         Location dest = DB.getDbInstance().getCitylocation(to);
        return checkDistance(dept.lat(), dept.lng(), dest.lat(), dest.lng());
    }

    private static double checkDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        return dist * 60 * 1.1515 * 1.609344;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}


