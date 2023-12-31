package com.uni.plovdiv.hapnitopni.entities;

public class Favourites {
    private int id;
    private int image;
    private String name;
    private String description;
    private String price;
    private String status;

    public Favourites(int image, String name, String description, String price,String status) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
