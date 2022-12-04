package com.example.milkyway.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthImagesDto {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("image")
    @Expose
    private String image;
}
