package com.ma.moon.moonapp.pojo;

import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categories implements Structure {

    //Objects and Variables:
    private List<String> names = new ArrayList<>();

    //Constructors:
    public Categories(List<String> names) {
        this.names = names;
    }

    //Getters and Setters:
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();
        hashMap.put("Name", names);

        return hashMap;
    }

}
