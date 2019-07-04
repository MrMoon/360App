package com.ma.moon.moonapp.pojo;

public class Moblies extends Electronics {

    //Objects and Variables:
    private String CPU, RAM, battery, storage, size;

    //Constructors:


    public Moblies(String name, String price, String CPU, String RAM, String battery, String storage, String size) {
        super(name, price);
        this.CPU = CPU;
        this.RAM = RAM;
        this.battery = battery;
        this.storage = storage;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
