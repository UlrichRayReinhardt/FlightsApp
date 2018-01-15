package FlightApp;

/*Виробник Airbus. Моделі: A310, A320, A330.
	Виробник Boeing. Моделі: 737, 747, 767, 777.
            	Виробник Embraer. Моделі: 175, 190, 195.*/

public enum AirPlaneType {
    BOEING_737,
    BOEING_747,
    BOEING_767,
    BOEING_777,
    AIRBUS_A310,
    AIRBUS_A320,
    AIRBUS_A330,
    EMBRAER_170,
    EMBRAER_190,
    EMBRAER_195;


    @Override
    public String toString() {
        return this.name();
    }

}


