package com.example.carsearch;

public class Car {
    private String brand;
    private String model;
    private int year;
    private String description;
    private int cost;
    private int imageResourceId;

    public Car(String brand, String model, int year, String description, int cost, int imageResourceId) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.description = description;
        this.cost = cost;
        this.imageResourceId = imageResourceId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
