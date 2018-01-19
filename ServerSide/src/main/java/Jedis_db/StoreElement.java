package Jedis_db;

import java.util.Map;

public abstract class StoreElement<T> {

    private String name;
    public abstract String getInfo();

    public String getName() {
        return name;
    }

    public abstract Map<String,String> getProperties();

}
