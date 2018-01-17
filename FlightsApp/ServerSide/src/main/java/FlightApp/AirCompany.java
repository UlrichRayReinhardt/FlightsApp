package FlightApp;

import java.util.HashSet;
import java.util.Set;

public class AirCompany {

    final private String companyName;
    private Set<AirPlane> planes;

    public AirCompany(String name) {
        this.companyName = name;
        this.planes = new HashSet<>();
    }

    /*public void registerPlane(AirPlane plane, String bortNumber) {
        try {
            analyzeDuplicateByBortNumber(plane.getBortNumber());
            plane.setBortNumber(bortNumber);
            planes.add(plane);
            System.out.println(plane.planeInfo() + " is successfully added to " + companyName);
        } catch (DuplicateException e) {
            System.err.print(e.getMessage() + " #Plane doesn't added");
        }
    }


    private void analyzeDuplicateByBortNumber(String number) throws DuplicateException {
        if (planes.contains(number))
            throw new DuplicateException("----AirPlane with bort number " + number + " already exists");
    }

    public String getCompanyName() {
        return companyName;
    }*/
}
