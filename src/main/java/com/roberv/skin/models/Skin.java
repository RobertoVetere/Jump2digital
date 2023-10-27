package com.roberv.skin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private double price;
    private String color;
    private String description;

    public Skin() {
    }

    public Skin(String name, String type, double price, String color, String descpription) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.color = color;
        this.description = descpription;
    }

    public Long getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String descpription) {
        this.description = descpription;
    }

    @Override
    public String toString() {
        return "Skin{" + '\n' +
                "id=" + id + '\n' +
                "name='" + name + '\n' +
                "type='" + type + '\n' +
                "price=" + price + '\n' +
                "color='" + color + '\n' +
                "description='" + description + '\n' +
                '}';
    }
}
