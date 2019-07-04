package com.ma.moon.moonapp.pojo;

import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.HashMap;
import java.util.Map;

public class Cars implements Structure {

    //Variables and Objects:
    private String name,price,engine,power;

    //Constructors:
    public Cars(String name, String price, String engine, String power) {
        this.name = name;
        this.price = price;
        this.engine = engine;
        this.power = power;
    }

    @Override
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();

        hashMap.put("Name",name);
        hashMap.put("Price",price);
        hashMap.put("Engine",engine);
        hashMap.put("Power",power);

        return hashMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
