package com.example.milkyway.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateInfoDto {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("description")
    @Expose
    private String description;

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getPlace() {return place; }

    public void setPlace(String place) { this.place = place; }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }
}
