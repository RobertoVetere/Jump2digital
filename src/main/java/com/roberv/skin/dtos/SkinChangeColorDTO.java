package com.roberv.skin.dtos;

public class SkinChangeColorDTO {
    private Long id;
    private String name;

    public SkinChangeColorDTO(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
