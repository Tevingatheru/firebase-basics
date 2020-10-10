package com.example.firebase.basics;

public class TravelDeal {
    private int id;
    private String title;
    private double price;
    private String description;
    private String imageUri;

    public TravelDeal(String title, double price, String description, String imageUri) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
