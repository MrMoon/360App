package com.ma.moon.moonapp.pojo;

import com.google.firebase.database.Exclude;
import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House implements Structure {

    //Objects and Variables:
    private String perX, price,name;
    //Constructors:
    public House(String perX, String price, String name) {
        this.perX = perX;
        this.price = price;
        this.name = name;
    }

    public House(String perX, String price) {
        this.perX = perX;
        this.price = price;
    }

    public House() {
    }

    //Getters and Setters:


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPerX() {
        return perX;
    }

    public void setPerX(String perX) {
        this.perX = perX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Exclude
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();
        hashMap.put("Name", name);
        hashMap.put("Price", price);
        hashMap.put("Per-X", perX);

        return hashMap;
    }

}
