package com.roberv.skin.dtos;

public class SkinDTO {
    private String name;
    private String type;
    private double price;
    private String color;

    public SkinDTO() {
    }

    public SkinDTO(String name, String type, double price, String color) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
