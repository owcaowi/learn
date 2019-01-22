package com.example.aprzybylo.learn;

import java.io.Serializable;


public class Car implements Serializable {

    private String name;

    private int wheels;

    private String color;

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static String showCar(){
        return "Admin Car";
    }
}
