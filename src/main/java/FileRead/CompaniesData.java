package FileRead;

import FlightApp.AirCompany;
@Deprecated
public class CompaniesData {


    public AirCompany getBritishAirWays(){
        AirCompany company = new AirCompany("British AirWays");

        /*company.addPlane(AIRBUS_A310, "BA-4321");
        company.registerPlane(AirPlaneFactory.buy(BOEING_767), "BA-5655");
        company.registerPlane(AirPlaneFactory.buy(EMBRAER_195), "BA-3221");
        company.registerPlane(AirPlaneFactory.buy(AIRBUS_A320), "BA-4000");*/

        return company;
    }


    public AirCompany getUIA(){
        AirCompany company = new AirCompany("UIA");

        /*company.registerPlane(AirPlaneFactory.buy(EMBRAER_190), "UIA-4698");
        company.registerPlane(AirPlaneFactory.buy(EMBRAER_190), "UIA-8070");
        company.registerPlane(AirPlaneFactory.buy(EMBRAER_195), "UIA-6044");
        company.registerPlane(AirPlaneFactory.buy(EMBRAER_195), "UIA-7913");*/


        return company;
    }

}
