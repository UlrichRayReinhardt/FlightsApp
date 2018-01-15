package FlightApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Deprecated
public class Assignment {

    private HashMap<Flight, AirPlane> company;

    public Assignment() {
        company = new HashMap<>();
    }

    public void addRoute(Flight schedule) {
        if (checkIsFlightAlreadyExists(schedule))
            //dont add
            System.out.println("FlightScheduler already exists");
        else company.put(schedule, null);
    }

    public boolean checkIsFlightAlreadyExists(Flight schedule) {
        return company.containsKey(schedule);
    }

    public void setPlane(Flight schedule, AirPlane plane) {
        if (checkIsFlightAlreadyExists(schedule))
            System.out.println("FlightScheduler already exists");
        else company.put(schedule, plane);
    }

    public boolean isPlaneAssigned(Flight schedule) {
        if (company.get(schedule) == null)
            return false;
        else return true;
    }

    public ArrayList<String> getScheduleList() {
        ArrayList<String> schedulerList = new ArrayList<>();
        for (Map.Entry<Flight, AirPlane> entry : company.entrySet()) {
            schedulerList.add(entry.toString());
        }
        return schedulerList;
    }

    public void printAllScheduleList() {
        ArrayList<String> schedulerList = getScheduleList();
        for (String schedule : schedulerList)
            System.out.println(schedule);
    }

    public String getPlaneInfo(Flight schedule) {
        return company.get(schedule).getPlaneModel() + company.get(schedule).getBortNumber();
    }
}



