package com.ma.moon.moonapp.pojo;

import java.util.HashMap;
import java.util.Map;

public class Laptops extends Electronics {

    //Objects and Variables:
    private String CPU,RAM,battery,size;

    //Constructors:

    public Laptops(String name, String price, String CPU, String RAM, String battery, String size) {
        super(name, price);
        this.CPU = CPU;
        this.RAM = RAM;
        this.battery = battery;
        this.size = size;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
