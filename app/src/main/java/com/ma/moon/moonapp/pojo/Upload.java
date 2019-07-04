package com.ma.moon.moonapp.pojo;

import android.net.Uri;
import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.HashMap;
import java.util.Map;

public class Upload implements Structure {

    //Objects and Variables:
    public String imgName,imgURL;

    //Constructors:
    public Upload(String imgName, String imgURL) {
        this.imgName = imgName;
        this.imgURL = imgURL;
    }

    public Upload() {
    }

    //Getters:
    public String getImgName() {
        return imgName;
    }

    public String getImgURL() {
        return imgURL;
    }

    @Override
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();
        hashMap.put("Name" , imgName);
        hashMap.put("Image URl" , imgURL);
        return hashMap;
    }
}
