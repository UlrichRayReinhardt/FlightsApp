package Location;

import com.google.code.geocoder.model.LatLng;

public class Location {

    private double latitude;
    private double longitude;

    public Location(LatLng location) {
        this.latitude = location.getLat().doubleValue();
        this.longitude = location.getLng().doubleValue();
    }

    public Location(double lat, double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }

    public double lat() {
        return latitude;
    }

    public double lng() {
        return longitude;
    }

    @Override
    public String toString() {
        return "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}



