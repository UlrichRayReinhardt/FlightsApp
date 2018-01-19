package Location;

public class City {
    private String name;
    private String latitude;
    private String longitude;

    public City(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getLocation(){
        return new Location(latitude, longitude);
    }

    @Override
    public String toString() {
        return name;
    }

    public char codeLetter() {
        return name.charAt(0);
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude != null ? latitude : "0";
    }

    public String getLongitude() {
        return longitude != null ? longitude : "0";

    }
}