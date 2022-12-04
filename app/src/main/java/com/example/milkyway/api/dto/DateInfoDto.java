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
}
