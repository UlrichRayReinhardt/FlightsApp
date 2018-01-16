package Location;

public class City {
    String name;

    public City(String name) {
        this.name = name;
        //location = new Location(lat,lng);
    }

    @Override
    public String toString() {
        return name;
    }

    public String toStringData() {
        return name;
    }



    public char codeLetter() {
        return name.charAt(0);
    }

    public String getName() {
        return name;
    }

}