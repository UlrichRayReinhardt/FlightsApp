package Location;

import Jedis_db.StoreElement;

import java.util.HashMap;
import java.util.Map;

public class City extends StoreElement{
    private String name;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public City(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = (latitude != null) ? Double.valueOf(latitude) : 0;
        //this.latitude = Double.valueOf(latitude);
        this.longitude = (longitude != null) ? Double.valueOf(longitude) : 0;
    }

    /*public Location getLocation() {
        return new Location(latitude, longitude);
    }*/

    public String getName() {
        return name;
    }

   public String getInfo() {
        return "#" + name +
                "lat:" + latitude +
                "lng:" + longitude;

    }

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("latitude", String.valueOf(latitude));
        properties.put("longitude", String.valueOf(longitude));
        return properties;
    }
}