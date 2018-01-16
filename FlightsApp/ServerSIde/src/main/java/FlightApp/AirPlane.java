package FlightApp;

public class AirPlane{

    private AirPlaneType type;
    private String bortNumber;

   public AirPlane(AirPlaneType type) {
        this.type = type;
    }

    public String getPlaneModel() {
        return type.toString();
    }

    public void setBortNumber(String bortNumber) {
        this.bortNumber = bortNumber;
    }

    public String getBortNumber() {
        return bortNumber;
    }

    @Override
    public String toString() {
        return getPlaneModel() + getBortNumber();
    }

    public String planeInfo() {
        String info = "#Airplane: " + getPlaneModel() + " #Bort: " + getBortNumber();
        return info;
    }


}
