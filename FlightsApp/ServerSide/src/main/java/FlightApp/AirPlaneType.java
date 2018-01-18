package FlightApp;

/*Виробник Airbus. Моделі: A310, A320, A330.
	Виробник Boeing. Моделі: 737, 747, 767, 777.
            	Виробник Embraer. Моделі: 175, 190, 195.*/

public enum AirPlaneType {
    BOEING_737("Boeing", "737"),
    BOEING_747("Boeing", "747"),
    BOEING_767("Boeing", "767"),
    BOEING_777("Boeing", "777"),
    AIRBUS_A310("Airbus", "A310"),
    AIRBUS_A320("Airbus", "A320"),
    AIRBUS_A330("Airbus", "A330"),
    EMBRAER_170("Embraer", "170"),
    EMBRAER_190("Embraer", "190"),
    EMBRAER_195("Embraer", "195");

    String mfr;
    String model;

    AirPlaneType(String mfr, String model) {
        this.mfr = mfr;
        this.model = model;
    }

    @Override
    public String toString() {
        return this.mfr + " " + model;
    }

}


