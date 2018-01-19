package Location;

import com.google.code.geocoder.model.LatLng;

import java.io.IOException;

public class Location {

    private double latitude;
    private double longitude;

    public Location(LatLng location) {
        this.latitude = location.getLat().doubleValue();
        this.longitude = location.getLng().doubleValue();
    }

    public Location(String lat, String lng) {
        this.latitude = Double.valueOf(lat);
        this.longitude = Double.valueOf(lng);
    }

    public static Location fetchLocation(String name) {
        try {
            return new GeoCode().getLocation(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoCode.LocationNotFoundException e) {
            e.doNothing();
        }
        return null;
    }

    public double lat() {
        return latitude;
    }

    public double lng() {
        return longitude;
    }

    }




