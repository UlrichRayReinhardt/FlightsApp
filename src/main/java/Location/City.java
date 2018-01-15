package Location;

import java.io.IOException;

public class City {
    String name;
    int code;
    Location location;

    public City(String name, double lat, double lng) {
        this.name = name;
        this.code = code;
        location = new Location(lat,lng);
    }

    @Override
    public String toString() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public char codeLetter() {
        return name.charAt(0);
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}