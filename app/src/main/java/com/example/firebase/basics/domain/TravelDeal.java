package com.example.firebase.basics.domain;

import java.io.Serializable;

public class TravelDeal implements Serializable {
    private String id;
    private String title;
    private String price;
    private String description;
    private String imageUri;
    private String imageName;

    public TravelDeal() {
    }

    public TravelDeal(String title, String price, String description, String imageUri, String imageName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUri = imageUri;
        this.imageName = imageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
