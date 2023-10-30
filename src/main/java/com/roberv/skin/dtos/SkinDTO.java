package com.roberv.skin.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SkinDTO {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String type;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double price;
    private String color;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String description;

    private SkinDTO() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private long id;
        private String name;
        private String type;
        private double price;
        private String color;
        private String description;

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public SkinDTO build() {
            SkinDTO skinDTO = new SkinDTO();
            skinDTO.id = this.id;
            skinDTO.name = this.name;
            skinDTO.type = this.type;
            skinDTO.price = this.price;
            skinDTO.color = this.color;
            skinDTO.description = this.description;
            return skinDTO;
        }
    }
}
