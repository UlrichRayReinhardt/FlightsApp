package Jedis_db;

import FlightApp.Suite;

import java.util.List;

public class Printer{

    public static void print(Suite suite) {
        System.out.print(suite.getName());
        suite.forEach((ele)-> System.out.print(ele+" , "));
    }

    public static void printList(List<StoreElement> list) {
        for (StoreElement StoreElement: list){
            System.out.println(StoreElement.getInfo());
        }
        list.forEach((ele)-> System.out.print(ele.getInfo()+" , "));
    }
}

