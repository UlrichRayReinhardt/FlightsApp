package Jedis_db;

import FlightApp.Suite;

public class Printer {

    public static void print(Suite suite) {
        System.out.print(suite.getName());
        suite.forEach((ele)-> System.out.print(ele+" , "));
    }
}

