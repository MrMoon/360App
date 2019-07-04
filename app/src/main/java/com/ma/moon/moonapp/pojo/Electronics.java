package com.ma.moon.moonapp.pojo;

import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.HashMap;
import java.util.Map;


abstract public class Electronics implements Structure {

    //Variables and Objects:
    private String name,price;

    public Electronics(String name, String price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();
        hashMap.put("Name",name);
        hashMap.put("Price",price);

        return hashMap;
    }
}
