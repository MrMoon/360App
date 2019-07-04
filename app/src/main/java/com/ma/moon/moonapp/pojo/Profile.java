package com.ma.moon.moonapp.pojo;

import com.google.firebase.database.Exclude;
import com.ma.moon.moonapp.Interfaces.Structure;

import java.util.HashMap;
import java.util.Map;

public class Profile implements Structure {

    //Objects and Variables:
    private String firstName, lastName, email, password, confirmedPassword, fullName ,id;
    private Map<String,Object> profileMap = new HashMap<>();

    //Constructors:
    public Profile(String firstName, String lastName, String email, String password, String confirmedPassword, String fullName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.fullName = fullName;
        this.id = id;
    }

    public Profile(String email, String fullName, String id) {
        this.email = email;
        this.fullName = fullName;
        this.id = id;
    }

    public Profile(String firstName, String lastName, String email, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public Profile(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public Profile() {

    }

    //Getters and Setters:
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getProfileMap() {
        return profileMap;
    }

    public void setProfileMap(Map<String, Object> profileMap) {
        this.profileMap = profileMap;
    }

    /**
     * Putting the Profile Info into a HashMap, so it could be easily written into the Database
     * @param hashMap
     * @return
     */
    @Override
    @Exclude
    public Map<String, Object> toMap(HashMap<String, Object> hashMap) {
        hashMap = new HashMap<>();
        hashMap.put("uid" , id);
        hashMap.put("fullname" ,fullName);
        hashMap.put("email" , email);
        hashMap.put("ProfileMap" , profileMap);

        return hashMap;
    }

}
